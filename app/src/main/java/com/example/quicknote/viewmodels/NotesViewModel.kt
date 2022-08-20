package com.example.quicknote.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quicknote.models.Note
import com.example.quicknote.repository.NotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NotesViewModel @Inject constructor(private val notesRepository: NotesRepository) :
    ViewModel() {


    fun getNotes(): LiveData<List<Note>> {
        return notesRepository.getNotes()
    }

    fun saveNote(note: Note) {
        viewModelScope.launch {
            notesRepository.saveNote(note)
        }
    }

    fun deleteNote(note: Note){
        viewModelScope.launch {
            notesRepository.deleteNote(note)
        }
    }


}