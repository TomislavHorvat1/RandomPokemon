package com.globallogic.core.usecase

abstract class UseCase<Q : UseCase.Request, P : UseCase.Response> {

    var request: Q? = null
    var callback: Callback<P>? = null

    internal fun run() {
        execute(request)
    }

    protected abstract fun execute(request: Q?)

    interface Request

    interface Response

    interface Callback<R> {
        fun onSuccess(response: R)
        fun onError(t: Throwable)
    }
}