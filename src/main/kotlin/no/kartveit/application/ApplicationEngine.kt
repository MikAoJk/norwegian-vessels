package no.kartveit.application

import com.github.mustachejava.DefaultMustacheFactory
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.install
import io.ktor.server.cio.CIO
import io.ktor.server.engine.ApplicationEngine
import io.ktor.server.engine.embeddedServer
import io.ktor.server.mustache.Mustache
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.response.respond
import io.ktor.server.routing.routing
import no.kartveit.api.registerVessel


fun createApplicationEngine(): ApplicationEngine =
    embeddedServer(CIO, 8080) {
        routing {
            registerVessel()
        }
        install(Mustache) {
            mustacheFactory = DefaultMustacheFactory("templates/mustache")
        }
        install(StatusPages) {
            exception<Throwable> {  call, cause ->
                call.respond(HttpStatusCode.InternalServerError, cause.message ?: "Unknown error")
                throw cause
            }
        }
    }