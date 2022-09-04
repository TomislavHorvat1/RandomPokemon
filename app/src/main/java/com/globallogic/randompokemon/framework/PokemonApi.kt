package com.globallogic.randompokemon.framework

import com.globallogic.randompokemon.data.api.PokeIndexDto
import com.globallogic.randompokemon.data.api.PokemonDto
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * A representation of the Pokemon API
 */
interface PokemonApi {
    @GET("pokemon/{id}/")
    suspend fun getPokemon(@Path("id") id: Int): PokemonDto

    @GET("pokemon?limit=100000&offset=0")
    suspend fun getPokeIndex(): PokeIndexDto
}