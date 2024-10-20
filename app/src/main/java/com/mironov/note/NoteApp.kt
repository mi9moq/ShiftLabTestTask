package com.mironov.note

import android.app.Application
import com.mironov.note.di.DaggerAppComponent

class NoteApp : Application() {
    val component by lazy {
        DaggerAppComponent.factory().create(this)
    }
}