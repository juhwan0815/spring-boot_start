package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data","spring!!");
        return "hello";
    }


    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name",name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name){
        return "hello" + name; // 이문자가 그대로 내려감 html이 아니라 그냥 문자가 나간다
    }

    @GetMapping("hello-api")
    @ResponseBody // json으로 반환하는 게 기본
    public  Hello helloApi(@RequestParam("name") String name){ //api json 방식
        Hello hello = new Hello(); // 객체를 반환하면 기본 디폴트가 json방식으로 만들어서 나간다
        hello.setName(name);
        return hello;


    }

    static class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
