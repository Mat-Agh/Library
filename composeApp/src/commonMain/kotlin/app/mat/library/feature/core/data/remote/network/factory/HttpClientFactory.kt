package app.mat.library.feature.core.data.remote.network.factory

import app.mat.library.feature.core.data.parameter.ServiceParameter
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object HttpClientFactory {
    fun create(
        engine: HttpClientEngine
    ): HttpClient {
        return HttpClient(
            engine = engine
        ) {
            install(
                plugin = ContentNegotiation
            ) {
                json(
                    json = Json {
                        ignoreUnknownKeys = true
                    }
                )
            }
            install(
                plugin = HttpTimeout
            ) {
                socketTimeoutMillis = ServiceParameter.Core.REQUEST_TIMEOUT
                requestTimeoutMillis = ServiceParameter.Core.REQUEST_TIMEOUT
            }
            install(
                plugin = Logging
            ) {
                logger = object : Logger {
                    override fun log(
                        message: String
                    ) {
                        println(
                            message = message
                        )
                    }
                }
                level = ServiceParameter.Core.LOGGING_LEVEL
            }
            defaultRequest {
                contentType(
                    type = ContentType.Application.Json
                )
            }
        }
    }
}