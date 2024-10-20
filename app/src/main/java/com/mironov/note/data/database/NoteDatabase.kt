package com.mironov.note.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mironov.note.data.database.model.NoteDbModel

@Database(
    entities = [
        NoteDbModel::class,
    ],
    version = 1
)
abstract class NoteDatabase: RoomDatabase() {

    abstract fun noteDao(): NoteDao
}