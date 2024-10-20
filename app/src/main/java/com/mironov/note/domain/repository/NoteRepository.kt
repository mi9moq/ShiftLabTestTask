package com.mironov.note.domain.repository

import com.mironov.note.domain.entity.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    fun getAll(): Flow<List<Note>>

    suspend fun getById(id: Int): Note

    suspend fun create(note: Note)

    suspend fun delete(id: Int)

    fun getDraft(): Note

    fun clearDraft()

    fun saveDraft(title: String?, description: String)
}