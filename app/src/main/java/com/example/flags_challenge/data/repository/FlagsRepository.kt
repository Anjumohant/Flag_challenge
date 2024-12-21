package com.example.flags_challenge.data.repository
import com.google.gson.Gson

import android.content.Context
import com.example.flags_challenge.R
import com.example.flags_challenge.data.model.FlagsData
import com.example.flags_challenge.data.model.FlagsQuestion
import java.io.IOException
import java.io.InputStreamReader

class FlagsRepository(private val context: Context) {

    // Function to load questions from JSON file in raw resources
    fun getQuestions(): List<FlagsQuestion> {
        val inputStream = context.resources.openRawResource(R.raw.questions)
        val reader = InputStreamReader(inputStream)
        val gson = Gson()

        // Parse the JSON into a Question List
        val questionResponse = gson.fromJson(reader, FlagsData::class.java)
        return questionResponse.questions
    }
}
