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

/**
 * A Dagger Hilt module responsible for providing repository dependencies.
 *
 * This abstract class uses the [Binds] annotation to bind implementation classes
 * (from the data layer) to their corresponding repository interfaces (in the domain layer).
 * This allows the application to depend on abstractions rather than concrete implementations,
 * facilitating testing and clean architecture.
 *
 * It is installed in the [SingletonComponent], ensuring that the repository instances
 * are created once and live for the entire lifecycle of the application.
 */
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