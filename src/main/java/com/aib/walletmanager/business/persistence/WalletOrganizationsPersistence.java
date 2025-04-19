package com.aib.walletmanager.business.persistence;

import com.aib.walletmanager.model.entities.WalletOrganizations;
import com.aib.walletmanager.repository.WalletOrganizationsRepository;
import org.hibernate.Session;

import java.util.List;

public class WalletOrganizationsPersistence {

    private final WalletOrganizationsRepository repository = new WalletOrganizationsRepository();

    public void saveBudgetUnit(WalletOrganizations item, Session session) {
        repository.saveWithoutTransaction(item, session);
    }

    public List<WalletOrganizations> getAll(){
        return repository.findAll();
    }

}
