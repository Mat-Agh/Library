package app.mat.library.feature.book.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import app.mat.library.feature.book.data.local.entity.FavoriteBookEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteBookDao {
    @Upsert
    suspend fun upsert(
        favoriteBookEntity: FavoriteBookEntity
    )

    @Query(
        "SELECT * FROM FavoriteBookEntity"
    )
    fun getFavoriteBookList(): Flow<List<FavoriteBookEntity>>

    @Query(
        "SELECT * FROM FavoriteBookEntity WHERE id = :id"
    )
    suspend fun getFavoriteBook(
        id: String
    ): FavoriteBookEntity?

    @Query(
        "DELETE FROM FavoriteBookEntity WHERE id = :id"
    )
    suspend fun deleteFavoriteBook(
        id: String
    )
}