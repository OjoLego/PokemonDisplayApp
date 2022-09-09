package com.example.pokemondisplayapp.model.data

data class PokemonPojo(
    var count:Int,
    var next:String?,
    var previous:String?,
    var results:ArrayList<Results>
) {
}