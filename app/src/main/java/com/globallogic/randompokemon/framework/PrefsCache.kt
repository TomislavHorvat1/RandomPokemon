package com.globallogic.randompokemon.framework

import com.globallogic.core.domain.PokeIndex

interface PrefsCache {
    fun savePokeIndex(pokeIndex: PokeIndex)
    fun getPokeIndex(): PokeIndex?
}