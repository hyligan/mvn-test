package org.example.service;

import java.io.IOException;
import java.math.BigDecimal;

public interface CurrencyService {
    BigDecimal convert(BigDecimal amount, String currency) throws IOException;
}
