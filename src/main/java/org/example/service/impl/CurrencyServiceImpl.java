package org.example.service.impl;

import org.example.dto.ExchangeDto;
import org.example.service.CurrencyService;
import org.example.service.ExchangeService;
import org.example.service.exceptions.NotFoundCurrencyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

public class CurrencyServiceImpl implements CurrencyService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyServiceImpl.class);
    private final ExchangeService exchangeService;

    public CurrencyServiceImpl() {
        this.exchangeService = new ExchangeServiceImpl();
    }

    @Override
    public BigDecimal convert(BigDecimal amount, String currency) throws IOException {
        LOGGER.debug("amount {}, currency {}",amount,currency);
        Set<ExchangeDto> exchange = exchangeService.getExchange();
        LOGGER.debug("exchange {}", exchange);
        Optional<ExchangeDto> first =exchange
                .stream()
                .filter(currencyDto -> currencyDto.getCcy().equals(currency)).findFirst();
        AtomicReference<BigDecimal> multiply = new AtomicReference<>();
        first.ifPresentOrElse(
                currencyDto -> multiply.set(currencyDto.getSale().multiply(amount)), ()->{
                    throw new NotFoundCurrencyException("Currency "+currency+" not found in "+Arrays.toString(exchange.toArray()));
                }
        );
        return multiply.get();
    }
}
