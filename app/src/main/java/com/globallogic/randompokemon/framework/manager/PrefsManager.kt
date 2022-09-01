package com.globallogic.randompokemon.framework.manager

import android.content.Context
import com.globallogic.core.domain.PokeIndex
import com.globallogic.randompokemon.adapter.PokeIndexJasonAdapter
import com.globallogic.randompokemon.framework.PrefsCache

class PrefsManager(
    context: Context,
    private val pokeIndexJasonAdapter: PokeIndexJasonAdapter,
) : PrefsCache {

    companion object {
        private const val POKE_INDEX_FILE = "poke_index"
        private const val POKE_INDEX = "last_refreshed"
    }

    private val pokeIndexPrefs = context.getSharedPreferences(POKE_INDEX_FILE, Context.MODE_PRIVATE)

    override fun getPokeIndex(): PokeIndex? =
        pokeIndexJasonAdapter.parseJson(pokeIndexPrefs.getString(POKE_INDEX, null))

    override fun savePokeIndex(pokeIndex: PokeIndex) {
        pokeIndexPrefs.edit()
            .putString(
                POKE_INDEX,
                pokeIndexJasonAdapter.toString(pokeIndex = pokeIndex)
            ).apply()
    }
}