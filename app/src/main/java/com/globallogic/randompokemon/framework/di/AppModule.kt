package com.globallogic.randompokemon.framework.di

import com.globallogic.core.data.PokemonCacheSource
import com.globallogic.core.data.PokemonDataRepository
import com.globallogic.core.data.PokemonDataSource
import com.globallogic.core.usecase.GetPokemon
import com.globallogic.randompokemon.framework.datasource.LocalDataSourceImpl
import com.globallogic.randompokemon.framework.datasource.RemoteDataSourceImpl
import com.globallogic.randompokemon.ui.viewmodel.PokemonCardViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<PokemonCacheSource> { LocalDataSourceImpl() }
    single<PokemonDataSource> { RemoteDataSourceImpl() }
    single { PokemonDataRepository.getInstance(get(), get()) }
    single { GetPokemon(get()) }

    viewModel { PokemonCardViewModel(get()) }

}