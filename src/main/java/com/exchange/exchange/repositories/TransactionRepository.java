package com.exchange.exchange.repositories;

import com.exchange.exchange.models.Transaction;
import com.exchange.exchange.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> getTransactionByUser(User user);
    List<Transaction> getTransactionsByUserAndTransactionDateAndSourceCurrencyAndTargetCurrency(User user, LocalDate selectedDate, String sourceCurrency, String targetCurrency);

    List<Transaction> getTransactionsByUserAndTransactionDateAndSourceCurrency(User user, LocalDate selectedDate, String sourceCurrency);
    List<Transaction> getTransactionByUserAndTransactionDateAndTargetCurrency(User user, LocalDate selectedDate, String targetCurrency);
    List<Transaction> getTransactionByUserAndTransactionDate(User user, LocalDate selectedDate);
}
