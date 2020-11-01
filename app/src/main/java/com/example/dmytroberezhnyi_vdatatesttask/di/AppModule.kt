package com.example.dmytroberezhnyi_vdatatesttask.di

import android.content.Context
import com.example.dmytroberezhnyi_vdatatesttask.data.local.AppDatabase
import com.example.dmytroberezhnyi_vdatatesttask.data.local.CollaboratorDao
import com.example.dmytroberezhnyi_vdatatesttask.data.local.CompanyCollaboratorDao
import com.example.dmytroberezhnyi_vdatatesttask.data.local.CompanyDao
import com.example.dmytroberezhnyi_vdatatesttask.data.repository.CompanyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

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
    fun provideRepository(
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