package com.mironov.note.presentation.list

import com.mironov.note.domain.entity.Note

sealed interface NoteListState {

    data object Initial: NoteListState

    data class Content(val content: List<Note>): NoteListState
}