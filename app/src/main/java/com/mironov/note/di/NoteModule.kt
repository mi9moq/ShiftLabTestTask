package com.mironov.note.di

import com.mironov.note.data.repository.NoteRepositoryImpl
import com.mironov.note.domain.repository.NoteRepository
import dagger.Binds
import dagger.Module

@Module
interface NoteModule {

    @AppScope
    @Binds
    fun bindNoteRepository(impl: NoteRepositoryImpl): NoteRepository
}