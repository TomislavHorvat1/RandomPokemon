package com.globallogic.randompokemon.framework.manager

import android.content.Context
import com.globallogic.core.domain.PokeIndex
import com.globallogic.randompokemon.adapter.PokeIndexJasonAdapter
import com.globallogic.randompokemon.framework.PrefsCache

/**
 * A manager to handle getting and setting data to the [SharedPreferences]
 *
 * @param context
 * @param adapter the JSON adapter for parsing the PokeIndex
 *
 * @property pokeIndexPrefs the reference to the shared preference file for storing the PokeIndex
 */
class PrefsManager(
    context: Context,
    private val adapter: PokeIndexJasonAdapter,
) : PrefsCache {

    companion object {
        private const val POKE_INDEX_FILE = "poke_index"
        private const val POKE_INDEX = "last_refreshed"
    }

    private val pokeIndexPrefs = context.getSharedPreferences(POKE_INDEX_FILE, Context.MODE_PRIVATE)

    /**
     * Gets the JSON string from the shared preferences, parses it and returns the PokeIndex
     *
     * @return the cached PokeIndex
     */
    override fun getPokeIndex(): PokeIndex? =
        adapter.parseJson(pokeIndexPrefs.getString(POKE_INDEX, null))

    /**
     * Converts the PokeIndex to a string and saves it to the shared preferences
     */
    override fun savePokeIndex(pokeIndex: PokeIndex) {
        pokeIndexPrefs.edit()
            .putString(
                POKE_INDEX,
                adapter.toString(pokeIndex = pokeIndex)
            ).apply()
    }
}