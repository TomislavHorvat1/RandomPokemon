package com.globallogic.randompokemon.adapter

import com.globallogic.core.domain.PokeIndex
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.ToJson

/**
 * A JSON adapter for the [PokeIndex] to be used with [Moshi].
 */
class PokeIndexJasonAdapter(moshi: Moshi) {
    private val adapter: JsonAdapter<PokeIndex> = moshi.adapter(PokeIndex::class.java)

    @ToJson
    fun toString(pokeIndex: PokeIndex): String = adapter.toJson(pokeIndex)

    @FromJson
    fun parseJson(json: String?): PokeIndex? = json?.let { adapter.fromJson(json) }
}