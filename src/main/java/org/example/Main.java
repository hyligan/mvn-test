package org.example;

import org.apache.log4j.BasicConfigurator;
import org.example.service.CurrencyService;
import org.example.service.impl.CurrencyServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.math.BigDecimal;

public class Main {
  private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);


  public static void main(String[] args) {
    BasicConfigurator.configure();
    CurrencyService currencyService = new CurrencyServiceImpl();
    BigDecimal usd = null;
    try {
      usd = currencyService
              .convert(BigDecimal.valueOf(1.13), "USD");
    } catch (IOException e) {
      LOGGER.error("can't convert! ",e);
    }
    LOGGER.info("item: {}",usd);
  }
}
