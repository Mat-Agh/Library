package app.mat.library.feature.book.data.remote.network.dataSource

import app.mat.library.feature.book.data.remote.dto.SearchResponseDto
import app.mat.library.feature.core.data.extension.safeApiCall
import app.mat.library.feature.core.data.parameter.ServiceParameter
import app.mat.library.feature.core.domain.type.DataError
import app.mat.library.feature.core.domain.type.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class BookRemoteDataSourceImpl(
    private val httpClient: HttpClient
) : BookRemoteDataSource {
    override suspend fun search(
        query: String,
        resultLimit: Int?
    ): Result<SearchResponseDto, DataError.Remote> = safeApiCall {
        httpClient.get(
            urlString = "${ServiceParameter.Core.BASE_API_URL}${ServiceParameter.Search.PATH}"
        ) {
            parameter(
                key = "q",
                value = query
            )
            parameter(
                key = "limit",
                value = resultLimit
            )
            parameter(
                key = "language",
                value = "eng"
            )
            parameter(
                key = "fields",
                value = "key,title,language,cover_i,author_key,author_name,cover_edition_key,first_publish_year,ratings_average,ratings_count,number_of_pages_median,edition_count"
            )
        }
    }
}