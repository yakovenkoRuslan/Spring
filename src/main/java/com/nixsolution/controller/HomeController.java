package com.nixsolution.controller;

import com.nixsolution.entity.User;
import com.nixsolution.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Objects;

@Controller
public class HomeController {

    final UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String displayHomePage(Model model) {
        String login = SecurityContextHolder.getContext().getAuthentication()
                .getName();
        String roleName = userService.findByLogin(login).getRole().getName();
        model.addAttribute("login", login);
        if (Objects.equals(roleName, "user")) {
            return "user_page";
        } else {
            model.addAttribute("listOfUsers", getAllUsers());
            return "admin_page";
        }
    }

    private List<User> getAllUsers() {
        List<User> list = userService.findAll();
        for (User user : list) {
            user.setPassword(null);
        }
        return list;
    }
}
