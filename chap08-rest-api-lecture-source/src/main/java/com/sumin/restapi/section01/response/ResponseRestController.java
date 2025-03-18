package com.sumin.restapi.section01.response;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/* 설명. @RestController 어노테이션을 통해 Controller의 모든 핸들러 메소드에 @ResponseBody 를 적용한 효과 */
/* 설명. 이제 더이상 view resolver가 처리하지 않는다 (어떤 반환 값이든 반환 됨) */
@RestController
@RequestMapping("/response")
public class ResponseRestController {

    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping("/random")
    public int getRandomNumber() {
        return (int) (Math.random() * 10) + 1;
    }
}
