package com.exchange.exchange.controllers;

import com.exchange.exchange.DAO.CurrencyDAO;
import com.exchange.exchange.DAO.HistoryDAO;
import com.exchange.exchange.models.Transaction;
import com.exchange.exchange.models.User;
import com.exchange.exchange.repositories.TransactionRepository;
import com.exchange.exchange.services.ExchangeService;
import com.exchange.exchange.services.HistoryService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HistoryController {

    private final HistoryDAO historyDAO;
    private final HistoryService historyService;

    public HistoryController(HistoryDAO historyDAO,
                             HistoryService historyService) {
        this.historyDAO = historyDAO;
        this.historyService = historyService;
    }

    @GetMapping("/history")
    public String history(
            @AuthenticationPrincipal User user,
            @RequestParam(value = "selectedDate", required = false) String selectedDate,
            @RequestParam(value = "sourceCurrency", required = false) String sourceCurrency,
            @RequestParam(value = "targetCurrency", required = false) String targetCurrency,
            Model model) {
        model.addAttribute("earlyTransactionDate", historyDAO.getEarlyTransactionDate(user));
        model.addAttribute("latestTransactionDate", historyDAO.getLatestTransactionDate(user));

        List<String> sourceCurrencyList = historyService.getSourceCurrencyList(user);
        List<String> targetCurrencyList = historyService.getTargetCurrencyList(user);

        model.addAttribute("sourceCurrencyList", sourceCurrencyList);
        model.addAttribute("targetCurrencyList", targetCurrencyList);

        model.addAttribute("sourceCurrency", sourceCurrency);
        model.addAttribute("targetCurrency", targetCurrency);
        model.addAttribute("selectedDate", selectedDate);

        if (!(selectedDate == null || sourceCurrency == null || targetCurrency == null)) {
            List<Transaction> transactionList = historyService.getTransactionList(user, LocalDate.parse(selectedDate), sourceCurrency, targetCurrency);
            model.addAttribute("transactionList", transactionList);
        }

        return "history";
    }
}
