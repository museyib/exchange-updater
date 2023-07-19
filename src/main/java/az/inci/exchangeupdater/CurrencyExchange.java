package az.inci.exchangeupdater;

import lombok.Data;

@Data
public class CurrencyExchange {
    private String currencyCode;
    private double rate;
}
