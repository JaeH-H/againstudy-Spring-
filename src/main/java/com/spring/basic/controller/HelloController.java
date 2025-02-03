package com.spring.basic.controller;


import com.spring.basic.service.HelloService;
import org.apache.catalina.Service;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    //속성
    private final HelloService helloService;

    //생성자
    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }


    //기능

    @Component
    public class InitializingBeanExample implements InitializingBean {
        @Override
        public void afterPropertiesSet() throws Exception {
            System.out.println("InitializingBean: 초기화 작업 실행");
        }
    }

    @Component
    public class DisposableBeanExample implements DisposableBean {
        @Override
        public void destroy() throws Exception {
            System.out.println("DisposableBean: 소멸 작업 실행");
        }
    }

    @GetMapping()
    public String sayHelloController() {
        helloService.method();

        return "hello";
    }
}
