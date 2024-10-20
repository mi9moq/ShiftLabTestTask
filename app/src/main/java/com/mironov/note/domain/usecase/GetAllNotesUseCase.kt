package com.mironov.note.domain.usecase

import com.mironov.note.domain.repository.NoteRepository
import javax.inject.Inject

class GetAllNotesUseCase @Inject constructor(
    private val repository: NoteRepository
) {
    operator fun invoke() = repository.getAll()
}