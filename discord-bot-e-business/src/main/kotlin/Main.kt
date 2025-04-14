import io.ktor.server.engine.*
import io.ktor.server.netty.*
import dev.kord.core.Kord
import dev.kord.core.event.message.MessageCreateEvent
import dev.kord.core.on
import dev.kord.gateway.Intent
import dev.kord.gateway.Intents
import dev.kord.gateway.PrivilegedIntent
import kotlinx.serialization.json.Json
import kotlinx.serialization.decodeFromString
import java.io.InputStream

private val BOT_TOKEN = try {
    ClassLoader.getSystemResource("bot-token.txt").readText().trim()
} catch (error: Exception) {
    throw RuntimeException(
        "Failed to load bot token. Ensure 'bot-token.txt' is in 'src/main/resources' with your bot token.", error
    )
}

suspend fun loadCategories(): Map<String, List<String>> {
    val inputStream: InputStream = object {}.javaClass.getResourceAsStream("/categories.json")
        ?: throw IllegalArgumentException("Resource not found: categories.json")
    val jsonString = inputStream.bufferedReader().use { it.readText() }
    return Json.decodeFromString(jsonString)
}

@OptIn(PrivilegedIntent::class)
suspend fun main(args: Array<String>) {
    val bot = Kord(BOT_TOKEN)
    val categories = loadCategories()
    val categoriesCI = categories.mapKeys { it.key.lowercase() }

    // Listening for message create event and acting on commands
    bot.on<MessageCreateEvent> {
        val content = message.content.trim()

        if (message.author?.isBot != false) return@on

        when {
            content.equals("!ping", ignoreCase = true) -> {
                message.channel.createMessage("pong!")
            }
            content.equals("!categories", ignoreCase = true) -> {
                val categoryList = categories.keys.joinToString(", ")
                message.channel.createMessage("Available categories: $categoryList")
            }
            content.startsWith("!products ", ignoreCase = true) -> {
                val categoryName = content.removePrefix("!products ").trim()
                val products = categoriesCI[categoryName.lowercase()]
                if (products != null) {
                    val productList = products.joinToString(", ")
                    message.channel.createMessage("Products in $categoryName: $productList")
                } else {
                    message.channel.createMessage("Category '$categoryName' not found.")
                }
            }
        }
    }

    bot.login {
        @OptIn(PrivilegedIntent::class)
        intents += Intent.MessageContent

    }
}
