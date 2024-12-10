package app.mat.library.feature.book.data.local.factory

import androidx.room.RoomDatabase
import app.mat.library.feature.book.data.local.database.FavoriteBookDatabase

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class FavoriteBookDatabaseFactory {
    fun create(): RoomDatabase.Builder<FavoriteBookDatabase>
}