package app.mat.library.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import app.mat.library.feature.book.data.local.database.FavoriteBookDatabase
import app.mat.library.feature.book.data.local.factory.FavoriteBookDatabaseFactory
import app.mat.library.feature.book.data.remote.network.dataSource.BookRemoteDataSource
import app.mat.library.feature.book.data.remote.network.dataSource.BookRemoteDataSourceImpl
import app.mat.library.feature.book.data.repository.BookRepositoryImpl
import app.mat.library.feature.book.domain.repository.BookRepository
import app.mat.library.feature.book.domain.usecase.local.description.GetBookDescriptionLocalUseCase
import app.mat.library.feature.book.domain.usecase.local.description.GetBookDescriptionLocalUseCaseImpl
import app.mat.library.feature.book.domain.usecase.local.favorite.add.AddFavoriteBookLocalUseCase
import app.mat.library.feature.book.domain.usecase.local.favorite.add.AddFavoriteBookLocalUseCaseImpl
import app.mat.library.feature.book.domain.usecase.local.favorite.checkStatus.CheckFavoriteBookStatusLocalUseCase
import app.mat.library.feature.book.domain.usecase.local.favorite.checkStatus.CheckFavoriteBookStatusLocalUseCaseImpl
import app.mat.library.feature.book.domain.usecase.local.favorite.delete.DeleteFavoriteBookLocalUseCase
import app.mat.library.feature.book.domain.usecase.local.favorite.delete.DeleteFavoriteBookLocalUseCaseImpl
import app.mat.library.feature.book.domain.usecase.local.favorite.get.GetFavoriteBookListLocalUseCase
import app.mat.library.feature.book.domain.usecase.local.favorite.get.GetFavoriteBookListLocalUseCaseImpl
import app.mat.library.feature.book.domain.usecase.remote.search.SearchBookRemoteUseCase
import app.mat.library.feature.book.domain.usecase.remote.search.SearchBookRemoteUseCaseImpl
import app.mat.library.feature.book.presentation.bookDetail.screen.BookDetailScreenViewModel
import app.mat.library.feature.book.presentation.bookList.screen.BookListScreenViewModel
import app.mat.library.feature.book.presentation.shared.BookViewModel
import app.mat.library.feature.core.data.remote.network.factory.HttpClientFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.SupervisorJob
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule: Module = module {
    //region Coroutine Scope
    single {
        Dispatchers.IO
    }

    single {
        CoroutineScope(
            context = SupervisorJob() + Dispatchers.Default
        )
    }
    //endregion Coroutine Scope

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

    //region Factory
    single {
        get<FavoriteBookDatabaseFactory>().create()
            .setDriver(
                driver = BundledSQLiteDriver()
            )
            .build()
    }
    //endregion Factory

    //region Local Data Source
    single {
        get<FavoriteBookDatabase>().favoriteBookDao
    }
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

    singleOf(
        constructor = ::GetBookDescriptionLocalUseCaseImpl
    ).bind<GetBookDescriptionLocalUseCase>()

    singleOf(
        constructor = ::AddFavoriteBookLocalUseCaseImpl
    ).bind<AddFavoriteBookLocalUseCase>()

    singleOf(
        constructor = ::DeleteFavoriteBookLocalUseCaseImpl
    ).bind<DeleteFavoriteBookLocalUseCase>()

    singleOf(
        constructor = ::CheckFavoriteBookStatusLocalUseCaseImpl
    ).bind<CheckFavoriteBookStatusLocalUseCase>()

    singleOf(
        constructor = ::GetFavoriteBookListLocalUseCaseImpl
    ).bind<GetFavoriteBookListLocalUseCase>()
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