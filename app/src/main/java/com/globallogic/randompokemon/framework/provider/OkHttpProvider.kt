package com.globallogic.randompokemon.framework.provider

import okhttp3.OkHttpClient

fun provideOkHttp(): OkHttpClient = OkHttpClient().newBuilder().build()
