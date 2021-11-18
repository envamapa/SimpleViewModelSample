package com.envamapa.simpleviewmodelsample.di

import com.envamapa.simpleviewmodelsample.presentation.ViewModelA

interface HasModule {
    fun provideViewModel(): ViewModelA
}
