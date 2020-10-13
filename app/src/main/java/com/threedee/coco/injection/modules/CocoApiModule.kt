package com.threedee.coco.injection.modules

import android.content.Context
import com.darkwater.kraken.injection.qualifiers.AppContext
import com.darkwater.kraken.injection.scopes.AppScope
import com.threedee.coco.R
import com.threedee.coco.service.CocoApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class CocoApiModule {

    @Provides
    @AppScope
    fun provideCocoApi(@AppContext context: Context, retrofit: Retrofit): CocoApi =
        CocoApi(
            apiKey = context.getString(R.string.coco_api_key),
            userAgent = context.getString(R.string.app_user_agent),
            retrofit = retrofit
        )
}