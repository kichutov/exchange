package com.exchange.exchange.controllers;

import com.exchange.exchange.DAO.CurrencyDAO;
import com.exchange.exchange.models.User;
import com.exchange.exchange.services.ExchangeService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Controller
public class ExchangeController {

    private final ExchangeService exchangeService;
    private final CurrencyDAO currencyDAO;

    public ExchangeController(ExchangeService exchangeService,
                              CurrencyDAO currencyDAO) {
        this.exchangeService = exchangeService;
        this.currencyDAO = currencyDAO;
    }

    @GetMapping("/")
    public String exchangePage(
            @AuthenticationPrincipal User user,
            @RequestParam(value = "sourceCurrency", required = false) String sourceCurrency,
            @RequestParam(value = "targetCurrency", required = false) String targetCurrency,
            @RequestParam(value = "sourceAmount", required = false) BigDecimal sourceAmount,
            Model model) {

        model.addAttribute("currencyNameList", currencyDAO.getCurrencyNameList());
        model.addAttribute("sourceCurrency", sourceCurrency);
        model.addAttribute("targetCurrency", targetCurrency);
        model.addAttribute("sourceAmount", sourceAmount);

        if (!(sourceCurrency == null || targetCurrency == null || sourceAmount == null)) {
            BigDecimal targetAmount = exchangeService.getExchangeResult(
                    user,
                    sourceCurrency,
                    targetCurrency,
                    sourceAmount);
            model.addAttribute("targetAmount",
                    targetAmount.compareTo(new BigDecimal("0.00")) == 0  ? "меньше 0.00" : targetAmount);
        }

        return "exchange";
    }
}
