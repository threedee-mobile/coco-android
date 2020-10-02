package com.threedee.coco.ui.main.injection.modules

import android.app.Activity
import com.darkwater.alfred.injection.scopes.ActivityScope
import com.threedee.coco.ui.main.*
import dagger.Binds
import dagger.Module

@Module(
    includes = [

    ]
)
abstract class MainActivityModule {

    @Binds
    @ActivityScope
    abstract fun bindActivity(activity: MainActivity): Activity

    @Binds
    @ActivityScope
    abstract fun bindView(activity: MainActivity): MainContract.View

    @Binds
    @ActivityScope
    abstract fun bindInteractor(interactor: MainInteractor): MainContract.Interactor

    @Binds
    @ActivityScope
    abstract fun bindPresenter(presenter: MainPresenter): MainContract.Presenter

    @Binds
    @ActivityScope
    abstract fun bindRouter(router: MainRouter): MainContract.Router
}