package com.exchange.exchange.services;

import com.exchange.exchange.DAO.HistoryDAO;
import com.exchange.exchange.models.Transaction;
import com.exchange.exchange.models.User;
import com.exchange.exchange.repositories.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class HistoryService {

    private final TransactionRepository transactionRepository;
    private final HistoryDAO historyDAO;

    public HistoryService(TransactionRepository transactionRepository,
                          HistoryDAO historyDAO) {
        this.transactionRepository = transactionRepository;
        this.historyDAO = historyDAO;
    }

    public List<Transaction> getTransactionList(User user, LocalDate selectedDate, String sourceCurrency, String targetCurrency) {
        List<Transaction> transactionList;
        if (sourceCurrency.equals("Все валюты") && targetCurrency.equals("Все валюты")) {
            transactionList = transactionRepository.getTransactionByUserAndTransactionDate(user, selectedDate);
        } else if (sourceCurrency.equals("Все валюты")) {
            transactionList = transactionRepository.getTransactionByUserAndTransactionDateAndTargetCurrency(user, selectedDate, targetCurrency);
        } else if (targetCurrency.equals("Все валюты")) {
            transactionList = transactionRepository.getTransactionsByUserAndTransactionDateAndSourceCurrency(user, selectedDate, sourceCurrency);
        } else {
            transactionList = transactionRepository.getTransactionsByUserAndTransactionDateAndSourceCurrencyAndTargetCurrency(user, selectedDate, sourceCurrency, targetCurrency);
        }
        return transactionList;
    }

    public List<String> getSourceCurrencyList(User user) {
        List<String> sourceCurrencyList = new ArrayList<>();
        sourceCurrencyList.add("Все валюты");
        sourceCurrencyList.addAll(historyDAO.getSourceCurrencyList(user));
        return sourceCurrencyList;
    }

    public List<String> getTargetCurrencyList(User user) {
        List<String> targetCurrencyList = new ArrayList<>();
        targetCurrencyList.add("Все валюты");
        targetCurrencyList.addAll(historyDAO.getTargetCurrencyList(user));
        return targetCurrencyList;
    }

}
