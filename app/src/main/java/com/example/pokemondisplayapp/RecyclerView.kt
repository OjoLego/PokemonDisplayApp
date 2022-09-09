package com.example.pokemondisplayapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemondisplayapp.model.data.Results
import com.example.pokemondisplayapp.util.Fetchdata
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.carview.view.*
import kotlinx.android.synthetic.main.fragment_profile.view.*

class RecyclerAdapter(var pokemon: ArrayList<Results>,
                      var nodeList: PokemonClick): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

                          inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView),View.OnClickListener{

                              var textView: TextView = itemView.pokemonName
                              var imageView: ImageView = itemView.pokemonImage
                              var idDisplay: TextView = itemView.displayId

                              init {
                                  itemView.setOnClickListener(this)
                              }

                              override fun onClick(v: View?) {
                                  var id = Fetchdata.getId(pokemon[adapterPosition].url)
                                  nodeList.onnodeClick(id)
                              }
                          }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.carview,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var id = Fetchdata.getId(pokemon[position].url)
        holder.idDisplay.text = id
        holder.textView.text = pokemon[position].name
//        Picasso.get().load("https://pokeres.bastionbot.org/images/pokemon/${id}.png").into(holder.imageView)
        Picasso.get().load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${id}.png").into(holder.imageView)

    }

    override fun getItemCount(): Int {
        return pokemon.size
    }


}