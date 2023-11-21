package com.eazybytes.accounts.controller;

import com.eazybytes.accounts.constants.AccountsConstant;
import com.eazybytes.accounts.dto.CustomerDTO;
import com.eazybytes.accounts.dto.ResponseDTO;
import com.eazybytes.accounts.entity.BaseEntity;
import com.eazybytes.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api",produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class AccountController {

private IAccountsService iAccountsService;

    @PostMapping("/create")
   public ResponseEntity<ResponseDTO> createAccount(@RequestBody CustomerDTO customerDTO){
        iAccountsService.creatAccount(customerDTO);
    return ResponseEntity.status(HttpStatus.CREATED)
            .body(new ResponseDTO(AccountsConstant.STATUS_201,AccountsConstant.MESSAGE_201));
   }
}
