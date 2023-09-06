package com.example.dogsproject.apisender;


import kong.unirest.GenericType;
import kong.unirest.Unirest;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ApiSender {
    public <T> T get(String url, Map<String, String> headers, GenericType<T> genericType) {
        return Unirest
            .get(url)
            .headers(headers)
            .asObject(genericType)
            .getBody();
    }
}
