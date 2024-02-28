package com.example.superheroes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.superheroes.R
import com.example.superheroes.data.Superhero
import com.example.superheroes.databinding.ItemSuperheroBinding

class SuperheroAdapter(private var items:List<Superhero> = listOf()) : RecyclerView.Adapter<SuperheroViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperheroViewHolder {
        val binding = ItemSuperheroBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SuperheroViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: SuperheroViewHolder, position: Int) {
        holder.render(items[position])
        //holder.itemView.setOnClickListener { onClickListener(position) }
    }

    fun updateItems(results: List<Superhero>?) {
        items = results!!
        notifyDataSetChanged()
    }
}


class SuperheroViewHolder(val binding:ItemSuperheroBinding) : RecyclerView.ViewHolder(binding.root) {

    fun render(superhero: Superhero) {
        binding.nameTextView.text = superhero.name
        binding.photoImageView.setImageResource(R.drawable.baseline_audio_file_24)
    }
}