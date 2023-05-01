package arh.bot.pricestalker.adapter.bot;

import arh.bot.pricestalker.adapter.bot.command.Commands;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
@Component
public class MyTelegramBot extends TelegramLongPollingBot {

    private final String token;
    private final String username;

    private final Commands commands;

    public MyTelegramBot(@Value("${telegram-bot.token}") String botToken, @Value("${telegram-bot.username}") String username, @Autowired Commands commands) {
        super(botToken);
        this.token = botToken;
        this.username = username;
        this.commands = commands;
    }

    @Override
    public void onUpdateReceived(Update update) {
        log.info("updating...");
        if (update.hasMessage()) {
            // handle incoming messages
            log.info("Received message");
            log.info("{}", update);
            var msg = update.getMessage();
            var chatId = msg.getChatId();
            log.info("chatId={}", chatId);
            try {
                var reply = "Message received";
                sendNotification(String.valueOf(chatId), reply);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private void sendNotification(String chatId, String msg) throws TelegramApiException {
        var response = new SendMessage(chatId, msg);
        execute(response);
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }
}