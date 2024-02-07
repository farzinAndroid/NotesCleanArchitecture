package com.farzin.notescleanarchitecture.framework.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.farzin.core.data.Note
import com.farzin.core.repository.NoteRepository
import com.farzin.core.usecase.AddNote
import com.farzin.core.usecase.GetAllNotes
import com.farzin.core.usecase.GetNote
import com.farzin.core.usecase.RemoveNote
import com.farzin.notescleanarchitecture.framework.RoomNoteDataSource
import com.farzin.notescleanarchitecture.framework.db.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val useCases: UseCases,
) : ViewModel() {
    val coroutineScope = CoroutineScope(Dispatchers.IO)

    val saved = MutableLiveData<Boolean>(false)

    fun addNote(note:Note){
        coroutineScope.launch {
            useCases.addNote(note)
            saved.postValue(true)
        }
    }

    val currentNote = MutableLiveData<Note?>()
    fun getNote(id:Long){
        coroutineScope.launch {
            currentNote.postValue(useCases.getNote(id))
        }
    }

    fun deleteNote(note: Note){
        coroutineScope.launch {
           useCases.removeNote(note)
            saved.postValue(true)
        }
    }

}