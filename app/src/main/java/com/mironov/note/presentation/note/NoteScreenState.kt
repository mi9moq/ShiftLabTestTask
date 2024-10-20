package com.mironov.note.presentation.note

sealed interface NoteScreenState {

    data object Initial: NoteScreenState

    data class Content(val title: String?, val description: String): NoteScreenState
}