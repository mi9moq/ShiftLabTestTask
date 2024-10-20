package com.mironov.note.data.mapper

import com.mironov.note.data.database.model.NoteDbModel
import com.mironov.note.domain.entity.Note

fun NoteDbModel.toEntity() = Note(
    id = id,
    title = title,
    description = description
)

fun Note.toDbModel() = NoteDbModel(
    id = id,
    title = title,
    description = description
)