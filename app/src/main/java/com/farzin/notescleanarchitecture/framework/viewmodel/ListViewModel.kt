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
class ListViewModel @Inject constructor(
    private val useCases: UseCases,
) :ViewModel() {
    val coroutineScope = CoroutineScope(Dispatchers.IO)

    val notesList = MutableLiveData<List<Note>>()

    fun getAllNotes(){
        coroutineScope.launch {
            val notes = useCases.getAllNotes()
            notesList.postValue(notes)
            notes.forEach {
                it.wordCount = useCases.getWordCount(it)
            }
        }
    }
}