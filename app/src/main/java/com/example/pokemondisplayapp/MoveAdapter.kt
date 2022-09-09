package com.example.pokemondisplayapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemondisplayapp.model.data.datapokemon.PokemonData
import kotlinx.android.synthetic.main.movedata_layout.view.*

class MoveAdapter(var moves: List<PokemonData.Move>): RecyclerView.Adapter<MoveAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var data: TextView = itemView.movesText
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.movedata_layout,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.data.text = moves[position].move?.name
    }

    override fun getItemCount(): Int {
        return moves.size
    }


}