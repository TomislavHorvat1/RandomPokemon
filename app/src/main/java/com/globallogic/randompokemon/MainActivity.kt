package com.globallogic.randompokemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.Observer
import com.globallogic.core.domain.PokeIndex
import com.globallogic.randompokemon.ui.view.MainScreen
import com.globallogic.randompokemon.ui.viewmodel.PokemonViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * The main app activity.
 */
class MainActivity : ComponentActivity() {

    private val pokemonViewModel: PokemonViewModel by viewModel()

    private val isPokeIndexCachedObserver = Observer<Boolean> {
        if (!it) pokemonViewModel.onActivityResumed(false)
    }

    private val pokeIndexObserver = Observer<PokeIndex?> {
        it?.run { pokemonViewModel.onGetPokemonClicked() }
    }

    private val failedFetchPokemonObserver = Observer<Int?> {
        it?.run { pokemonViewModel.onGetPokemonClicked(false) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setObservers()

        setContent { MainScreen(pokemonViewModel = pokemonViewModel) }


    }

    private fun setObservers() {
        pokemonViewModel.isPokeIndexCached.observe(this, isPokeIndexCachedObserver)
        pokemonViewModel.pokeIndex.observe(this, pokeIndexObserver)
        pokemonViewModel.failedFetchPokemonId.observe(this, failedFetchPokemonObserver)
    }

    override fun onResume() {
        super.onResume()

        pokemonViewModel.onActivityResumed()
    }
}