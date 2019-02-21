package com.example.droidintro.ui


import dagger.Subcomponent
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap
import dagger.Binds
import dagger.Module

@Subcomponent
interface MainActivityComponent : AndroidInjector<MainActivity> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<MainActivity>()
}

@Module(subcomponents = [MainActivityComponent::class])
internal abstract class MainActivityModule {
}