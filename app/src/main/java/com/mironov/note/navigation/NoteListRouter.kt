package com.mironov.note.navigation

import com.github.terrakok.cicerone.Router
import com.mironov.note.domain.entity.Note
import javax.inject.Inject

interface NoteListRouter {

    fun editNote(noteId: Int)

    fun createNote()
}

class NoteListRouterImpl @Inject constructor(
    private val router: Router
) : NoteListRouter {

    override fun editNote(noteId: Int) {
        router.navigateTo(getNoteScreen(isEdit = true, noteId = noteId))
    }

    override fun createNote() {
        router.navigateTo(getNoteScreen(isEdit = false, noteId = Note.UNDEFINED_ID))
    }
}