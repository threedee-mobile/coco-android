package com.threedee.coco.injection.modules

import android.app.Application
import android.content.Context
import com.darkwater.alfred.injection.qualifiers.AppContext
import com.darkwater.alfred.injection.scopes.AppScope
import dagger.Module
import dagger.Provides

@Module(
    includes = [
        ActivityBinderModule::class,
        FragmentBinderModule::class,
        CommonModule::class,
        NetworkModule::class,
        CocoApiModule::class
    ]
)
class ApplicationModule {

    @Provides
    @AppScope
    @AppContext
    fun provideAppContext(application: Application): Context = application
}