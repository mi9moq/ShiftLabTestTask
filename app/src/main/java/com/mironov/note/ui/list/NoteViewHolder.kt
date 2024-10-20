package com.mironov.note.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.mironov.note.R
import com.mironov.note.databinding.NoteItemBinding
import com.mironov.note.domain.entity.Note

class NoteViewHolder(
    parent: ViewGroup,
    private val onClick: (Int) -> Unit,
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
) {

    private val binding = NoteItemBinding.bind(itemView)

    fun bind(note: Note) {
        with(binding) {
            if (note.title.isNullOrEmpty())
                title.isVisible = false
            else {
                title.isVisible = true
                title.text = note.title
            }
            description.text = note.description
        }
        itemView.setOnClickListener {
            onClick(note.id)

        }
    }
}