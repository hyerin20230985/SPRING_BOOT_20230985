package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.model.domain.TestDB;
import com.example.demo.model.service.TestService; // 최상단 서비스 클래스 연동 추가


@Controller // 컨트롤러 어노테이션 명시
public class DemoController {
    @GetMapping("/hello") //전송 방식 get
    public String hello(Model model) {
        model.addAttribute("data", "반갑습니다."); //model 설정
        return "hello"; //hello.html 연결
    }
    @GetMapping("/hello2")
    public String hello2(Model model) {
        model.addAttribute("data1", "홍길동님.");
        model.addAttribute("dataa", "방갑습니다.");
        model.addAttribute("datab", "오늘.");
        model.addAttribute("datac", "날씨는.");
        model.addAttribute("datad", "매우 좋습니다.");
        return "hello2";
    }

    @GetMapping("/about_detailed")
    public String about(Model model) {
        return "about_detailed";
    }

    @GetMapping("/index_copy")
    public String index(Model model) {
        return "index copy";
    }

    @GetMapping("/test1")
    public String thymeleaf_test1(Model model) {
        model.addAttribute("data11", "<h2> 방갑습니다 </h2>");
        model.addAttribute("data2", "태그의 속성 값");
        model.addAttribute("link", 01);
        model.addAttribute("name", "김혜린");
        model.addAttribute("para1", "001");
        model.addAttribute("para2", 002);
        return "thymeleaf_test1";
    } 

    @Autowired
    TestService testService; // DemoController 클래스 아래 객체 생성

    @GetMapping("/testdb")
    public String getAllTestDBs(Model model) {
        TestDB test = testService.findByName("홍길동");
        TestDB test2 = testService.findByName("아저씨");
        TestDB test3 = testService.findByName("꾸러기");

        model.addAttribute("data4", test);
        model.addAttribute("data5", test2);
        model.addAttribute("data6", test3);

        System.out.println("데이터 출력 디버그 : " + test);
        System.out.println("데이터 출력 디버그 : " + test2);
        System.out.println("데이터 출력 디버그 : " + test3);
        return "testdb";
 }
 
    @GetMapping("/demo_article_list")
    public String article_list() {
        return "article_list";
    }
}


