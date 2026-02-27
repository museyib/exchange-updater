package az.inci.exchangeupdater;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class WebDataService
{
    @Autowired
    private CurrencyService currencyService;
    public List<CurrencyExchange> getExchangeList()
    {

        List<CurrencyExchange> exchangeList = new ArrayList<>();
        List<String> currencies = currencyService.getCurrencies();
        System.out.println(currencies);
        Document doc;
        try {
            doc = Jsoup.connect("https://cbar.az/currency/rates").get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        Elements dataTable = doc.select(".table_items");
        for (Element element : dataTable.select(".table_row")) {
            String currencyCode = element.select(".kod").get(0).text().toUpperCase();
            double unitOfCurrency;
            try {
                unitOfCurrency = Double.parseDouble(element.select(".valuta").get(0).text().split(" ")[0]);
            }
            catch (NumberFormatException e) {
                unitOfCurrency = 1;
            }
            double rate = Double.parseDouble(element.select(".kurs").get(0).text()) / unitOfCurrency;
            if (currencies.contains(currencyCode))
            {
                System.out.println(element.select(".valuta").get(0).text() + " - " + element.select(".kurs").get(0).text());
                CurrencyExchange exchange = new CurrencyExchange();
                exchange.setCurrencyCode(currencyCode);
                exchange.setRate(rate);
                exchangeList.add(exchange);
            }
        }

        return exchangeList;
    }
}
