package com.threedee.coco.ui.main

import com.darkwater.kraken.injection.scopes.ActivityScope
import javax.inject.Inject

@ActivityScope
class MainRouter
@Inject
constructor(

) : MainContract.Router {

    override fun cleanUp() {

    }
}