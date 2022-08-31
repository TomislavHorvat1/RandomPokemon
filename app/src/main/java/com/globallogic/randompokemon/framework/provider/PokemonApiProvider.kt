package com.globallogic.randompokemon.framework.provider

import com.globallogic.randompokemon.framework.PokemonApi
import retrofit2.Retrofit

fun providePokemonApi(retrofit: Retrofit): PokemonApi = retrofit.create(PokemonApi::class.java)