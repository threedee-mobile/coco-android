package com.threedee.coco.injection.modules

import android.content.Context
import com.darkwater.alfred.injection.qualifiers.AppContext
import com.darkwater.alfred.injection.scopes.AppScope
import com.darkwater.alfred.providers.DimensionProvider
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides

@Module
class CommonModule {

    @Provides
    @AppScope
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    @AppScope
    fun provideDimensionProvider(@AppContext context: Context): DimensionProvider =
        DimensionProvider(context)
}