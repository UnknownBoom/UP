package com.JSKP.KP.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


@Entity
@Table
@Data
@NoArgsConstructor
public class Card {

    @Id
    private String number;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date issue_date;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date end_date;

    private String owner_name;
    private String owner_surname;
    private String owner_patronymic;

    private Integer cvv2;

    private String password;

    private BigDecimal money;


}
