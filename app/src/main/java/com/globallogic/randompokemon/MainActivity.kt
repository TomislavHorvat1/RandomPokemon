package com.globallogic.randompokemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Observer
import com.globallogic.core.domain.PokeIndex
import com.globallogic.randompokemon.ui.theme.RandomPokemonTheme
import com.globallogic.randompokemon.ui.viewmodel.PokemonCardViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel: PokemonCardViewModel by viewModel()

    private val isPokeIndexCachedObserver = Observer<Boolean> {
        if (!it) viewModel.getPokeIndex(false)
    }

    private val pokeIndexObserver = Observer<PokeIndex?> {
        it?.run { viewModel.getRandomPokemon() }
    }

    private val failedFetchPokemonObserver = Observer<Int?> {
        it?.run { viewModel.getRandomPokemon(false) }
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
                    Greeting(name = "Android")
                }
            }
        }

        viewModel.getPokeIndex()
    }

    private fun setObservers() {
        viewModel.isPokeIndexCached.observe(this, isPokeIndexCachedObserver)
        viewModel.pokeIndex.observe(this, pokeIndexObserver)
        viewModel.failedFetchPokemonId.observe(this, failedFetchPokemonObserver)
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RandomPokemonTheme {
        Greeting("Android")
    }
}