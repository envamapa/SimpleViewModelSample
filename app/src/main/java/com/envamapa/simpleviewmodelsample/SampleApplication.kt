package com.envamapa.simpleviewmodelsample

import android.app.Application
import com.envamapa.simpleviewmodelsample.di.HasModule
import com.envamapa.simpleviewmodelsample.domain.Tracking
import com.envamapa.simpleviewmodelsample.domain.repository.ABCTestRepository
import com.envamapa.simpleviewmodelsample.domain.repository.RepositoryA
import com.envamapa.simpleviewmodelsample.domain.repository.RepositoryB
import com.envamapa.simpleviewmodelsample.domain.repository.RepositoryC
import com.envamapa.simpleviewmodelsample.domain.repository.RepositoryD
import com.envamapa.simpleviewmodelsample.domain.repository.RepositoryE
import com.envamapa.simpleviewmodelsample.domain.usecase.UseCaseA
import com.envamapa.simpleviewmodelsample.domain.usecase.UseCaseB
import com.envamapa.simpleviewmodelsample.domain.usecase.UseCaseC
import com.envamapa.simpleviewmodelsample.presentation.ViewModelA

class SampleApplication : Application(), HasModule {
    override fun provideViewModel(): ViewModelA =
        ViewModelA(
            useCaseA = provideUseCaseA(),
            useCaseB = provideUseCaseB(),
            useCaseC = provideUseCaseC(),
            tracking = provideTracking()
        )

    private fun provideTracking(): Tracking =
        Tracking()

    private fun provideUseCaseC(): UseCaseC =
        UseCaseC(
            repositoryB = provideRepositoryB(),
            repositoryC = provideRepositoryC(),
            repositoryE = provideRepositoryE(),
            abcTestRepository = provideAbcTestRepository()
        )

    private fun provideAbcTestRepository(): ABCTestRepository =
        ABCTestRepository()

    private fun provideRepositoryE(): RepositoryE =
        RepositoryE()

    private fun provideRepositoryC(): RepositoryC =
        RepositoryC()

    private fun provideRepositoryB(): RepositoryB =
        RepositoryB()

    private fun provideUseCaseB(): UseCaseB =
        UseCaseB(
            repositoryB = provideRepositoryB()
        )

    private fun provideUseCaseA(): UseCaseA =
        UseCaseA(
            repositoryA = provideRepositoryA(),
            repositoryD = provideRepositoryD(),
            abcTestRepository = provideAbcTestRepository()
        )

    private fun provideRepositoryD(): RepositoryD =
        RepositoryD(
            repositoryC = provideRepositoryC(),
            repositoryE = provideRepositoryE()
        )

    private fun provideRepositoryA(): RepositoryA =
        RepositoryA()
}
