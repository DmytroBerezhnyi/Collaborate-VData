package com.example.dmytroberezhnyi_vdatatesttask.di

import android.content.Context
import com.example.dmytroberezhnyi_vdatatesttask.api.PictureService
import com.example.dmytroberezhnyi_vdatatesttask.data.local.AppDatabase
import com.example.dmytroberezhnyi_vdatatesttask.data.local.CollaboratorDao
import com.example.dmytroberezhnyi_vdatatesttask.data.local.CompanyCollaboratorDao
import com.example.dmytroberezhnyi_vdatatesttask.data.local.CompanyDao
import com.example.dmytroberezhnyi_vdatatesttask.data.repository.CompanyRepository
import com.example.dmytroberezhnyi_vdatatesttask.data.repository.PictureRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl("https://www.googleapis.com/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun providePictureService(retrofit: Retrofit): PictureService =
        retrofit.create(PictureService::class.java)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) =
        AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideCompanyDao(db: AppDatabase) = db.companyDao()

    @Singleton
    @Provides
    fun provideCompanyCollaboratorDao(db: AppDatabase) = db.companyCollaboratorDao()

    @Singleton
    @Provides
    fun provideCollaboratorDao(db: AppDatabase) = db.collaboratorDao()

    @Singleton
    @Provides
    fun providePictureRepository(pictureService: PictureService): PictureRepository {
        return PictureRepository(pictureService)
    }

    @Singleton
    @Provides
    fun provideCompanyRepository(
        localCompanyDataSource: CompanyDao,
        localCompanyCollabDataSource: CompanyCollaboratorDao,
        localCollaboratorDataSource: CollaboratorDao
    ): CompanyRepository {
        return CompanyRepository(
            localCompanyDataSource,
            localCompanyCollabDataSource,
            localCollaboratorDataSource
        )
    }
}