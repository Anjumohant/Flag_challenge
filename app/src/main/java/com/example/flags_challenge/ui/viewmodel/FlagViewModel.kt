package com.example.flags_challenge.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.liveData
import com.example.flags_challenge.data.repository.FlagsRepository
import kotlinx.coroutines.Dispatchers


class FlagViewModel(application: Application) : AndroidViewModel(application) {

    private val flagRepository = FlagsRepository(application)

    // LiveData to observe the list of questions
    val questions = liveData(Dispatchers.IO) {
        val questions = flagRepository.getQuestions()
        emit(questions)
    }
}
