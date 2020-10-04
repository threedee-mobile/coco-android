package com.threedee.coco.service

import androidx.annotation.IntRange
import com.threedee.coco.service.request.ApiHeaders
import com.threedee.coco.service.request.QueryParams
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

class CocoApi(
    val apiKey: String,
    val userAgent: String,
    retrofit: Retrofit
) {
    private val service = retrofit.create(CocoService::class.java)

    fun getData(
        @IntRange(from = 2016, to = 2018) year: Int,
        month: String,
        @IntRange(from = 100, to = 2000) radius: Int,
        latitude: Float,
        longitude: Float
    ): Single<DataResponse> {
        return Single.fromObservable(
            service.getData(
                apiKey = apiKey,
                userAgent = userAgent,
                year = year,
                month = month,
                radius = radius,
                latitude = latitude,
                longitude = longitude
            )
        )
    }

    internal interface CocoService {
        @GET("data")
        fun getData(
            @Header(ApiHeaders.API_KEY) apiKey: String,
            @Header(ApiHeaders.ACCEPT) accept: String = "application/json",
            @Header(ApiHeaders.CONTENT_TYPE) contentType: String = "application/json",
            @Header(ApiHeaders.USER_AGENT) userAgent: String,
            @Query(QueryParams.YEAR) year: Int,
            @Query(QueryParams.MONTH) month: String,
            @Query(QueryParams.RADIUS) radius: Int,
            @Query(QueryParams.LATITUDE) latitude: Float,
            @Query(QueryParams.LONGITUDE) longitude: Float
        ): Observable<DataResponse>
    }
}
