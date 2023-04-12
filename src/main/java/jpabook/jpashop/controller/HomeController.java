package jpabook.jpashop.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j //lombok 기능. 자동으로 로그를 생성하는 데 사용됩니다.

public class HomeController {
    @RequestMapping("/") //
    public String home() {
        log.info("home controller");
        return "home";
    }
}
