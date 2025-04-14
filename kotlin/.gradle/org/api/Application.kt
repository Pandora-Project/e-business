import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.example.discord.configureDiscord
import org.example.discord.sendDiscordMessage

fun Application.module() {
    install(io.ktor.server.plugins.contentnegotiation.ContentNegotiation) {
        io.ktor.serialization.kotlinx.json.json()
    }
    configureDiscord()

    routing {
        post("/discord/send/{channelId}") {
            val channelId = call.parameters["channelId"]?.toLongOrNull()
            val message = call.request.queryParameters["message"]

            if (channelId != null && !message.isNullOrBlank()) {
                sendDiscordMessage(channelId, message)
                call.respond("Wiadomość wysłana na Discord")
            } else {
                call.respond("Nieprawidłowe ID kanału lub treść wiadomości")
            }
        }
    }
}

fun main() {
    io.ktor.server.netty.EngineMain.main(arrayOf("-port=8080"))
}