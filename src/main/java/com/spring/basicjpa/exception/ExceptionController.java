package com.spring.basicjpa.exception;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/exceptions")
public class ExceptionController {

    private final ExceptionService exceptionService;


    /**
     *  체크 예외
     */
    @GetMapping("/checked")
    public String checkedExceptionAPI() {
        try {
            exceptionService.throwCheckedException();
        }catch (Exception e) {
            log.info("예외 처리");
        }
        return "checkedException";
    }


    /**
     *  언체크 예외
     */
    @GetMapping("/unchecked")
    private String uncheckedExceptionAPI() {
        exceptionService.throwUncheckedExcpetion();
        return "uncheckedException";
    }

}
