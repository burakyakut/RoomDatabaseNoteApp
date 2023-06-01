package com.example.roomdatabasenoteapp.room

import androidx.room.*

@Dao
interface NoteDao {

    @Insert
    fun insertNote(noteModel: NoteModel)

    @Update
    fun updateNote(noteModel: NoteModel)

    @Delete
    fun deleteNote(noteModel: NoteModel)

    @Query("SELECT * FROM noteTable")
    fun getAllNote():List<NoteModel>
}