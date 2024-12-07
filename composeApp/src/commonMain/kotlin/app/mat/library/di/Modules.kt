package app.mat.library.di

import app.mat.library.feature.book.data.remote.network.dataSource.BookRemoteDataSource
import app.mat.library.feature.book.data.remote.network.dataSource.BookRemoteDataSourceImpl
import app.mat.library.feature.book.data.repository.BookRepositoryImpl
import app.mat.library.feature.book.domain.repository.BookRepository
import app.mat.library.feature.book.domain.usecase.SearchBookRemoteUseCase
import app.mat.library.feature.book.domain.usecase.SearchBookRemoteUseCaseImpl
import app.mat.library.feature.book.presentation.bookDetail.screen.BookDetailScreenViewModel
import app.mat.library.feature.book.presentation.bookList.screen.BookListScreenViewModel
import app.mat.library.feature.book.presentation.shared.BookViewModel
import app.mat.library.feature.core.data.remote.network.factory.HttpClientFactory
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule: Module = module {
    //region Objects
    single {
        HttpClientFactory.create(
            engine = get()
        )
    }
    //endregion Objects

    //region Remote Data Source
    singleOf(
        constructor = ::BookRemoteDataSourceImpl
    ).bind<BookRemoteDataSource>()
    //endregion Remote Data Source

    //region Local Data Source

    //endregion Local Data Source

    //region Repository
    singleOf(
        constructor = ::BookRepositoryImpl
    ).bind<BookRepository>()
    //endregion Repository

    //region Use Case
    singleOf(
        constructor = ::SearchBookRemoteUseCaseImpl
    ).bind<SearchBookRemoteUseCase>()
    //endregion Use Case

    //region View Model
    viewModelOf(
        constructor = ::BookViewModel
    )

    viewModelOf(
        constructor = ::BookListScreenViewModel
    )

    viewModelOf(
        constructor = ::BookDetailScreenViewModel
    )
    //endregion View Model
}