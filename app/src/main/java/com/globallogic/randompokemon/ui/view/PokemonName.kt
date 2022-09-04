package com.globallogic.randompokemon.ui.view

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Displays the pokemon name
 */
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
