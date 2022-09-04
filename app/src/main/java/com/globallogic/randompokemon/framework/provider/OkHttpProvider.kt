package com.globallogic.randompokemon.framework.provider

import okhttp3.OkHttpClient

/**
 * Provides the [OkHttpClient] instance
 */
fun provideOkHttp(): OkHttpClient = OkHttpClient().newBuilder().build()
