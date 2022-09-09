package com.example.pokemondisplayapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pokemondisplayapp.model.data.datapokemon.PokemonData
import com.example.pokemondisplayapp.viewmodel.MainViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.view.*

class ProfileFragment : Fragment() {

    lateinit var Viewmodel : MainViewModel
    private val args: ProfileFragmentArgs by navArgs()

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile,container,false)
        Viewmodel = ViewModelProvider(this).get(MainViewModel::class.java)
        Viewmodel.getPokemonData(args.hold).observe(viewLifecycleOwner, Observer {
            Picasso.get().load(it.sprites?.backDefault).into(view.firstPokemon)
            Picasso.get().load(it.sprites?.backShiny).into(view.secondPokemon)
            Picasso.get().load(it.sprites?.frontDefault).into(view.thirdPokemon)
            Picasso.get().load(it.sprites?.frontShiny).into(view.forthPokemon)

            recycler_move.adapter = MoveAdapter(it.moves)
            recycler_move.layoutManager = GridLayoutManager(requireContext(),3)
//            recyclerView.adapter = MoveAdapter(it.moves)
//            recyclerView.layoutManager = GridLayoutManager(requireContext(),3)

            view.firstAbility.text = it.abilities?.get(0)?.ability?.name
            view.secondAbility.text = it.abilities?.get(1)?.ability?.name

            var pokeStats = ""
            for (i in it.stats!!){
                pokeStats += "${i?.stat?.name}_${i?.basestat}"
            }
            view.pokemonStat.text = pokeStats

            view.heightDisplay.text = "Height: ${it.height}"
            view.weightDisplay.text = "Weight: ${it.weight}"
            view.typeDisplay.text = "Type: ${it.types?.get(0)?.type?.name}"

            Picasso.get().load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/56.png").into(view.bg_image)
//            Picasso.get().load("https://pokeres.bastionbot.org/images/pokemon/${args.hold}.png").into(view.bg_image)
        })
        return view
    }
}