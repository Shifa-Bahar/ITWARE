package com.example.myapplication.di

import com.example.myapplication.api.Api
import com.example.myapplication.api.Api2
import com.example.myapplication.repository.UserRepository
import com.example.myapplication.repository.UserRepository2
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import retrofit2.Retrofit

@InstallIn(ActivityRetainedComponent::class)
@Module
object RepositoryModule {
    @Provides
    fun getApi(retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }
    @Provides
    fun provideUserRepo(api: Api):UserRepository{
        return UserRepository(api)
    }
    @Provides
    fun getApi2(retrofit: Retrofit): Api2 {
        return retrofit.create(Api2::class.java)
    }
    @Provides
    fun provideUserRepo2(api2: Api2):UserRepository2{
        return UserRepository2(api2)
    }

}