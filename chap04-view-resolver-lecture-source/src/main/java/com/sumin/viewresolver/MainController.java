package com.sumin.viewresolver;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/*") // 안 적어도 무방하나 구분을 위해 적어주면 좋다
public class MainController {

    /* 설명. '/' 와 '/main' 모두 main.html 페이지로 연결시킴 */
//    @RequestMapping("/")
//    public String defaultLocation() {
//        return "main";
//    }

//    @RequestMapping("/main")
//    public void main(){
//    }

    @RequestMapping(value = {"/", "/main"})
    public String main(){
        return "main";
    }
}
