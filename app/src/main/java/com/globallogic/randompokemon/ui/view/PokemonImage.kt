package com.globallogic.randompokemon.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ErrorResult
import coil.request.ImageRequest
import coil.request.SuccessResult

@Composable
fun PokemonImage(
    frontImage: String,
    backImage: String,
    onImageLoaded: (Boolean) -> Unit,
    showLoading: Boolean,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(144.dp)
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .fillMaxWidth(0.5f)
                .padding(4.dp),
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(frontImage)
                    .crossfade(true)
                    .listener(object : ImageRequest.Listener {
                        override fun onStart(request: ImageRequest) {
                            super.onStart(request)
                            onImageLoaded(false)
                        }

                        override fun onSuccess(request: ImageRequest, result: SuccessResult) {
                            super.onSuccess(request, result)
                            onImageLoaded(true)
                        }

                        override fun onError(request: ImageRequest, result: ErrorResult) {
                            super.onError(request, result)
                            onImageLoaded(false)
                        }
                    })
                    .build(),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
            )
            if (showLoading)
                Box(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .fillMaxWidth(0.5f)
                        .padding(4.dp),
                ) { CircularProgressIndicator(color = Color.LightGray) }
        }
        Box(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .fillMaxWidth(1f)
                .padding(4.dp),
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(backImage)
                    .crossfade(true)
                    .build(),
                onState = {},
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
            if (showLoading) Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth(0.5f)
                    .padding(4.dp),
            ) { CircularProgressIndicator(color = Color.LightGray) }
        }
    }
}