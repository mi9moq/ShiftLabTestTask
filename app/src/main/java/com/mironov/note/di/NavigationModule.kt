package com.mironov.note.di

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.mironov.note.navigation.NoteListRouter
import com.mironov.note.navigation.NoteListRouterImpl
import com.mironov.note.navigation.NoteRouter
import com.mironov.note.navigation.NoteRouterImpl
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface NavigationModule {

    companion object {

        private val cicerone: Cicerone<Router> = Cicerone.create()

        @AppScope
        @Provides
        fun provideRouter(): Router = cicerone.router

        @AppScope
        @Provides
        fun provideNavigatorHolder(): NavigatorHolder = cicerone.getNavigatorHolder()
    }

    @AppScope
    @Binds
    fun bindNoteListRouter(impl: NoteListRouterImpl): NoteListRouter

    @AppScope
    @Binds
    fun bindNoteRouter(impl: NoteRouterImpl): NoteRouter
}