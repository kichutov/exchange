package com.exchange.exchange.models;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
@ToString
@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "source_currency")
    private String sourceCurrency;
    @Column(name = "target_currency")
    private String targetCurrency;
    @Column(name = "source_amount")
    private BigDecimal sourceAmount;
    @Column(name = "target_amount")
    private BigDecimal targetAmount;
    @Column(name = "transaction_date")
    private LocalDate transactionDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;


    public Transaction(){

    }

    public Transaction (String sourceCurrency,
                        String targetCurrency,
                        BigDecimal sourceAmount,
                        BigDecimal targetAmount,
                        LocalDate transactionDate,
                        User user) {
        this.sourceCurrency = sourceCurrency;
        this.targetCurrency = targetCurrency;
        this.sourceAmount = sourceAmount;
        this.targetAmount = targetAmount;
        this.transactionDate = transactionDate;
        this.user = user;
    }

}
