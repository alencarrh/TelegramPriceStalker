package arh.bot.pricestalker;

import arh.bot.pricestalker.adapter.bot.MyTelegramBot;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Slf4j
@SpringBootApplication
public class PriceStalkerArhBotApplication {

    @Autowired
    private MyTelegramBot myTelegramBot;

    public static void main(String[] args) {
        SpringApplication.run(PriceStalkerArhBotApplication.class, args);

    }

    @PostConstruct
    public void startBot() {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(myTelegramBot);
        } catch (TelegramApiException e) {
            log.error("Error on register bot");
        }
    }

}
