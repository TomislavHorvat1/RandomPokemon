package com.globallogic.randompokemon.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ErrorResult
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.globallogic.core.domain.Pokemon
import com.globallogic.randompokemon.ui.theme.RandomPokemonTheme
import com.globallogic.randompokemon.ui.viewmodel.PokemonScreenViewModel
import com.globallogic.randompokemon.ui.viewmodel.PokemonViewModel
import timber.log.Timber
import java.util.*

@Composable
fun MainScreen(
    pokemonViewModel: PokemonViewModel,
    pokemonScreenViewModel: PokemonScreenViewModel,
) {
    val pokemon: Pokemon? by pokemonViewModel.pokemon.observeAsState()
    val isMoveListExpanded: Boolean? by pokemonScreenViewModel.isMoveListExpanded.observeAsState()

    RandomPokemonTheme {
        RooTLayout(
            pokemonName = pokemon?.name ?: "Loading...",
            pokemonImageFront = pokemon?.sprites?.frontDefault ?: "",
            pokemonImageBack = pokemon?.sprites?.backDefault ?: ""
        )
    }
}

@Composable
fun RooTLayout(
    pokemonName: String,
    pokemonImageFront: String,
    pokemonImageBack: String,
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            Card(
                backgroundColor = MaterialTheme.colors.surface,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                Column(
                    modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth()
                ) {
                    PokemonName(
                        name = pokemonName.replaceFirstChar {
                            if (it.isLowerCase()) it.titlecase(
                                Locale.getDefault()
                            ) else it.toString()
                        },
                        modifier = Modifier
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(144.dp)
                    ) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(pokemonImageFront)
                                .crossfade(true)
                                .build(),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth(0.5f)
                                .padding(8.dp),
                        )
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(pokemonImageBack)
                                .crossfade(true)
                                .build(),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth(1f)
                                .padding(8.dp),
                        )
                    }
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
            pokemonName = "Test",
            pokemonImageFront = "",
            pokemonImageBack = ""
        )
    }
}