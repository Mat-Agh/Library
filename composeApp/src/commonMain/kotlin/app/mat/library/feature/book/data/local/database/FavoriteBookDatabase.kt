package app.mat.library.feature.book.data.local.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import app.mat.library.feature.book.data.local.constructor.FavoriteBookDatabaseConstructor
import app.mat.library.feature.book.data.local.dao.FavoriteBookDao
import app.mat.library.feature.book.data.local.entity.FavoriteBookEntity
import app.mat.library.feature.core.data.local.typeConverter.StringListTypeConverter

@Database(
    entities = [FavoriteBookEntity::class],
    version = 1
)
@TypeConverters(
    StringListTypeConverter::class
)
@ConstructedBy(
    FavoriteBookDatabaseConstructor::class
)
abstract class FavoriteBookDatabase : RoomDatabase() {
    //region Companion Object
    companion object {
        const val DATABASE_NAME = "favoriteBook.db"
    }
    //endregion Companion Object

    //region Variables
    abstract val favoriteBookDao: FavoriteBookDao
    //endregion Variables


}