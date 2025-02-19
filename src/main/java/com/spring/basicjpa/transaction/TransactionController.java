package com.spring.basicjpa.transaction;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/transaction")
public class TransactionController {

    private final ApplicationContext applicationContext;

    private final TransactionService2 transactionService2;

    /**
     * 롤백 정책 실습
     */
    @GetMapping("/rollback")
    public void checkRollbackAPI() throws Exception {
        transactionService2.defaultRollbackService();

    }


    /**
     * 프록시 객체 확인 메서드
     */
    @GetMapping("/find-proxy")
    public void checkProxyAPI() {
        Object aopServiceBean = applicationContext.getBean("transactionService");
        Class<?> aClass = aopServiceBean.getClass();
        log.info(aClass.getName());
    }
}
