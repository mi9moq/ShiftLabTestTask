package com.mironov.note.data.pref

interface DraftStore {
    fun saveDraft(title: String?, description: String)

    fun clearDraft()

    fun getDraft(): Pair<String?, String>
}