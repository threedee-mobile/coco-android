package com.threedee.coco.injection.modules

import android.content.Context
import com.darkwater.alfred.injection.qualifiers.AppContext
import com.darkwater.alfred.injection.scopes.AppScope
import com.readystatesoftware.chuck.ChuckInterceptor
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @Provides
    @AppScope
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://google.com/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    @Provides
    @AppScope
    fun provideOkHttpClient(interceptors: MutableSet<Interceptor>): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
        interceptors.forEach {
            okHttpClient.addInterceptor(it)
        }
        return okHttpClient.build()
    }

    @Provides
    @AppScope
    fun provideChuckInterceptor(@AppContext context: Context): ChuckInterceptor =
        ChuckInterceptor(context)

    @Provides
    @AppScope
    @IntoSet
    fun intoSetChuckInterceptor(chuckInterceptor: ChuckInterceptor): Interceptor = chuckInterceptor
}