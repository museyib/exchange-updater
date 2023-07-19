package az.inci.exchangeupdater;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ScheduledService
{
    private final WebDataService webDataService;
    private final DataUpdateService dataUpdateService;

    @Scheduled(cron = "0 0 8 * * *")
    public void execute()
    {
        dataUpdateService.updateExchange(webDataService.getExchangeList());
    }
}
