package com.example.dogs.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.dogs.R
import com.example.dogs.model.DogBreed
import kotlinx.android.synthetic.main.item_dog.view.*

class DogsListAdapter(val dogList: ArrayList<DogBreed>) :
    RecyclerView.Adapter<DogsListAdapter.DogViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_dog, parent, false)
        return DogViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dogList.size
    }

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        holder.view.name.text = dogList[position].dogBreed
        holder.view.lifeSpan.text = dogList[position].lifeSpan
        holder.view.setOnClickListener {
            var action = ListFragmentDirections.actionDetailFragment()
            action.dogUuid = position
            Navigation.findNavController(it).navigate(action)
        }
    }

    fun updateDogList(newDogList: List<DogBreed>) {
        dogList.clear()
        dogList.addAll(newDogList)
        notifyDataSetChanged()
    }

    class DogViewHolder(var view: View) : RecyclerView.ViewHolder(view)
}