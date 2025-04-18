package com.aib.walletmanager.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Table(name = "Incomes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Incomes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idIncome")
    private Integer idIncomes;

    @Column(name = "amountIncome")
    private BigDecimal amountIncome;

    @Column(name = "dateIncome")
    private LocalDateTime dateIncome;


    @PrePersist
    public void prePersist(){
        if(dateIncome == null)
            dateIncome = LocalDateTime.now();
    }
}
