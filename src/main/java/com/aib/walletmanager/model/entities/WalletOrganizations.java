package com.aib.walletmanager.model.entities;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "WalletOrganizations")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class WalletOrganizations {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idOrganizer")
    private Integer idWalletOrganization;

    @Column(name = "OrganizerName")
    private String organizationName;

    @Column(name = "budgetAsigned")
    private BigDecimal budgetAssigned;

    @Column(name = "percentageFromWallet")
    private Double percentageFromWallet;

    @Column(name = "creationOrganization")
    private LocalDateTime creationOrganization;

    @Column(name = "startDuration")
    private LocalDateTime startDuration;

    @Column(name = "endDuration")
    private LocalDateTime endDuration;

    @Column(name = "idWalletCategory")
    private Integer idWalletCategory;

    @Column(name = "idDuration")
    private Integer idDuration;

    @Column(name = "idWallet")
    private Integer idWallet;
}
