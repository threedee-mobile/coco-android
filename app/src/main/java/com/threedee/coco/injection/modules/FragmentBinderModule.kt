package com.threedee.coco.injection.modules

import androidx.fragment.app.Fragment
import com.darkwater.alfred.injection.scopes.FragmentScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBinderModule {
    @ContributesAndroidInjector(
        modules = [
        ]
    )
    @FragmentScope
    abstract fun contributeFragment(): Fragment

}