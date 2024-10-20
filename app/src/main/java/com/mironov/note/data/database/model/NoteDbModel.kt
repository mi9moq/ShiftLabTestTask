package com.mironov.note.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mironov.note.data.database.model.NoteDbModel.Companion.TABLE_NAME

@Entity(
    tableName = TABLE_NAME
)
data class NoteDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String?,
    val description: String,
){

    companion object{
        const val TABLE_NAME = "note"
    }
}