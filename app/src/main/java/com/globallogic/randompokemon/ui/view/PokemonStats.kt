package com.globallogic.randompokemon.ui.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.globallogic.core.domain.Stat

/**
 * Displays the stats grid
 */
@Composable
fun PokemonStats(stats: List<Stat>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2)
    ) {
        items(stats.size) { i ->
            StatCell(
                name = stats[i].name,
                value = stats[i].value,
            )
        }
    }
}

/**
 * A single stat cell with a stat name and value
 */
@Composable
fun StatCell(name: String, value: Int) {
    Box(modifier = Modifier.padding(4.dp)) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = 0.dp,
            backgroundColor = Color.Gray
        ) {
            Column(
                modifier = Modifier.padding(
                    horizontal = 8.dp,
                    vertical = 4.dp,
                )
            ) {
                Text(
                    text = name,
                    color = Color.LightGray,
                    fontSize = 11.sp,
                )
                Text(
                    text = value.toString(),
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}