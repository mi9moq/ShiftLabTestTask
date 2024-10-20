package com.mironov.note.domain.usecase

import com.mironov.note.domain.repository.NoteRepository
import javax.inject.Inject

class DeleteNoteUseCase @Inject constructor(
    private val repository: NoteRepository
) {

    suspend operator fun invoke(id: Int) = repository.delete(id)
}