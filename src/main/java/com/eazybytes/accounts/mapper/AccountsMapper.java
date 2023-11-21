package com.eazybytes.accounts.mapper;

import com.eazybytes.accounts.dto.AccountsDTO;
import com.eazybytes.accounts.entity.Accounts;

public class AccountsMapper {
    public static AccountsDTO mapToAccountsDto(Accounts accounts, AccountsDTO accountsDto) {
        accountsDto.setAccountNumber(accounts.getAccountNumber());
        accountsDto.setAccountType(accounts.getAccountType());
        accountsDto.setBranchAddress(accounts.getBranchAddress());
        return accountsDto;
    }

    public static Accounts mapToAccounts(AccountsDTO accountsDTO, Accounts accounts){
        accounts.setAccountNumber(accountsDTO.getAccountNumber());
        accounts.setAccountType(accountsDTO.getAccountType());
        accounts.setAccountType(accountsDTO.getBranchAddress());
        return accounts;
    }
}
