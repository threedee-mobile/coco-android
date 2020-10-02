package com.threedee.coco.service

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CocoResponse(
    val items: List<CocoItem>
)

@JsonClass(generateAdapter = true)
data class CocoItem(
    val name: String
)