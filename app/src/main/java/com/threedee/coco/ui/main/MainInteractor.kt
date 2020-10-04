package com.threedee.coco.ui.main

import com.darkwater.alfred.injection.scopes.ActivityScope
import com.threedee.coco.service.Cell
import com.threedee.coco.service.CocoApi
import io.reactivex.Single
import javax.inject.Inject

@ActivityScope
class MainInteractor
@Inject
constructor(
    private var cocoApi: CocoApi?
) : MainContract.Interactor {

    override fun cleanUp() {
        cocoApi = null
    }

    override fun getData(): Single<List<Cell>> {
        cocoApi?.let { cocoApi ->
            return cocoApi.getData(
                year = 2018,
                month = "january",
                radius = 200,
                latitude = 43.65f,
                longitude = -79.38f
            ).map { it.data }
        }
        return Single.error(IllegalStateException())
    }
}