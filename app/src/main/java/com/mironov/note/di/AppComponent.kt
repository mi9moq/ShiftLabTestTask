package com.mironov.note.di

import android.content.Context
import com.mironov.note.ui.MainActivity
import com.mironov.note.ui.note.NoteFragment
import com.mironov.note.ui.list.NoteListFragment
import dagger.BindsInstance
import dagger.Component

@AppScope
@Component(
    modules = [
        DatabaseModule::class,
        NoteModule::class,
        ViewModelModule::class,
        NavigationModule::class,
    ]
)
interface AppComponent {

    fun inject(activity: MainActivity)
    fun inject(fragment: NoteFragment)
    fun inject(fragment: NoteListFragment)

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance context: Context
        ): AppComponent
    }
}