package com.mironov.note.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.mironov.note.ui.list.NoteListFragment
import com.mironov.note.ui.note.NoteFragment

fun getNoteScreen(isEdit: Boolean, noteId: Int) = FragmentScreen {
    NoteFragment.newInstance(isEdit, noteId)
}

fun getNoteListScreen() = FragmentScreen {
    NoteListFragment.newInstance()
}