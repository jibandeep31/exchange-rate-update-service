package com.jiban.exchangerate.update.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jiban.exchangerate.update.model.ExchangeRate;

@Repository
public interface ExchangeRateRepository extends CrudRepository<ExchangeRate, String> {

}
