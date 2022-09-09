package com.example.pokemondisplayapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.pokemondisplayapp.model.data.PokemonPojo
import com.example.pokemondisplayapp.model.data.PostData
import com.example.pokemondisplayapp.model.data.datapokemon.PokemonData
import com.example.pokemondisplayapp.model.repository.MainRepository
import okhttp3.MultipartBody

class MainViewModel: ViewModel() {

    fun getPokemonData(_userId: String): LiveData<PokemonData>{
        return MainRepository.getPokemonData(_userId)
    }

    fun cancelJob(){
        MainRepository.canceljob()
    }

    fun getData(): LiveData<PokemonPojo>{
        return MainRepository.getAllPokemon()
    }

    fun getNextData(limit: Int, offset: Int): LiveData<PokemonPojo>{
        return MainRepository.getNextPage(limit,offset)
    }

    fun uploadImage(img: MultipartBody.Part): LiveData<PostData>{
        return MainRepository.uploadImage(img)
    }

}