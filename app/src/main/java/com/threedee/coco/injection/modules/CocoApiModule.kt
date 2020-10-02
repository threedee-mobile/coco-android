package com.threedee.coco.injection.modules

import com.darkwater.alfred.injection.scopes.AppScope
import com.threedee.coco.service.CocoApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class CocoApiModule {

    @Provides
    @AppScope
    fun provideCocoApi(retrofit: Retrofit): CocoApi = CocoApi(retrofit)
}