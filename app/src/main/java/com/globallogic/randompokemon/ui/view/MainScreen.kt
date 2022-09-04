package com.globallogic.randompokemon.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.globallogic.core.domain.Pokemon
import com.globallogic.core.domain.Stat
import com.globallogic.randompokemon.R
import com.globallogic.randompokemon.ui.theme.RandomPokemonTheme
import com.globallogic.randompokemon.ui.viewmodel.PokemonViewModel

/**
 * Holds the root layout of the application UI.
 *
 * @param pokemonViewModel a view model used with the UI
 */
@Composable
fun MainScreen(
    pokemonViewModel: PokemonViewModel,
) {
    val pokemon: Pokemon? by pokemonViewModel.pokemon.observeAsState()

    RandomPokemonTheme {
        RootLayout(
            name = pokemon?.name ?: stringResource(id = R.string.loading_lbl),
            frontImage = pokemon?.frontImageUrl ?: stringResource(id = R.string.empty_string),
            backImage = pokemon?.backImageUrl ?: stringResource(id = R.string.empty_string),
            stats = pokemon?.stats ?: emptyList(),
            moves = pokemon?.moves ?: emptyList(),
            onRefreshClicked = { pokemonViewModel.getRandomPokemon() }
        )
    }
}

/**
 * Holds the Pokedex and Moves list
 */
@Composable
fun RootLayout(
    name: String,
    frontImage: String,
    backImage: String,
    stats: List<Stat>,
    moves: List<String>,
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
        },
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                },
                navigationIcon = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Filled.Menu, stringResource(id = R.string.empty_string))
                    }
                },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = Color.White,
                elevation = 12.dp
            )
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