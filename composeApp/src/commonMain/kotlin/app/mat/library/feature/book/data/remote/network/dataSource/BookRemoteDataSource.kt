package app.mat.library.feature.book.data.remote.network.dataSource

import app.mat.library.feature.book.data.remote.dto.SearchResponseDto
import app.mat.library.feature.core.domain.type.DataError
import app.mat.library.feature.core.domain.type.Result

interface BookRemoteDataSource {
    suspend fun search(
        query: String,
        resultLimit: Int? = null
    ): Result<SearchResponseDto, DataError.Remote>
}