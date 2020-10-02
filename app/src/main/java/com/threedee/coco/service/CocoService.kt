package com.threedee.coco.service

import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.http.GET

class CocoApi(
    retrofit: Retrofit
) {
    private val service = retrofit.create(CocoService::class.java)

    fun getData(): Single<CocoResponse> {
        return Single.fromObservable(service.getData())
    }

    internal interface CocoService {
        @GET("data")
        fun getData(): Observable<CocoResponse>
    }
}
