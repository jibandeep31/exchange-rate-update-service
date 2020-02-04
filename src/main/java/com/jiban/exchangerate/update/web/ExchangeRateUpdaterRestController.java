package com.jiban.exchangerate.update.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.jiban.exchangerate.update.model.ExchangeRate;
import com.jiban.exchangerate.update.service.ExchangeRateUpdaterService;

@RestController
public class ExchangeRateUpdaterRestController {

    private final ExchangeRateUpdaterService exchangeRateUpdaterService;

    public ExchangeRateUpdaterRestController(final ExchangeRateUpdaterService exchangeRateUpdaterService) {
        this.exchangeRateUpdaterService = exchangeRateUpdaterService;
    }

    @GetMapping("/exchangerateupdate/fromCurrency/{fromCurrency}")
    public ExchangeRate getExchangeRate(@PathVariable("fromCurrency") final String fromCurrency) {
        return exchangeRateUpdaterService.getExchangeRate(fromCurrency);
    }

}
