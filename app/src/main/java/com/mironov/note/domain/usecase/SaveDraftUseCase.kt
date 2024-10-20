package com.mironov.note.domain.usecase

import com.mironov.note.domain.repository.NoteRepository
import javax.inject.Inject

class SaveDraftUseCase @Inject constructor(
    private val repository: NoteRepository
) {
    operator fun invoke(title: String?, description: String) =
        repository.saveDraft(title, description)
}