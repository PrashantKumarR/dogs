package com.example.dogs.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.dogs.model.DogBreed
import com.example.dogs.model.DogDatabase
import com.example.dogs.model.DogsApiService
import com.example.dogs.util.NotificationsHelper
import com.example.dogs.util.SharedPreferencesHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch
import java.lang.NumberFormatException
import java.util.concurrent.TimeUnit

class ListViewModel(application: Application) : BaseViewModel(application) {
    val dogs = MutableLiveData<List<DogBreed>>()
    val dogLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()
    private val dogsService = DogsApiService()
    private val disposable = CompositeDisposable()
    private var prefHelper = SharedPreferencesHelper(getApplication())
    private var refreshTime = TimeUnit.MINUTES.toNanos(5)

    fun refresh() {
        checkCachedDuration()
        val updateTime: Long? = prefHelper.getUpdateTime()
        if (updateTime != null && updateTime != 0L && System.nanoTime() - updateTime < refreshTime) {
            fetchFromDatabase()
        } else {
            fetchFromRemote()
        }
    }

    private fun checkCachedDuration() {
        val cachedPreference = prefHelper.getCachedDuration()
        try {
            val cachedPreferenceInt = cachedPreference?.toLong() ?: 5 * 60
            refreshTime = TimeUnit.SECONDS.toNanos(cachedPreferenceInt)
        } catch (e: NumberFormatException) {
            e.printStackTrace()
        }
    }

    fun refreshBypassCache() {
        fetchFromRemote()
    }

    private fun fetchFromDatabase() {
        loading.value = true
        launch {
            val dogs = DogDatabase(getApplication()).dogDao().getAllDogs()
            Toast.makeText(getApplication(), "DATABASE!", Toast.LENGTH_LONG).show()
            dogRetrieved(dogs)
        }
    }

    private fun fetchFromRemote() {
        loading.value = true
        disposable.add(
            dogsService.getDogs()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<DogBreed>>() {
                    override fun onSuccess(newDogList: List<DogBreed>) {
                        storeDogLocally(newDogList)
                        Toast.makeText(getApplication(), "REMOTE!", Toast.LENGTH_LONG).show()
                        NotificationsHelper(getApplication()).createNotification()
                    }

                    override fun onError(e: Throwable) {
                        dogLoadError.value = true
                        loading.value = false
                        e.printStackTrace()
                    }
                })
        )

    }

    private fun dogRetrieved(newDogList: List<DogBreed>) {
        dogs.value = newDogList
        dogLoadError.value = false
        loading.value = false
    }

    private fun storeDogLocally(list: List<DogBreed>) {
        launch {
            val dogDao = DogDatabase(getApplication()).dogDao()
            dogDao.deleteAllDogs()
            val result: List<Long> = dogDao.insertAll(*list.toTypedArray())
            var i = 0
            while (i < list.size) {
                list[i].uuid = result[i].toInt()
                ++i
            }
            dogRetrieved(list)
        }
        prefHelper.saveUpdateTime(System.nanoTime())
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}