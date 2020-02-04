package com.jiban.exchangerate.update.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "exchangeRate")
public class ExchangeRate {
    @Id
    private String fromCurrency;

    private String toCurrString;

    private double exrate;

}
