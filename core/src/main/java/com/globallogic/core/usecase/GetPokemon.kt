package com.globallogic.core.usecase

import com.globallogic.core.data.PokemonDataSource
import com.globallogic.core.domain.Pokemon

class GetPokemon(private val dataSource: PokemonDataSource) :
    UseCase<GetPokemon.Request, GetPokemon.Response>() {

    override fun execute(request: Request?) {
        dataSource.getPokemon(
            pokemonId = request?.pokemonId ?: -1,
            callback = object : PokemonDataSource.LoadPokemonCallback {
                override fun onPokemonLoaded(pokemon: Pokemon) {
                    callback?.onSuccess(response = Response(pokemon = pokemon))
                }

                override fun onError(t: Throwable) {
                    callback?.onError(t = t)
                }
            }
        )
    }

    class Request(val pokemonId: Int) : UseCase.Request
    class Response(val pokemon: Pokemon) : UseCase.Response
}