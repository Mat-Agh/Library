package app.mat.library.di.initializer

import app.mat.library.di.platformModule
import app.mat.library.di.sharedModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

private val moduleList = listOf(
    sharedModule,
    platformModule
)

fun initializeKoin(
    configuration: KoinAppDeclaration? = null
) {
    startKoin {
        configuration?.invoke(this)

        modules(
            modules = moduleList
        )
    }
}