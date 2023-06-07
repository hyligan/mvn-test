package org.example;

import com.google.gson.Gson;
import org.apache.log4j.BasicConfigurator;
import org.example.dto.CurrencyDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
  private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);


  public static void main(String[] args) {
    BasicConfigurator.configure();
    String cur = "{\n"
        + "    \"currency\": \"USD\",\n"
        + "    \"value\": \"4.05\"\n"
        + "}";
    CurrencyDto item = new Gson().fromJson(cur, CurrencyDto.class);
    item.log();
    LOGGER.info("the end");
  }
}
