package com.globallogic.randompokemon.framework

import com.globallogic.core.domain.PokeIndex

/**
 * An interface for storing the PokeIndex in the SharedPreferences
 */
interface PrefsCache {
    fun savePokeIndex(pokeIndex: PokeIndex)
    fun getPokeIndex(): PokeIndex?
}