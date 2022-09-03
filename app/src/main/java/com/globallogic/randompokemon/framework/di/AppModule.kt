package com.globallogic.randompokemon.framework.di

import com.globallogic.core.data.PokemonDataRepository
import com.globallogic.core.data.PokemonDataSource
import com.globallogic.core.usecase.CachePokeIndex
import com.globallogic.core.usecase.CachePokemon
import com.globallogic.core.usecase.GetPokeIndex
import com.globallogic.core.usecase.GetPokemon
import com.globallogic.randompokemon.adapter.PokeIndexJasonAdapter
import com.globallogic.randompokemon.adapter.PokemonJsonAdapter
import com.globallogic.randompokemon.framework.PrefsCache
import com.globallogic.randompokemon.framework.datasource.LocalDataSourceImpl
import com.globallogic.randompokemon.framework.datasource.RemoteDataSourceImpl
import com.globallogic.randompokemon.framework.manager.PrefsManager
import com.globallogic.randompokemon.framework.provider.*
import com.globallogic.randompokemon.ui.viewmodel.PokemonScreenViewModel
import com.globallogic.randompokemon.ui.viewmodel.PokemonViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { RemoteDataSourceImpl(api = get()) }
    single {
        LocalDataSourceImpl(
            prefsCache = get(),
            pokemonDao = get(),
            pokemonJsonAdapter = get(),
        )
    }
    single<PokemonDataSource> {
        PokemonDataRepository.getInstance(
            localDataSource = get<LocalDataSourceImpl>(),
            remoteDataSource = get<RemoteDataSourceImpl>(),
        )
    }

    single { GetPokemon(dataSource = get()) }
    single { CachePokemon(dataSource = get()) }
    single { GetPokeIndex(dataSource = get()) }
    single { CachePokeIndex(dataSource = get()) }

    factory { provideOkHttp() }
    factory { provideMoshi() }
    factory {
        provideRetrofit(
            okHttpClient = get(),
            moshi = get(),
        )
    }
    single { providePokemonApi(retrofit = get()) }

    single { PokeIndexJasonAdapter(moshi = get()) }
    single { PokemonJsonAdapter(moshi = get()) }

    single<PrefsCache> {
        PrefsManager(
            context = get(),
            pokeIndexJasonAdapter = get(),
        )
    }

    single { provideDatabase(get()) }
    single { provideDao(get()) }

    viewModel {
        PokemonViewModel(
            getPokemon = get(),
            cachePokemon = get(),
            getPokeIndex = get(),
            cachePokeIndex = get(),
        )
    }

    viewModel {
        PokemonScreenViewModel()
    }
}