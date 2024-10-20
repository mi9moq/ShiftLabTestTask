package com.mironov.note.domain.usecase

import com.mironov.note.domain.entity.Note
import com.mironov.note.domain.repository.NoteRepository
import javax.inject.Inject

class CreateNoteUseCase @Inject constructor(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(note: Note) = repository.create(note)
}