package com.example.pokemondisplayapp.model.repository

import androidx.lifecycle.LiveData
import com.example.pokemondisplayapp.model.data.PokemonPojo
import com.example.pokemondisplayapp.model.data.PostData
import com.example.pokemondisplayapp.model.data.datapokemon.PokemonData
import com.example.pokemondisplayapp.model.datasource.MyRetrofitBuilder
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import okhttp3.MultipartBody

object MainRepository {

    var task: CompletableJob? = null

    fun getAllPokemon(): LiveData<PokemonPojo>{
        task = Job()
        return object : LiveData<PokemonPojo>(){
            override fun onActive() {
                super.onActive()
                task?.let {
                    CoroutineScope(IO + it).launch {
                        val allPokemon = MyRetrofitBuilder.apiService.getAllPokemon()

                        withContext(Main){
                            value = allPokemon
                            it.complete()
                        }
                    }
                }
            }
        }
    }

    fun getPokemonData(userId: String): LiveData<PokemonData>{

        task = Job()

        return object : LiveData<PokemonData>(){
            override fun onActive() {
                super.onActive()
                task?.let {
                    theTask ->
                    CoroutineScope(IO + theTask).launch {
                        val pokemonSingledata = MyRetrofitBuilder.apiService.getPokemonData(userId)

                        withContext(Main){
                            value = pokemonSingledata
                            theTask.complete()
                        }
                    }
                }
            }
        }
    }

    fun getNextPage(limit: Int, offset: Int): LiveData<PokemonPojo>{

        task = Job()

        return object : LiveData<PokemonPojo>(){
            override fun onActive() {
                super.onActive()
                task?.let { task ->
                    CoroutineScope(IO + task).launch {
                        val nextpage = MyRetrofitBuilder.apiService.getNextPage(limit, offset)

                        withContext(Main){
                            value = nextpage
                            task.complete()
                        }
                    }
                }
            }
        }

    }

    fun uploadImage(img: MultipartBody.Part): LiveData<PostData>{

        task = Job()

        return object : LiveData<PostData>(){
            override fun onActive() {
                super.onActive()
                task?.let { task ->
                    CoroutineScope(Main + task).launch {
                        val postResponse = MyRetrofitBuilder.postService.uploadToServer(img)

                        withContext(Main){
                            value = postResponse

                            task.complete()
                        }
                    }
                }
            }
        }

    }

    fun canceljob(){
        task?.cancel()
    }
}