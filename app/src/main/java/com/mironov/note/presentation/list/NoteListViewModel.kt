package com.mironov.note.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.terrakok.cicerone.Router
import com.mironov.note.domain.usecase.DeleteNoteUseCase
import com.mironov.note.domain.usecase.GetAllNotesUseCase
import com.mironov.note.navigation.NoteListRouter
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class NoteListViewModel @Inject constructor(
    private val getAllNotesUseCase: GetAllNotesUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val router: NoteListRouter,
) : ViewModel() {

    val state: StateFlow<NoteListState> = getAllNotesUseCase().map {
        NoteListState.Content(it)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = NoteListState.Initial
    )

    fun deleteNote(noteId: Int) {
        viewModelScope.launch {
            deleteNoteUseCase(noteId)
        }
    }

    fun editNote(noteId: Int) {
        router.editNote(noteId)
    }

    fun createNote() {
        router.createNote()
    }
}