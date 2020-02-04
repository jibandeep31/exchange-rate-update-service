package com.jiban.exchangerate.update.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.jiban.exchangerate.update.model.ExchangeRate;
import com.jiban.exchangerate.update.repository.ExchangeRateRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ExchangeRateUpdaterService {

    private final RestTemplate restTemplate;

    private final ExchangeRateRepository exchangeRateRepository;

    @Value("${api.key}")
    private String apiKey;

    @Value("${base.url}")
    private String baseUrl;
    
    @Value("${base.currency}")
    private String baseCurrency;
    
    private static final String ACCESS_KEY = "access_key";
    private static final String BASE_CURRENCY = "base";

    public ExchangeRateUpdaterService(final ExchangeRateRepository exchangeRateRepository) {
        this.exchangeRateRepository = exchangeRateRepository;
        this.restTemplate = new RestTemplate();
    }
    
    public ExchangeRate getExchangeRate(String fromCurrency) {
        return exchangeRateRepository.findById(fromCurrency).get();
    }

    @Scheduled(fixedRateString = "${fixedrate.interval : 40000}")
    public void exchangeRateUpdateService() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        UriComponentsBuilder builder = UriComponentsBuilder
                                        .fromHttpUrl(baseUrl)
                                        .queryParam(ACCESS_KEY, apiKey)
                                        .queryParam(BASE_CURRENCY, baseCurrency);

        HttpEntity<?> entity = new HttpEntity<>(headers);
        HttpEntity<String> response = restTemplate
                                        .exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);

        JSONObject exchangeRateObject = new JSONObject(response.getBody());
        JSONObject rates = exchangeRateObject.getJSONObject("rates");
        log.info("Currencies available are : " + rates.keySet());
        
        rates.keySet()
            .stream()
            .forEach(key -> {
                exchangeRateRepository.save(new ExchangeRate(key.toString().toUpperCase(), 
                                                                baseCurrency, rates.getDouble(key)));
                log.info("Saved : " + key.toString() + " with exrate : " + rates.getDouble(key));
        });
    }

}
