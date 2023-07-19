package az.inci.exchangeupdater;

import jakarta.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static jakarta.persistence.ParameterMode.IN;

@Service
public class DataUpdateService extends AbstractService
{
    @Transactional
    public void updateExchange(List<CurrencyExchange> exchangeList)
    {
        for(CurrencyExchange exchange : exchangeList)
        {
            StoredProcedureQuery query = em.createStoredProcedureQuery("SP_CREATE_EX_RATE_AUTO");
            query.registerStoredProcedureParameter("CURR_CODE", String.class, IN)
                 .registerStoredProcedureParameter("EX_RATE", Double.class, IN)
                 .setParameter("CURR_CODE", exchange.getCurrencyCode())
                 .setParameter("EX_RATE", exchange.getRate())
                 .executeUpdate();
        }

        em.close();
    }
}
