package no.kartveit.application

import com.github.mustachejava.DefaultMustacheFactory
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.StatusPages
import io.ktor.http.HttpStatusCode
import io.ktor.mustache.Mustache
import io.ktor.response.respond
import io.ktor.routing.routing
import io.ktor.server.engine.ApplicationEngine
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import no.kartveit.api.registerVessel


fun createApplicationEngine(): ApplicationEngine =
    embeddedServer(Netty, 8080) {
        routing {
            registerVessel()
        }
        install(Mustache) {
            mustacheFactory = DefaultMustacheFactory("templates/mustache")
        }
        install(StatusPages) {
            exception<Throwable> { cause ->
                call.respond(HttpStatusCode.InternalServerError, cause.message ?: "Unknown error")
                throw cause
            }
        }
    }