package com.globallogic.randompokemon.framework.provider

import com.globallogic.randompokemon.data.database.PokemonDao
import com.globallogic.randompokemon.framework.PokemonDatabase

fun provideDao(database: PokemonDatabase): PokemonDao = database.pokemonDao()