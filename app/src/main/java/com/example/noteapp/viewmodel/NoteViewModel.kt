package com.example.noteapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.noteapp.data.model.NoteModel
import com.example.noteapp.data.repository.NoteRepository
import com.example.noteapp.data.database.NoteDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    private val TAG = "NoteViewModel"
    val showAllNotes: LiveData<List<NoteModel>>
    private val noteRepository: NoteRepository

    init {
        val workDao = NoteDatabase.getDatabase(application).noteDao()
        noteRepository = NoteRepository(workDao)
        showAllNotes = noteRepository.showAllNotes
    }

    fun addSingleNote(note: NoteModel) = viewModelScope.launch(Dispatchers.IO) {
        noteRepository.addSingleNote(note)
    }

    fun updateExistingNote(note: NoteModel) = viewModelScope.launch(Dispatchers.IO) {
        noteRepository.updateExistingNote(note)
    }

    fun deleteSingleNote(note: NoteModel) = viewModelScope.launch(Dispatchers.IO) {
        noteRepository.deleteSingleNote(note)
    }

    fun deleteAllNotes() = viewModelScope.launch(Dispatchers.IO) {
        noteRepository.deleteAllNotes()
    }

}