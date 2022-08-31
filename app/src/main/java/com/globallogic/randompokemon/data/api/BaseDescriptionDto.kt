package com.globallogic.randompokemon.data.api

import com.globallogic.core.domain.BaseDescription
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BaseDescriptionDto(
    val name: String,
    val url: String,
) : ApiDto<BaseDescription>() {
    override fun toObject() = BaseDescription(
        name = name,
        url = url,
    )
}