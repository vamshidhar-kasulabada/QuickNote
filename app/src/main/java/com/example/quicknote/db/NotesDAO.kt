package com.example.quicknote.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.quicknote.models.Note


@Dao
interface NotesDAO {


    @Query("SELECT * FROM Note")
    fun getNotes(): LiveData<List<Note>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)


    @Query("DELETE FROM Note")
    suspend fun deleteAll()

    @Update
    suspend fun updateNote(note: Note)



}