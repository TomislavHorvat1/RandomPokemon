package com.globallogic.randompokemon.adapter

import com.globallogic.core.domain.Pokemon
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.ToJson

class PokemonJsonAdapter(moshi: Moshi) {
    private val adapter: JsonAdapter<Pokemon> = moshi.adapter(Pokemon::class.java)

    @ToJson
    fun toString(pokemon: Pokemon): String = adapter.toJson(pokemon)

    @FromJson
    fun parseJson(json: String?): Pokemon? = json?.let { adapter.fromJson(json) }
}