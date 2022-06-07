package no.kartveit.api

import io.ktor.server.application.call
import io.ktor.server.mustache.MustacheContent
import io.ktor.server.response.respond
import io.ktor.server.routing.Routing
import io.ktor.server.routing.get
import java.util.Locale
import no.kartveit.service.VesselDataService


fun Routing.registerVessel() {

    get("/api") {
        val request = call.request
        val municipalityUpperCase: String? = request.queryParameters["municipality"]?.uppercase(Locale.getDefault())

        call.respond(
            MustacheContent(
                "vessel-data.hbs",
                mapOf("vessel" to VesselDataService().getVesselData().filter { it.municipalityName == municipalityUpperCase })
            )
        )
    }

    get("/api/allVessels") {
        call.respond(MustacheContent("vessel-data.hbs", mapOf("vessel" to VesselDataService().getVesselData())))
    }

}