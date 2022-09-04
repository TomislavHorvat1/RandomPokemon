package com.globallogic.randompokemon.ui.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.globallogic.randompokemon.R

/**
 * Displays a list title and a column of cards with move names
 */
@Composable
fun PokemonMoves(
    moves: List<String>,
) {
    Column {
        TitleItem()
        LazyColumn {
            items(
                count = moves.size,
            ) {
                MoveItem(moveName = moves[it])
            }
        }
    }
}

/**
 * Displays the list title
 */
@Composable
fun TitleItem() {
    Text(
        text = stringResource(id = R.string.moves_lbl),
        color = Color.DarkGray,
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        textAlign = TextAlign.Start,
    )
}

/**
 * A card item with a text field
 */
@Composable
fun MoveItem(moveName: String) {
    Box(
        modifier = Modifier
            .padding(4.dp)
    ) {
        Card(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = moveName,
                color = Color.DarkGray,
                fontSize = 16.sp,
                fontStyle = FontStyle.Italic,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 8.dp),
                textAlign = TextAlign.Start,
            )
        }
    }
}
