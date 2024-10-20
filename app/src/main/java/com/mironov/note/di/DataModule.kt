package com.mironov.note.di

import android.content.Context
import androidx.room.Room
import com.mironov.note.data.database.NoteDao
import com.mironov.note.data.database.NoteDatabase
import com.mironov.note.data.pref.DraftStore
import com.mironov.note.data.pref.DraftStoreImpl
import com.mironov.note.data.repository.NoteRepositoryImpl
import com.mironov.note.domain.repository.NoteRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    companion object {
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

    @AppScope
    @Binds
    fun bindNoteRepository(impl: NoteRepositoryImpl): NoteRepository

    @AppScope
    @Binds
    fun bindDraftStore(impl: DraftStoreImpl): DraftStore
}