package com.globallogic.randompokemon.framework.manager

import com.globallogic.core.usecase.UseCase

class UseCaseManager {
    // TODO run observable
    // TODO response observable
    // TODO error observable

    fun <Q : UseCase.Request, R : UseCase.Response> execute(
        useCase: UseCase<Q, R>,
        values: Q,
        callback: UseCase.Callback<R>,
    ) {
        useCase.request = values
        useCase.callback = UiCallbackWrapper(callback, this)

        // TODO useCase.run through coroutines
    }

    private fun <R : UseCase.Response> notifyResponse(
        response: R,
        callback: UseCase.Callback<R>,
    ) {
        // TODO post to response observable
    }

    private fun <R : UseCase.Response> notifyError(
        callback: UseCase.Callback<R>,
        t: Throwable,
    ) {
        // TODO post to error observable
    }

    private class UiCallbackWrapper<R : UseCase.Response>(
        private val callback: UseCase.Callback<R>,
        private val manager: UseCaseManager
    ) : UseCase.Callback<R> {
        override fun onSuccess(response: R) {
            manager.notifyResponse(
                response = response,
                callback = callback,
            )
        }

        override fun onError(t: Throwable) {
            manager.notifyError(
                callback = callback,
                t = t,
            )
        }
    }
}