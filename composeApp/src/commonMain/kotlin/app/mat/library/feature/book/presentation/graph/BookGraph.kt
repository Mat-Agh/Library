package app.mat.library.feature.book.presentation.graph

import kotlinx.serialization.Serializable

@Serializable
sealed interface BookGraph {
    @Serializable
    data object Root : BookGraph

    @Serializable
    data object BookListScreen : BookGraph

    @Serializable
    data class BookDetailScreen(
        val id: String
    ) : BookGraph
}