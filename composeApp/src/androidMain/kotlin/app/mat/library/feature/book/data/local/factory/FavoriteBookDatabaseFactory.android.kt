package app.mat.library.feature.book.data.local.factory

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import app.mat.library.feature.book.data.local.database.FavoriteBookDatabase

actual class FavoriteBookDatabaseFactory(
    private val context: Context
) {
    //region Public Methods
    actual fun create(): RoomDatabase.Builder<FavoriteBookDatabase> {
        val applicationContext = context.applicationContext

        val databaseFile = applicationContext.getDatabasePath(
            FavoriteBookDatabase.DATABASE_NAME
        )

        return Room.databaseBuilder<FavoriteBookDatabase>(
            context = applicationContext,
            name = databaseFile.absolutePath
        )
    }
    //endregion Public Methods
}