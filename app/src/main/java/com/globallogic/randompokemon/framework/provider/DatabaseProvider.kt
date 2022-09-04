package com.globallogic.randompokemon.framework.provider

import android.content.Context
import androidx.room.Room
import com.globallogic.randompokemon.framework.PokemonDatabase

/**
 * Provides a [PokemonDatabase] instance.
 *
 * @param context
 */
fun provideDatabase(context: Context): PokemonDatabase = Room
    .databaseBuilder(
        context.applicationContext,
        PokemonDatabase::class.java,
        PokemonDatabase.POKEMON_DB
    )
    .build()