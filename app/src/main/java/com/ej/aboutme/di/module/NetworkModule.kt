package com.ej.aboutme.di.module

import com.ej.aboutme.api.AboutMeApi
import com.ej.aboutme.api.AboutMeFetchr
import com.ej.aboutme.util.ServerInfo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

//    @Singleton
//    @Provides
//    fun provideAboutMeFetchr() : AboutMeFetchr{
//        return AboutMeFetchr()
//    }

    @Singleton
    @Provides
    fun provideAboutMeApi() : AboutMeApi{
        val retrofit = Retrofit.Builder()
            .baseUrl(ServerInfo.SERVER_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        val aboutMeApi = retrofit.create(AboutMeApi::class.java)
        return aboutMeApi
    }
}