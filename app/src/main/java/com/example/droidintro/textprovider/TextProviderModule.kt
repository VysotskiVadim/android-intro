package com.example.droidintro.textprovider

import dagger.Module
import dagger.Provides

@Module
class TextProviderModule {
    @Provides fun getTextProvider(implementation: TextProviderImplementation):TextProvider = implementation
}