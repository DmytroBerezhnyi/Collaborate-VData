package com.example.dmytroberezhnyi_vdatatesttask.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.dmytroberezhnyi_vdatatesttask.data.entity.Collaborator
import com.example.dmytroberezhnyi_vdatatesttask.data.entity.Company
import com.example.dmytroberezhnyi_vdatatesttask.data.entity.CompanyCollaboratorContract

@Database(
    entities = [
        Collaborator::class,
        Company::class,
        CompanyCollaboratorContract::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun collaboratorDao(): CollaboratorDao

    abstract fun companyCollaboratorDao(): CompanyCollaboratorDao

    abstract fun companyDao(): CompanyDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, "database")
                .fallbackToDestructiveMigration()
                .build()
    }
}