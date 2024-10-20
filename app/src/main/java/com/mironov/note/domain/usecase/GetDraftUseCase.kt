package com.mironov.note.domain.usecase

import com.mironov.note.domain.repository.NoteRepository
import javax.inject.Inject

class GetDraftUseCase @Inject constructor(
    private val repository: NoteRepository
) {

    operator fun invoke() = repository.getDraft()
}