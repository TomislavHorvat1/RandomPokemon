package com.globallogic.randompokemon.data.api

abstract class ApiDto<out D> {
    abstract fun toObject(): D
}