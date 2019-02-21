package com.example.droidintro

import com.example.droidintro.ui.MainActivityBuilder
import com.example.droidintro.ui.MainActivityComponent
import com.example.droidintro.ui.MainActivityModule
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Module
class AppModule(val app: App) {
    @Provides
    @Singleton
    fun provideApp() = app
}

@Singleton
@Component(modules = [
    AppModule::class,
    MainActivityBuilder::class,
    MainActivityModule::class,
    AndroidSupportInjectionModule::class
])
interface AppComponent {
    fun inject(app: App)
}
