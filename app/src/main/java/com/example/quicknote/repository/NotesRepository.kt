package com.example.quicknote.repository

import androidx.lifecycle.LiveData
import com.example.quicknote.db.NotesDataBase
import com.example.quicknote.models.Note
import javax.inject.Inject


class NotesRepository @Inject constructor(private val notesDataBase: NotesDataBase) {


    private val notesDAO = notesDataBase.getNoteDao()
    suspend fun saveNote(note: Note) {
        notesDAO.saveNote(note)
    }

    suspend fun deleteNote(note: Note) {
        notesDAO.deleteNote(note)
    }

    suspend fun deleteAll() {
        notesDAO.deleteAll()
    }

    suspend fun updateNote(note: Note) {
        notesDAO.updateNote(note)
    }

    fun getNotes(): LiveData<List<Note>> {
        return notesDAO.getNotes()

    }
}