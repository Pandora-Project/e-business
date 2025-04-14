import io.ktor.server.engine.*
import io.ktor.server.netty.*
import dev.kord.core.Kord
import dev.kord.core.event.message.MessageCreateEvent
import dev.kord.core.on
import dev.kord.gateway.Intent
import dev.kord.gateway.Intents
import dev.kord.gateway.PrivilegedIntent

private val BOT_TOKEN = try {
    ClassLoader.getSystemResource("bot-token.txt").readText().trim()
} catch (error: Exception) {
    throw RuntimeException(
        "Failed to load bot token. Ensure 'bot-token.txt' is in 'src/main/resources' with your bot token.", error
    )
}
@OptIn(PrivilegedIntent::class)
suspend fun main(args: Array<String>) {
    val bot = Kord(BOT_TOKEN)

    // Listening for message create event and acting on commands
    bot.on<MessageCreateEvent> {

        if (message.author?.isBot != false) return@on
        if (message.content != "!ping") return@on
        message.channel.createMessage("pong!")
    }

    bot.login {
        @OptIn(PrivilegedIntent::class)
        intents += Intent.MessageContent

    }
}
