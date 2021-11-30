package com.nixsolution.controller;

import com.nixsolution.entity.Role;
import com.nixsolution.entity.User;
import com.nixsolution.service.RoleService;
import com.nixsolution.service.UserService;
import com.nixsolution.user.AddEditUserImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/home")
public class AddController {

    private static final String EMAIL_ERROR = "Email exists or wrong email";
    private static final String LOGIN_ERROR = "Wrong login, or login exists";

    final RoleService roleService;

    final UserService userService;

    final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AddController(UserService userService, RoleService roleService,
            BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String getAddPage() {
        return "add_page";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("login") String login,
            @ModelAttribute("password") String password,
            @ModelAttribute("confirmPassword") String confirmPassword,
            @ModelAttribute("email") String email,
            @ModelAttribute("firstName") String firstName,
            @ModelAttribute("lastName") String latsName,
            @ModelAttribute("date") String date,
            @ModelAttribute("role") String role, Model model,
            RedirectAttributes redirectAttributes) {
        List<String> customErrors = Arrays.asList(null, null, null, null, null,
                null, null);
        if (!isValidFields(login, password, confirmPassword, email, firstName,
                latsName, date, role, customErrors)) {
            model.addAttribute("errors", customErrors);
            return "add_page";
        }
        addUserDataBase(login, password, email, firstName, latsName, date,
                role);
        return "redirect:/home";
    }

    private boolean isValidFields(String login, String password,
            String confirmPassword, String email, String firstName,
            String lastName, String date, String role,
            List<String> customErrors) {
        boolean answer;

        answer = new AddEditUserImpl().isValidFormFields(login, password,
                confirmPassword, email, firstName, lastName, date, role,
                customErrors);

        if (userService.findByLogin(login) != null) {
            answer = false;
            customErrors.set(0, LOGIN_ERROR);
        }

        if (userService.findByEmail(email) != null) {
            answer = false;
            customErrors.set(3, EMAIL_ERROR);
        }
        return answer;
    }

    private void addUserDataBase(String login, String password, String email,
            String firstName, String lastName, String date, String role) {
        Role roleUser = roleService.findRoleByName(role);
        password = bCryptPasswordEncoder.encode(password);
        User user = new AddEditUserImpl().createUser(login, password, email,
                firstName, lastName, date, roleUser);
        userService.save(user);
    }
}
