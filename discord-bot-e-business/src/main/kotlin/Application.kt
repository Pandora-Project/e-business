import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import io.ktor.http.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Bot is running!", ContentType.Text.Plain)
        }

        get("/api/auth/discord/redirect") {
            val code = call.request.queryParameters["code"]
            if (code == null) {
                call.respond(HttpStatusCode.BadRequest, "Missing 'code' parameter")
            } else {
                call.respondText("Authorization successful! Code: $code", ContentType.Text.Plain)
            }
        }
    }
}
