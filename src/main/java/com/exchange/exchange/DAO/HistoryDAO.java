package com.exchange.exchange.DAO;

import com.exchange.exchange.models.Transaction;
import com.exchange.exchange.models.User;
import com.exchange.exchange.repositories.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HistoryDAO {

    private final TransactionRepository transactionRepository;

    public HistoryDAO(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public LocalDate getEarlyTransactionDate(User user) {
        List<Transaction> transactionList = transactionRepository.getTransactionByUser(user);
        LocalDate earlyTransactionDate = transactionList
                .stream()
                .map(Transaction::getTransactionDate)
                .min(LocalDate::compareTo)
                .orElse(LocalDate.now());
        return earlyTransactionDate;
    }

    public LocalDate getLatestTransactionDate(User user) {
        List<Transaction> transactionList = transactionRepository.getTransactionByUser(user);
        LocalDate latestTransactionDate = transactionList
                .stream()
                .map(Transaction::getTransactionDate)
                .max(LocalDate::compareTo)
                .orElse(LocalDate.now());
        return latestTransactionDate;
    }

    public List<String> getSourceCurrencyList(User user) {
        List<Transaction> transactionList = transactionRepository.getTransactionByUser(user);
        List<String> sourceCurrencyList = transactionList
                .stream()
                .map(Transaction::getSourceCurrency)
                .distinct()
                .collect(Collectors.toList());
        return sourceCurrencyList;
    }

    public List<String> getTargetCurrencyList(User user) {
        List<Transaction> transactionList = transactionRepository.getTransactionByUser(user);
        List<String> targetCurrencyList = transactionList
                .stream()
                .map(Transaction::getTargetCurrency)
                .distinct()
                .collect(Collectors.toList());
        return targetCurrencyList;
    }
}
