package com.threedee.coco.injection

import android.app.Application
import com.darkwater.kraken.injection.scopes.AppScope
import com.threedee.coco.injection.modules.ApplicationModule
import com.threedee.coco.CocoApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule

@AppScope
@Component(
    modules = [
        AndroidInjectionModule::class,
        ApplicationModule::class
    ]
)
interface ApplicationComponent {
    fun inject(application: CocoApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }
}