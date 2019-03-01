package jee.support.controller;

import jee.support.constants.OpCode;
import jee.support.constants.Constants;
import jee.support.entity.User;
import jee.support.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController{
    @Autowired
    UserService userService;
    //身份验证
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpServletRequest request
                        ){
        User user  = userService.authenticate(username, password);
        if (user==null){
            request.setAttribute(Constants.LOGIN_STATUS, OpCode.INVALID_USER);
            return "login";
        }else {
            request.changeSessionId();
            request.getSession().setAttribute(Constants.SESSION_USER, user);
            return "redirect:tickets/list";
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session){
            session.invalidate();
            return "redirect:login";
        }

}