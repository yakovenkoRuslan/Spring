package com.nixsolution.controller;

import com.nixsolution.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Objects;

@Controller
@RequestMapping("/home")
public class DeleteController {

    final UserService userService;

    public DeleteController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable("id") long id) {
        if (Objects.equals(userService.findById(id).getLogin(),
                SecurityContextHolder.getContext().getAuthentication()
                        .getName())) {
            userService.delete(id);
            return "redirect:/logout";
        }
        userService.delete(id);
        return "redirect:/home";
    }
}
