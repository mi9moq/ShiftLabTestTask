package com.mironov.note.ui.list

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.mironov.note.domain.entity.Note

class NoteAdapter(
    private val onClick: (Int) -> Unit,
): ListAdapter<Note, NoteViewHolder>(NoteDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder =
        NoteViewHolder(parent, onClick)

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}