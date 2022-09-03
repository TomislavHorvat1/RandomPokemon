package com.globallogic.randompokemon.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.globallogic.core.domain.Pokemon
import com.globallogic.randompokemon.ui.theme.RandomPokemonTheme
import com.globallogic.randompokemon.ui.viewmodel.PokemonScreenViewModel
import com.globallogic.randompokemon.ui.viewmodel.PokemonViewModel
import timber.log.Timber

@Composable
fun MainScreen(
    pokemonViewModel: PokemonViewModel,
    pokemonScreenViewModel: PokemonScreenViewModel,
) {
    val pokemon: Pokemon? by pokemonViewModel.pokemon.observeAsState()
    val isMoveListExpanded: Boolean? by pokemonScreenViewModel.isMoveListExpanded.observeAsState()

    RandomPokemonTheme {
        RooTLayout(
            pokemonImageFront = pokemon?.sprites?.frontDefault ?: "",
            pokemonImageBack = pokemon?.sprites?.backDefault ?: ""
        )
    }
}

@Composable
fun RooTLayout(
    pokemonImageFront: String,
    pokemonImageBack: String,
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                Card(
                    backgroundColor = MaterialTheme.colors.surface,
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .fillMaxHeight(0.3f)
                        .padding(start = 16.dp, top = 16.dp, end = 8.dp, bottom = 72.dp)
                ) {

                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(pokemonImageFront)
                            .crossfade(true)
                            .build(),
                        contentDescription = null,
                        onState = {
                            Timber.tag("COIL").d("$it")
                        },
                        modifier = Modifier.size(96.dp)
                    )
                }
                Card(
                    backgroundColor = MaterialTheme.colors.surface,
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .fillMaxHeight(0.3f)
                        .padding(start = 8.dp, top = 16.dp, end = 16.dp, bottom = 72.dp)
                ) {
                    AsyncImage(model = pokemonImageBack, contentDescription = null)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RootLayoutPreview() {
    RandomPokemonTheme {
        RooTLayout(
            pokemonImageFront = "",
            pokemonImageBack = ""
        )
    }
}