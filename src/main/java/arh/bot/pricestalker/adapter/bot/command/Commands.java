package arh.bot.pricestalker.adapter.bot.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Commands {

    private final List<BotComand> commands;

}
