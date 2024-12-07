package app.mat.library.feature.core.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import app.mat.library.feature.core.presentation.screen.application.ApplicationRootScreen

class LibraryActivity : ComponentActivity() {
    //region Companion Object
    companion object {
        private lateinit var activity: LibraryActivity

        fun get(): LibraryActivity = activity
    }

    //endregion Companion Object

    //region Override Methods
    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(
            savedInstanceState
        )

        setActivity()

        setContent {
            ApplicationRootScreen()
        }
    }
    //endregion Override Methods

    //region Private Methods
    private fun setActivity() {
        activity = this
    }
    //endregion Private Methods
}