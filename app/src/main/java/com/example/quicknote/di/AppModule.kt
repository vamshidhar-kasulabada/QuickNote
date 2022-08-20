package com.example.quicknote.di

import android.content.Context
import androidx.room.Room
import com.example.quicknote.db.NotesDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesNotesDataBase(@ApplicationContext context: Context): NotesDataBase{
        return Room.databaseBuilder(context, NotesDataBase::class.java,"AppDatabase").build()
    }
}