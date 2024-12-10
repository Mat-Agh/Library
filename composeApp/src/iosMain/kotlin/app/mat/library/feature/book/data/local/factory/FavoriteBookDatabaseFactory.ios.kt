package app.mat.library.feature.book.data.local.factory

import androidx.room.Room
import androidx.room.RoomDatabase
import app.mat.library.feature.book.data.local.database.FavoriteBookDatabase
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class FavoriteBookDatabaseFactory {
    //region Public Methods
    actual fun create(): RoomDatabase.Builder<FavoriteBookDatabase> {
        val databaseFile = "${documentDirectory()}/${FavoriteBookDatabase.DATABASE_NAME}"

        return Room.databaseBuilder<FavoriteBookDatabase>(
            name = databaseFile
        )
    }
    //endregion Public Methods

    //region Private Methods
    @OptIn(ExperimentalForeignApi::class)
    private fun documentDirectory(): String {
        val documentDirectory = NSFileManager.defaultManager().URLForDirectory(
            directory = NSDocumentDirectory,
            inDomain = NSUserDomainMask,
            appropriateForURL = null,
            create = false,
            error = null
        )

        return requireNotNull(
            value = documentDirectory?.path
        )
    }
}