package com.globallogic.randompokemon.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.globallogic.core.domain.Move
import com.globallogic.core.domain.Pokemon
import com.globallogic.core.domain.Stat
import com.globallogic.randompokemon.R
import com.globallogic.randompokemon.ui.theme.RandomPokemonTheme
import com.globallogic.randompokemon.ui.viewmodel.PokemonViewModel
import timber.log.Timber
import java.util.*

@Composable
fun MainScreen(
    pokemonViewModel: PokemonViewModel,
) {
    val pokemon: Pokemon? by pokemonViewModel.pokemon.observeAsState()

    RandomPokemonTheme {
        RootLayout(
            name = pokemon?.name?.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.getDefault()
                ) else it.toString()
            } ?: "Loading...",
            frontImage = pokemon?.sprites?.frontDefault ?: "",
            backImage = pokemon?.sprites?.backDefault ?: "",
            stats = pokemon?.stats ?: emptyList(),
            moves = pokemon?.moves ?: emptyList(),
            onRefreshClicked = { pokemonViewModel.getRandomPokemon() }
        )
    }
}

@Composable
fun RootLayout(
    name: String,
    frontImage: String,
    backImage: String,
    stats: List<Stat>,
    moves: List<Move>,
    onRefreshClicked: (() -> Unit),
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onRefreshClicked() }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_baseline_refresh_24),
                    contentDescription = null,
                )
            }
        }
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        start = 8.dp,
                        top = 8.dp,
                        end = 8.dp,
                        bottom = 0.dp,
                    )
            ) {
                Pokedex(
                    name = name,
                    frontImage = frontImage,
                    backImage = backImage,
                    stats = stats
                )
                PokemonMoves(moves = moves)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RootLayoutPreview() {
    RandomPokemonTheme {
        RootLayout(
            name = "Test",
            frontImage = "",
            backImage = "",
            stats = emptyList(),
            moves = emptyList(),
            onRefreshClicked = {},
        )
    }
}