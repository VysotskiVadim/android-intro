package com.example.droidintro.countwordusecase

import com.example.droidintro.textprovider.TextProvider
import dagger.Module
import dagger.Provides

@Module
class TextProviderTestModule(val mock:TextProvider) {
    @Provides fun getMocked():TextProvider = mock
}