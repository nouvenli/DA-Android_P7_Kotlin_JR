package com.openclassrooms.arista.di

import com.openclassrooms.arista.data.repository.ExerciseRepository
import com.openclassrooms.arista.data.repository.SleepRepository
import com.openclassrooms.arista.data.repository.UserRepository
import com.openclassrooms.arista.domain.repository.ExerciseRepositoryInterface
import com.openclassrooms.arista.domain.repository.SleepRepositoryInterface
import com.openclassrooms.arista.domain.repository.UserRepositoryInterface
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindUserRepository(
        userRepository: UserRepository
    ): UserRepositoryInterface

    @Binds
    @Singleton
    abstract fun bindSleepRepository(
        sleepRepository: SleepRepository
    ): SleepRepositoryInterface

    @Binds
    @Singleton
    abstract fun bindExerciseRepository(
        exerciseRepository: ExerciseRepository
    ): ExerciseRepositoryInterface
}