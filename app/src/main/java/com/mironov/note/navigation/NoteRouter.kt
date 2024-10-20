package com.mironov.note.navigation

import com.github.terrakok.cicerone.Router
import javax.inject.Inject

interface NoteRouter {

    fun back()
}

class NoteRouterImpl @Inject constructor(
    private val router: Router
): NoteRouter {
    override fun back() {
        router.backTo(getNoteListScreen())
    }
}