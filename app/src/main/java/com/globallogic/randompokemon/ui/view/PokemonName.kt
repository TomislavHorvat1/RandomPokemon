package com.globallogic.randompokemon.ui.view

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.globallogic.randompokemon.ui.theme.RandomPokemonTheme

@Composable
fun PokemonName(
    name: String,
    modifier: Modifier
) {
    Text(
        text = name,
        modifier = modifier,
        style = MaterialTheme.typography.h5
    )
}

@Preview(showBackground = true)
@Composable
fun PokemonNamePreview() {
    RandomPokemonTheme {
        PokemonName(name = "Test name", modifier = Modifier.padding(8.dp))
    }
}