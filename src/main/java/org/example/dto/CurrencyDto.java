package org.example.dto;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CurrencyDto {
  private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyDto.class);

  private String currency;
  private BigDecimal value;

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public BigDecimal getValue() {
    return value;
  }

  public void setValue(BigDecimal value) {
    this.value = value;
  }

  public void log(){
    LOGGER.info("currency = {}, value = {}",currency,value);
  }
}
