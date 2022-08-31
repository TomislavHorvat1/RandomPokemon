package com.globallogic.randompokemon.framework.di

import com.globallogic.core.data.PokemonDataRepository
import com.globallogic.core.data.PokemonDataSource
import com.globallogic.core.usecase.GetPokeIndex
import com.globallogic.core.usecase.GetPokemon
import com.globallogic.randompokemon.framework.datasource.RemoteDataSourceImpl
import com.globallogic.randompokemon.framework.provider.provideMoshi
import com.globallogic.randompokemon.framework.provider.provideOkHttp
import com.globallogic.randompokemon.framework.provider.providePokemonApi
import com.globallogic.randompokemon.framework.provider.provideRetrofit
import com.globallogic.randompokemon.ui.viewmodel.PokemonCardViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<PokemonDataSource> { RemoteDataSourceImpl(api = get()) }
    single {
        PokemonDataRepository.getInstance(
            localDataSource = get(),
            remoteDataSource = get(),
        )
    }

    single { GetPokemon(dataSource = get()) }
    single { GetPokeIndex(dataSource = get()) }

    factory { provideOkHttp() }
    factory { provideMoshi() }
    factory {
        provideRetrofit(
            okHttpClient = get(),
            moshi = get(),
        )
    }
    single { providePokemonApi(retrofit = get()) }

    viewModel {
        PokemonCardViewModel(
            getPokemon = get(),
            getPokeIndex = get(),
        )
    }
}