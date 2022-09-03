package com.globallogic.randompokemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.Observer
import com.globallogic.core.domain.PokeIndex
import com.globallogic.randompokemon.ui.theme.RandomPokemonTheme
import com.globallogic.randompokemon.ui.view.MainScreen
import com.globallogic.randompokemon.ui.viewmodel.PokemonScreenViewModel
import com.globallogic.randompokemon.ui.viewmodel.PokemonViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class MainActivity : ComponentActivity() {

    private val pokemonViewModel: PokemonViewModel by viewModel()
    private val pokemonScreenViewModel: PokemonScreenViewModel by viewModel()

    private val isPokeIndexCachedObserver = Observer<Boolean> {
        if (!it) pokemonViewModel.getPokeIndex(false)
    }

    private val pokeIndexObserver = Observer<PokeIndex?> {
        it?.run { pokemonViewModel.getRandomPokemon() }
    }

    private val failedFetchPokemonObserver = Observer<Int?> {
        it?.run { pokemonViewModel.getRandomPokemon(false) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setObservers()

        setContent {
            RandomPokemonTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen(
                        pokemonViewModel = pokemonViewModel,
                        pokemonScreenViewModel = pokemonScreenViewModel,
                    )
                }
            }
        }

        pokemonViewModel.getPokeIndex()
    }

    private fun setObservers() {
        pokemonViewModel.isPokeIndexCached.observe(this, isPokeIndexCachedObserver)
        pokemonViewModel.pokeIndex.observe(this, pokeIndexObserver)
        pokemonViewModel.failedFetchPokemonId.observe(this, failedFetchPokemonObserver)
    }
}