package com.spring.basicjpa.transaction;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionService {

    @Transactional
    public void methodA() {}

}
