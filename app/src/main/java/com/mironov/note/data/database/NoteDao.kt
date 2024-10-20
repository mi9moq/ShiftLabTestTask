package com.mironov.note.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mironov.note.data.database.model.NoteDbModel
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("SELECT * FROM ${NoteDbModel.TABLE_NAME}")
    fun getAll(): Flow<List<NoteDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(noteDbModel: NoteDbModel)

    @Query("SELECT * FROM ${NoteDbModel.TABLE_NAME} WHERE id = :id")
    suspend fun getById(id: Int): NoteDbModel

    @Query("DELETE FROM ${NoteDbModel.TABLE_NAME} WHERE id = :id")
    fun delete(id: Int)
}