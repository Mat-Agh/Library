package app.mat.library.di

import app.mat.library.feature.book.data.local.factory.FavoriteBookDatabaseFactory
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module
    get() = module {
        //region Objects
        single<HttpClientEngine> {
            OkHttp.create()
        }
        //endregion Objects

        //region Factory
        single {
            FavoriteBookDatabaseFactory()
        }
        //endregion Factory
    }