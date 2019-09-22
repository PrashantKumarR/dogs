package com.example.dogs.model

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class DogsApiService {
    private val BASE_URL = "https://raw.githubusercontent.com"
//        private val BASE_URL = "*.gonuclei.com"
//    private val BASE_URL = "publicobject.com"
//    val certificatePinner = CertificatePinner.Builder()
//        .add(BASE_URL, "sha256/sm6xYAA3V3PtiyWIX6G/FY2kgHCRzR1k9XndcF5A0mg=")
//        .add(BASE_URL, "sha256/k2v657xBsOVe1PQRwOsHsw3bsGT2VzIqz5K+59sNQws=")
//        .add(BASE_URL, "sha256/WoiWRyIOVNa9ihaBciRSC7XHjliYS9VwUGOIud4PB18=")
//        .build()
//    val client = OkHttpClient.Builder()
//        .sslSocketFactory(SSLContext.getDefault())
//        .certificatePinner(certificatePinner)
//        .build()

    private val api = Retrofit.Builder()
        .baseUrl("$BASE_URL")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(DogsApi::class.java)

    fun getDogs(): Single<List<DogBreed>> {
        return api.getDogs()
    }
}