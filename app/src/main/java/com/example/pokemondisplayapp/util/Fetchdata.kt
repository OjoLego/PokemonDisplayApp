package com.example.pokemondisplayapp.util

object Fetchdata {

    fun getPageOffset(url: String): Int{
        return url.substring(41..url.lastIndex).takeWhile { it != '&' }.toInt()
    }

    fun getId(url: String): String{
        return url.substring(34..url.lastIndex -1)
    }

    fun validOffsetInput(value: String): Boolean{
        return !(value == "0" || value == "")
    }

}