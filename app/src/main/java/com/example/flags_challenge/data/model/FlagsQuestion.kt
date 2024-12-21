package com.example.flags_challenge.data.model

data class FlagsQuestion(
    val answer_id: Int,
    val countries: List<Country>,
    val country_code: String
    )