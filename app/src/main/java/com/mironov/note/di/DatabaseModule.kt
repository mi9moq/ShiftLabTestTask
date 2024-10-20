package com.mironov.note.di

import android.content.Context
import androidx.room.Room
import com.mironov.note.data.database.NoteDao
import com.mironov.note.data.database.NoteDatabase
import dagger.Module
import dagger.Provides

@Module
object DatabaseModule {

    private const val DB_NAME = "app_database.db"

    @AppScope
    @Provides
    fun provideDatabase(context: Context): NoteDatabase = Room.databaseBuilder(
        context,
        NoteDatabase::class.java,
        DB_NAME
    ).build()

    @AppScope
    @Provides
    fun providesNoteDao(database: NoteDatabase): NoteDao = database.noteDao()
}