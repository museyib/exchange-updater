package az.inci.exchangeupdater;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ExchangeUpdaterApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(ExchangeUpdaterApplication.class, args);
    }
}
