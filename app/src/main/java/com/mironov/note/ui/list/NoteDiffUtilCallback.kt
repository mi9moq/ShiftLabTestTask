package com.mironov.note.ui.list

import androidx.recyclerview.widget.DiffUtil
import com.mironov.note.domain.entity.Note

class NoteDiffUtilCallback: DiffUtil.ItemCallback<Note>() {

    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean = oldItem == newItem
}