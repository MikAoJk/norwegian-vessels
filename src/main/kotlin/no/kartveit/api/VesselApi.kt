package no.kartveit.api

import io.ktor.application.call
import io.ktor.mustache.MustacheContent
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get
import no.kartveit.service.VesselDataService


fun Routing.registerVessel() {

    get("/api") {
        val request = call.request
        val municipality: String? = request.queryParameters["municipality"]

        call.respond(
            MustacheContent(
                "vessel-data.hbs",
                mapOf("vessel" to VesselDataService().getVesselData().filter { it.municipalityName == municipality })
            )
        )
    }

    get("/api/allVessels") {
        call.respond(MustacheContent("vessel-data.hbs", mapOf("vessel" to VesselDataService().getVesselData())))
    }

}