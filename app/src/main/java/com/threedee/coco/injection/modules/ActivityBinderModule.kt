package com.threedee.coco.injection.modules

import com.darkwater.kraken.injection.scopes.ActivityScope
import com.threedee.coco.ui.main.MainActivity
import com.threedee.coco.ui.main.injection.modules.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBinderModule {
    @ContributesAndroidInjector(
        modules = [
            MainActivityModule::class
        ]
    )
    @ActivityScope
    abstract fun contributeMainActivity(): MainActivity
}