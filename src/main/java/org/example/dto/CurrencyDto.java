package org.example.dto;

import java.math.BigDecimal;
import java.util.Objects;

public class CurrencyDto {

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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    CurrencyDto that = (CurrencyDto) o;
    return Objects.equals(currency, that.currency);
  }

  @Override
  public int hashCode() {
    return Objects.hash(currency);
  }

  @Override
  public String toString() {
    return "CurrencyDto{" +
            "currency='" + currency + '\'' +
            ", value=" + value +
            '}';
  }
}
