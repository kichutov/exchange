package com.exchange.exchange.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@ToString
@Table (name = "currency")
public class Currency {

    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "num_code")
    private Integer numCode;
    @Column(name = "char_code")
    private String charCode;
    @Column(name = "nominal")
    private BigDecimal nominal;
    @Column(name = "name")
    private String name;
    @Column(name = "value")
    private BigDecimal value;
    @Column(name = "update_date")
    private LocalDate updateDate;

}
