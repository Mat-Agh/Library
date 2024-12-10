package app.mat.library.feature.book.data.local.constructor

import androidx.room.RoomDatabaseConstructor
import app.mat.library.feature.book.data.local.database.FavoriteBookDatabase

@Suppress(
    "NO_ACTUAL_FOR_EXPECT",
    "EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING"
)
expect object FavoriteBookDatabaseConstructor : RoomDatabaseConstructor<FavoriteBookDatabase> {
    override fun initialize(): FavoriteBookDatabase
}