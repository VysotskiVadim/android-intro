package com.example.droidintro.ui;

import android.app.Activity;
import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;

@Module
public abstract class MainActivityBuilder {
    @Binds
    @IntoMap
    @ClassKey(MainActivity.class)
    abstract AndroidInjector.Factory<?> bindMainActivity(MainActivityComponent.Builder builder);
}
