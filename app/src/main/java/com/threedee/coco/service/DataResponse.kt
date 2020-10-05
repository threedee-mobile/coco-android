package com.threedee.coco.service

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DataResponse(
    val data: List<Data>,
)

@JsonClass(generateAdapter = true)
data class Data(
    val month: String,
    val monthIndex: Int,
    val cells: List<Cell>
)

@JsonClass(generateAdapter = true)
data class Cell(
    val id: String,
    val lat: Float,
    val lon: Float,
    val ff_co2: Float,
    val ib_co2: Float
)