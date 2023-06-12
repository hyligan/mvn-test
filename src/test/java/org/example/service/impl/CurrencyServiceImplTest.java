package org.example.service.impl;

import org.example.service.exceptions.NotFoundCurrencyException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;

public class CurrencyServiceImplTest {
    private static CurrencyServiceImpl currencyService;
    @BeforeAll
    public static void init(){
        currencyService=new CurrencyServiceImpl();
    }
    @Test
    public void convertSuccess() throws IOException {
        BigDecimal usd = currencyService.convert(BigDecimal.valueOf(10.0), "USD");
        Assertions.assertEquals(0,usd.compareTo(BigDecimal.valueOf(372.200000)));
    }

    @Test
    public void convertUnsucces() {
        Assertions.assertThrowsExactly(NotFoundCurrencyException.class,() ->{
            currencyService.convert(BigDecimal.valueOf(10.0), "qwe");
        });
    }
}
