package org.example.discord

import discord4j.core.DiscordClient
import io.ktor.server.application.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// Zastąp tokenem swojego bota Discord
const val DISCORD_BOT_TOKEN = "MTM1NjAxMjQ0NzkzMjM1NDcwMA.Gbk08O.e7mgyFThH5VbpEm6JYjdCxsVvs5mrahc07N4Lk"

// Inicjalizacja klienta Discorda (można to zrobić globalnie lub przy każdym wysłaniu)
private val discordClient = DiscordClient.create(DISCORD_BOT_TOKEN)

fun Application.configureDiscord() {
    CoroutineScope(Dispatchers.IO).launch {
        discordClient.login().block()
    }
}

// Funkcja do wysyłania wiadomości na Discord
suspend fun sendDiscordMessage(channelId: Long, messageContent: String) {
    try {
        // Upewnij się, że klient jest zalogowany
        if (!discordClient.isLoggedIn) {
            discordClient.login().block()
        }

        val channel = discordClient.rest().getChannelById(discord4j.common.util.Snowflake.of(channelId)).block()
        if (channel is discord4j.core.`object`.entity.channel.TextChannel) {
            channel.createMessage(messageContent).block()
            println("Wiadomość wysłana na kanał Discord o ID: $channelId")
        } else {
            println("Nie można wysłać wiadomości na kanał o ID: $channelId. Sprawdź, czy ID jest poprawne i czy bot ma dostęp do tego kanału.")
        }
    } catch (e: Exception) {
        println("Wystąpił błąd podczas wysyłania wiadomości na Discord: ${e.message}")
        e.printStackTrace()
    } finally {
        // Można rozważyć wylogowanie klienta po wysłaniu, jeśli nie planujesz ciągłej interakcji
        // discordClient.logout().block()
    }
}