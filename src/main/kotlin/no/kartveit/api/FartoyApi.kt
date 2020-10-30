package no.kartveit.api

import io.ktor.application.call
import io.ktor.mustache.MustacheContent
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get
import no.kartveit.service.VesselDataService


fun Routing.registerVesselData() {

    get("/vesselData") {
        call.respond(MustacheContent("vessel-data.hbs", mapOf("vessel" to VesselDataService().getVesselData())))
    }

}