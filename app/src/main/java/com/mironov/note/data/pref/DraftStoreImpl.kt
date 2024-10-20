package com.mironov.note.data.pref

import android.content.Context
import javax.inject.Inject

class DraftStoreImpl @Inject constructor(
    private val context: Context
) : DraftStore {

    companion object {
        private const val DRAFT_NAME = "DRAFT_NAME"
        private const val DRAFT_TITLE_KEY = "DRAFT_TITLE"
        private const val DRAFT_DESCRIPTION_KEY = "DRAFT_DESCRIPTION"
        private const val EMPTY_STRING = ""
    }

    override fun saveDraft(title: String?, description: String) {
        context.getSharedPreferences(DRAFT_NAME, Context.MODE_PRIVATE)
            .edit()
            .putString(DRAFT_TITLE_KEY, title)
            .putString(DRAFT_DESCRIPTION_KEY, description)
            .apply()
    }

    override fun clearDraft() {
        context.getSharedPreferences(DRAFT_NAME, Context.MODE_PRIVATE)
            .edit()
            .clear()
            .apply()
    }

    override fun getDraft(): Pair<String?, String> {
        val title = context.getSharedPreferences(DRAFT_NAME, Context.MODE_PRIVATE)
            .getString(DRAFT_TITLE_KEY, null)
        val description = context.getSharedPreferences(DRAFT_NAME, Context.MODE_PRIVATE)
            .getString(DRAFT_DESCRIPTION_KEY, EMPTY_STRING) ?: EMPTY_STRING

        return Pair(title, description)
    }
}