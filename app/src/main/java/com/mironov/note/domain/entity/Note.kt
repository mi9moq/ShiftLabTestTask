package com.mironov.note.domain.entity

data class Note(
    val id: Int = UNDEFINED_ID,
    val title: String?,
    val description: String,
){

    companion object {

        const val UNDEFINED_ID = 0
    }
}