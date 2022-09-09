package com.example.pokemondisplayapp.model.datasource

import com.example.pokemondisplayapp.model.data.PokemonPojo
import com.example.pokemondisplayapp.model.data.datapokemon.PokemonData
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("pokemon")
    suspend fun getAllPokemon(): PokemonPojo

    @GET("pokemon/{userId}")
    suspend fun getPokemonData(@Path("userId") userId: String): PokemonData

    @GET("pokemon")
    suspend fun getNextPage(
        @Query("limit") limit : Int,
        @Query("offset") offset : Int
    ): PokemonPojo
}