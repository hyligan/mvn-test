package org.example.service.impl;

import org.example.dto.ExchangeDto;
import org.example.service.CurrencyService;
import org.example.service.ExchangeService;
import org.example.service.exceptions.NotFoundCurrencyException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

public class CurrencyServiceImplTest {
    private static CurrencyServiceImpl currencyService;
    private static CurrencyServiceImpl currencyServiceWithMock;
    private static ExchangeService exchangeServiceMock;

    private static CurrencyService currencyServiceMock;

    @BeforeAll
    public static void init(){
        currencyService=new CurrencyServiceImpl();
        exchangeServiceMock = Mockito.mock(ExchangeService.class);
        currencyServiceMock = Mockito.mock(CurrencyService.class);
        currencyServiceWithMock = new CurrencyServiceImpl(exchangeServiceMock);
    }
    @Test
    void convertSuccess() {
        Assertions.assertDoesNotThrow(()->{
            currencyService.convert(BigDecimal.valueOf(10.0), "USD");
        });
    }

    @Test
    void convertUnsucces() {
        Assertions.assertThrowsExactly(NotFoundCurrencyException.class,() ->{
            currencyService.convert(BigDecimal.valueOf(10.0), "qwe");
        });
    }
    @Test
    void convertSuccessWithMock() throws IOException {
        Set<ExchangeDto> exchangeDtos = new LinkedHashSet<>();
        exchangeDtos.add(new ExchangeDto("USD","UAH",BigDecimal.valueOf(12),BigDecimal.valueOf(11)));
        exchangeDtos.add(new ExchangeDto("EUR","UAH",BigDecimal.valueOf(13),BigDecimal.valueOf(12)));
        Mockito.when(exchangeServiceMock.getExchange()).thenReturn(exchangeDtos);
        BigDecimal usd = currencyServiceWithMock.convert(BigDecimal.valueOf(10.0), "USD");
        BigDecimal eur = currencyServiceWithMock.convert(BigDecimal.valueOf(10.0), "EUR");
        Assertions.assertEquals(0,usd.compareTo(BigDecimal.valueOf(110.0)));
        Assertions.assertEquals(0,eur.compareTo(BigDecimal.valueOf(120.0)));
    }

    @Test
    void convertSuccessMock() throws IOException {
        Set<ExchangeDto> exchangeDtos = new LinkedHashSet<>();
        exchangeDtos.add(new ExchangeDto("USD","UAH",BigDecimal.valueOf(12),BigDecimal.valueOf(11)));
        exchangeDtos.add(new ExchangeDto("EUR","UAH",BigDecimal.valueOf(13),BigDecimal.valueOf(12)));
        Mockito.when(currencyServiceMock.convert(BigDecimal.valueOf(10.0), "USD")).thenReturn(BigDecimal.valueOf(123));
        Mockito.when(currencyServiceMock.convert(BigDecimal.valueOf(10.0), "EUR")).thenReturn(BigDecimal.valueOf(1234));
        BigDecimal usd = currencyServiceMock.convert(BigDecimal.valueOf(10.0), "USD");
        BigDecimal eur = currencyServiceMock.convert(BigDecimal.valueOf(10.0), "EUR");
        Assertions.assertEquals(0,usd.compareTo(BigDecimal.valueOf(123)));
        Assertions.assertEquals(0,eur.compareTo(BigDecimal.valueOf(1234)));
    }

    @Test
    void convertUnsuccessfulMock() throws IOException {
        Set<ExchangeDto> exchangeDtos = new LinkedHashSet<>();
        exchangeDtos.add(new ExchangeDto("USD","UAH",BigDecimal.valueOf(12),BigDecimal.valueOf(11)));
        exchangeDtos.add(new ExchangeDto("EUR","UAH",BigDecimal.valueOf(13),BigDecimal.valueOf(12)));
        Mockito.when(currencyServiceMock.convert(BigDecimal.valueOf(10.0), "USD")).thenThrow(NotFoundCurrencyException.class);
        BigDecimal bigDecimal = BigDecimal.valueOf(10.0);
        try {
            currencyServiceMock.convert(bigDecimal, "USD");
            Assertions.fail("Expected an NotFoundCurrencyException to be thrown");
        }catch (NotFoundCurrencyException e){
            Assertions.assertNull(e.getMessage());
        }
    }

}
