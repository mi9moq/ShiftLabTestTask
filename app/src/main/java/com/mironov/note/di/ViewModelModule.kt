package com.mironov.note.di

import androidx.lifecycle.ViewModel
import com.mironov.note.presentation.list.NoteListViewModel
import com.mironov.note.presentation.note.NoteViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(NoteViewModel::class)
    fun bindNoteViewModel(impl: NoteViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NoteListViewModel::class)
    fun bindNoteListViewModel(impl: NoteListViewModel): ViewModel
}