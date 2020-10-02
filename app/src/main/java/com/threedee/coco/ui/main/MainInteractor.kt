package com.threedee.coco.ui.main

import com.darkwater.alfred.injection.scopes.ActivityScope
import com.threedee.coco.service.CocoApi
import com.threedee.coco.service.CocoItem
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

    override fun getData(): Single<List<CocoItem>> {
        cocoApi?.let { cocoApi ->
            return cocoApi.getData()
                .map { it.items }
        }
        return Single.error(IllegalStateException())
    }
}