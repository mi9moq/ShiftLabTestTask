package com.mironov.note.presentation.note

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mironov.note.domain.entity.Note
import com.mironov.note.domain.usecase.CreateNoteUseCase
import com.mironov.note.domain.usecase.GetNoteByIdUseCase
import com.mironov.note.navigation.NoteRouter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class NoteViewModel @Inject constructor(
    private val createNoteUseCase: CreateNoteUseCase,
    private val getNoteByIdUseCase: GetNoteByIdUseCase,
    private val router: NoteRouter,
) : ViewModel() {

    private val _state = MutableStateFlow<NoteScreenState>(NoteScreenState.Initial)
    val state = _state.asStateFlow()

    fun createNote(title: String?, description: String) {
        viewModelScope.launch {
            val note = Note(title = title, description = description)
            createNoteUseCase(note)
            router.back()
        }
    }

    fun editNote(title: String?, description: String, noteId: Int) {
        viewModelScope.launch {
            val note = Note(id = noteId, title = title, description = description)
            createNoteUseCase(note)
            router.back()
        }
    }

    fun getNote(noteId: Int) {
        viewModelScope.launch {
            val note = getNoteByIdUseCase(noteId)
            _state.value = NoteScreenState.Content(note.title, note.description)
        }
    }

    fun back(){
        router.back()
    }
}