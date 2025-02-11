package com.spring.basicjpa.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ExceptionService {

    /**
     * 체크 예외 발생
     */
    public void throwCheckedException() throws Exception {

        throw new Exception(); // 예외 발생

//       //예외 직접 처리
//        try {
//            throw new Exception();
//        } catch (Exception e) {
//             log.info("예외 처리");
//        }
    }


    /**
     * 언체크 예외 발생
     */
    public void throwUncheckedExcpetion() {
        throw new RuntimeException();
    }
}
