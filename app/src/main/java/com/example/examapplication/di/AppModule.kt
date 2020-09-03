package com.example.examapplication.di

import com.example.examapplication.repo.PhotoRepository
import com.example.examapplication.repo.PhotoRepositoryImpl
import com.example.examapplication.viewmodel.PhotoViewModel
import com.example.examapplication.rx.ApplicationSchedulerProvider
import com.example.examapplication.rx.SchedulerProvider
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val appModule = module {
    viewModel {
        PhotoViewModel(
            get(),
            get()
        )
    }

    single<PhotoRepository>{
        PhotoRepositoryImpl(
            get()
        )
    }

    single<SchedulerProvider> { ApplicationSchedulerProvider() }
}

val allAppModule = listOf(appModule, remoteDatasourceModule)