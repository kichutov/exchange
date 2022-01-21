package com.exchange.exchange.repositories;

import com.exchange.exchange.models.Currency;
import com.exchange.exchange.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {
    Currency findCurrencyByCharCode(String charCode);
}
