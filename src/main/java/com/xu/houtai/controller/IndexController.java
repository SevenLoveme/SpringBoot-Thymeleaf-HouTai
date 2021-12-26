package com.xu.houtai.controller;

import com.xu.houtai.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import javax.servlet.http.HttpSession;

@Controller
public class IndexController {
    @GetMapping(value = {"/login","/"})
    public String loginPage (){
        return "login";
    }

    /*解决重复提交表单的最好办法是 重定向*/
    @PostMapping("/login")
    public String index(User user, HttpSession session, Model model ){
        if(user.getUsername().equals("xu")&&user.getPassword().equals("111")){
            session.setAttribute("user", user);
            return "redirect:/index.html";
        }else {
            /*重要的事情说三遍  返回Object 返回Object 返回Object */
            model.addAttribute("msg","账号或密码错误");
            return "/login";
        }
    }

    /*跳转到main页面*/
    @GetMapping("/index.html")
    public String main(HttpSession session,Model model){
        //拦截最好使用过滤器
        return "index";
    }
}
