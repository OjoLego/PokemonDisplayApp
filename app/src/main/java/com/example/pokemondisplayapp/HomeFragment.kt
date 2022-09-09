package com.example.pokemondisplayapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pokemondisplayapp.util.Fetchdata
import com.example.pokemondisplayapp.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.home_fragment.*
import kotlinx.android.synthetic.main.home_fragment.view.*

class FirstFragment : Fragment(),PokemonClick {

    lateinit var Viewmodel: MainViewModel
    var nextPokemonPage: Int? = null
    var prevPokemonPage: Int? = null
    var limit: Int = 20
    var offset: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Viewmodel = ViewModelProvider(this).get((MainViewModel::class.java))
        Viewmodel.getData().observe(viewLifecycleOwner, Observer {
            recyclerView.adapter = RecyclerAdapter(it.results,this)
            recyclerView.layoutManager = GridLayoutManager(requireContext(),2)

            it.next?.let {
                nextPokemonPage = Fetchdata.getPageOffset(it)
            }

            it.previous?.let {
                prevPokemonPage = Fetchdata.getPageOffset(it)
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.home_fragment, container, false)

        view.next.setOnClickListener {
            Viewmodel.getNextData(limit,nextPokemonPage!!).observe(viewLifecycleOwner, Observer {
                recyclerView.adapter = RecyclerAdapter(it.results,this)
                recyclerView.layoutManager = GridLayoutManager(requireContext(),2)

                it.next?.let {
                    nextPokemonPage = Fetchdata.getPageOffset(it)
                    offset = nextPokemonPage!! - limit
                }

                it.previous?.let {
                    prevPokemonPage = Fetchdata.getPageOffset(it)
                }
            })
            view.prev.isVisible = true
        }
        view.prev.setOnClickListener {
            Viewmodel.getNextData(limit, prevPokemonPage!!).observe(viewLifecycleOwner, Observer {
                recyclerView.adapter = RecyclerAdapter(it.results,this)
                recyclerView.layoutManager = GridLayoutManager(requireContext(),2)

                it.next?.let {
                    nextPokemonPage = Fetchdata.getPageOffset(it)
                    offset = nextPokemonPage!! - limit
                }

                it.previous?.let {
                    prevPokemonPage = Fetchdata.getPageOffset(it)
                }
            })
        }
        view.btn_limit.setOnClickListener {
            var limitvalue = inputLimit.text.toString()
            var valid = Fetchdata.validOffsetInput(limitvalue)

            if (valid){
                limit = inputLimit.text.toString().toInt()

                Viewmodel.getNextData(limit, offset).observe(viewLifecycleOwner, Observer {
                    recyclerView.adapter = RecyclerAdapter(it.results,this)
                    recyclerView.layoutManager = GridLayoutManager(requireContext(),2)

                    it.next?.let {
                        nextPokemonPage = Fetchdata.getPageOffset(it)
                    }

                    it.previous?.let {
                        prevPokemonPage = Fetchdata.getPageOffset(it)
                    }
                })
            }else{
                inputLimit.text.clear()
                inputLimit.setError("0 or empty field is not allowed")
            }
        }
        return view
    }

//    override fun onnodeClick(position: Int) {
//        val intent = Intent(this,ProfileFragment::class.java)
//        startActivity(intent)
//    }


    override fun onnodeClick(id: String){
        val action = FirstFragmentDirections.firstCall().setHold(id)
        Navigation.findNavController(requireView()).navigate(action)
    }
}