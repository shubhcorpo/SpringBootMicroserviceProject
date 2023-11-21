package com.eazybytes.accounts.service.impl;

import com.eazybytes.accounts.constants.AccountsConstant;
import com.eazybytes.accounts.dto.CustomerDTO;
import com.eazybytes.accounts.entity.Accounts;
import com.eazybytes.accounts.entity.Customer;
import com.eazybytes.accounts.exception.CustomerAlreadyExistsException;
import com.eazybytes.accounts.mapper.CustomerMapper;
import com.eazybytes.accounts.repository.AccountRepository;
import com.eazybytes.accounts.repository.CustomerRepository;
import com.eazybytes.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountsService{

    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;

    @Override
    public void creatAccount(CustomerDTO customerDTO) {
     Customer customer= CustomerMapper.mapToCustomer(customerDTO, new Customer());
     if(!isAlreadyACustomer(customer)) {
         customer.setCreatedAt(LocalDateTime.now());
         customer.setCreatedBy("By API");
         Customer savedCustomer = customerRepository.save(customer);
         accountRepository.save(createNewAccount(savedCustomer));
     }
     else{
         throw new CustomerAlreadyExistsException("Customer already registered with the mobile number:"+""
                 +customerDTO.getMobileNumber());
     }

    }

    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount= new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber= 1000000000L + new Random().nextInt(900000000);
        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstant.SAVINGS);
        newAccount.setBranchAddress(AccountsConstant.ADDRESS);
        newAccount.setCreatedAt(LocalDateTime.now());
        newAccount.setCreatedBy("By API");

        return newAccount;
    }

    private boolean isAlreadyACustomer(Customer customer) {
        Optional<Customer> fetchedCustomer = customerRepository.findByMobileNumber(customer.getMobileNumber());
      return fetchedCustomer.isPresent() ? true : false;
    }
}
