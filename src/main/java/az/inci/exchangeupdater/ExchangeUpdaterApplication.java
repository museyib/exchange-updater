package az.inci.exchangeupdater;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@RequiredArgsConstructor
public class ExchangeUpdaterApplication
{
    private final WebDataService webDataService;
    private final DataUpdateService dataUpdateService;
    public static void main(String[] args)
    {
        SpringApplication.run(ExchangeUpdaterApplication.class, args);
    }

    @Bean
    public CommandLineRunner executeAtStartup()
    {
        return args -> dataUpdateService.updateExchange(webDataService.getExchangeList());
    }
}
