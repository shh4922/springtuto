package com.example.springtuto1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!");
        return "hello";
    }

    @GetMapping("hello-string")
    @ResponseBody // 응답바디에 return값을 넣어주겠다 라는뜻
    public String helloMvc(@RequestParam("name") String name, Model model) {
        return "hello" + name;
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    @GetMapping("hello-api")
    @ResponseBody // 응답바디에 return값을 넣어주겠다 라는뜻
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return  hello;
    }


}
