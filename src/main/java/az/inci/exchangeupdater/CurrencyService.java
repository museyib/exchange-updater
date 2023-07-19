package az.inci.exchangeupdater;

import jakarta.persistence.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyService extends AbstractService
{

    public List<String> getCurrencies()
    {
        Query query = em.createNativeQuery("SELECT CURR_CODE FROM CURRENCY WHERE CURR_CODE != 'AZN'");

        return query.getResultList();
    }
}
