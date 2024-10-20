package com.mironov.note.data.repository

import com.mironov.note.data.database.NoteDao
import com.mironov.note.data.mapper.toDbModel
import com.mironov.note.data.mapper.toEntity
import com.mironov.note.data.pref.DraftStore
import com.mironov.note.domain.entity.Note
import com.mironov.note.domain.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val dao: NoteDao,
    private val draftStore: DraftStore,
) : NoteRepository {

    override fun getAll(): Flow<List<Note>> = dao.getAll().map { noteBdModelList ->
        noteBdModelList.map { it.toEntity() }
    }

    override suspend fun getById(id: Int): Note = withContext(Dispatchers.Default) {
        dao.getById(id).toEntity()
    }

    override suspend fun create(note: Note) {
        dao.insert(note.toDbModel())
    }

    override suspend fun delete(id: Int) = withContext(Dispatchers.Default) {
        dao.delete(id)
    }

    override fun getDraft(): Note {
        val (title, description) = draftStore.getDraft()
        return Note(title = title, description = description)
    }

    override fun clearDraft() {
        draftStore.clearDraft()
    }

    override fun saveDraft(title: String?, description: String) {
        draftStore.saveDraft(title, description)
    }
}