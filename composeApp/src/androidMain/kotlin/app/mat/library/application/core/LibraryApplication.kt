package app.mat.library.application.core

import android.app.Application
import app.mat.library.di.initializer.initializeKoin
import org.koin.android.ext.koin.androidContext

class LibraryApplication : Application() {
    //region Companion Object
    companion object {
        lateinit var application: Application

        fun get() = application
    }
    //endregion Companion Object

    //region Override Methods
    override fun onCreate() {
        super.onCreate()

        handleKoin()

        setApplication()
    }
    //endregion Override Methods

    //region Private Methods
    private fun setApplication() {
        application = this
    }

    private fun handleKoin() {
        initializeKoin {
            androidContext(
                this@LibraryApplication
            )
        }
    }
    //endregion Private Methods
}