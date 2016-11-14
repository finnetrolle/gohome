package ru.finntrolle.telegram

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.telegram.telegrambots.TelegramBotsApi
import org.telegram.telegrambots.api.methods.send.SendMessage
import org.telegram.telegrambots.api.objects.Update
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.exceptions.TelegramApiException
import javax.annotation.PostConstruct

@Component
open class TelegramBot {

    private val log = LoggerFactory.getLogger(TelegramBot::class.java)

    @Value("\${telegram.token}")
    private lateinit var token: String

    @Value("\${telegram.botname}")
    private lateinit var name: String

    private var api = object : TelegramLongPollingBot() {
        override fun getBotUsername() = name

        override fun getBotToken() = token

        override fun onUpdateReceived(update: Update) {
            if (update.hasMessage()) {
                val msg = update.message
                log.info("Received message [${msg.text}] from {${msg.chatId}}")
                val message = SendMessage()
                message.chatId = msg.chatId.toString()
                message.text = msg.text
                sendMessage(message)
                println(message.text)
            } else {
                log.debug("Some received $update")
            }
        }
    }

    @PostConstruct
    open fun init() {
        log.info("STARTING BOT")
        try {
            TelegramBotsApi().registerBot(api)
        } catch (e: TelegramApiException) {
            log.error("Error $e")
        }
    }

}