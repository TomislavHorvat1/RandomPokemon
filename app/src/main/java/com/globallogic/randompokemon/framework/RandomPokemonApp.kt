package com.globallogic.randompokemon.framework

import android.app.Application
import com.globallogic.randompokemon.BuildConfig
import com.globallogic.randompokemon.framework.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class RandomPokemonApp : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidLogger()
            androidContext(this@RandomPokemonApp)
            modules(appModule)
        }
    }
}