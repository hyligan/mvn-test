package org.example.service.impl;

import com.google.gson.Gson;
import org.example.dto.ExchangeDto;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class ExchangeServiceImplTest {
    private static ExchangeServiceImpl exchangeService;
    @BeforeAll
    public static void init(){
        exchangeService = new ExchangeServiceImpl();
    }
    @AfterAll
    public static void end(){
        System.out.println("the end!");
    }
    private Method getResultForGETRequestMethod() throws NoSuchMethodException {
        Method method = exchangeService.getClass().getDeclaredMethod("resultForGETRequest", String.class);
        method.setAccessible(true);
        return method;
    }
    @Test
    void resultForGETRequestSuccess() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String invoke = (String) getResultForGETRequestMethod().invoke(exchangeService, "https://random-data-api.com/api/v2/users?size=5&response_type=json");
        assertNotNull(invoke);
        Object[] objects = new Gson().fromJson(invoke, Object[].class);
        assertEquals(5,objects.length);
    }
    @Test
    void getExchangeSuccess() throws IOException {
        Set<ExchangeDto> exchange = exchangeService.getExchange();
        assertEquals(2,exchange.size());
    }
}
