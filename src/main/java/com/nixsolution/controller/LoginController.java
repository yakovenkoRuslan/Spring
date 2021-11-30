package com.nixsolution.controller;

import com.nixsolution.entity.Role;
import com.nixsolution.entity.User;
import com.nixsolution.service.RoleService;
import com.nixsolution.service.UserService;
import com.nixsolution.user.AddEditUserImpl;
import com.nixsolution.util.VerifyUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Controller
public class LoginController {

    private static final String EMAIL_ERROR = "Email exists or wrong email";
    private static final String LOGIN_ERROR = "Wrong login, or login exists";
    private static final String CAPTCHA_ERROR = "Please verify captcha!";

    final BCryptPasswordEncoder bCryptPasswordEncoder;

    final RoleService roleService;

    final UserService userService;

    public LoginController(UserService userService, RoleService roleService,
            BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @RequestMapping(value = "/login")
    String getLoginPage() {
        return "login_page";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    String getRegistrationPage() {
        return "registration_page";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    String createNewUser(@ModelAttribute("login") String login,
            @ModelAttribute("password") String password,
            @ModelAttribute("confirmPassword") String confirmPassword,
            @ModelAttribute("email") String email,
            @ModelAttribute("firstName") String firstName,
            @ModelAttribute("lastName") String latsName,
            @ModelAttribute("date") String date,
            @ModelAttribute("role") String role,
            @ModelAttribute("g-recaptcha-response") String captcha, Model model,
            RedirectAttributes redirectAttributes) {
        List<String> customErrors = Arrays.asList(null, null, null, null, null,
                null, null);
        String gRecaptchaResponse = Objects.requireNonNull(captcha);
        boolean valid = VerifyUtils.verify(gRecaptchaResponse);
        if (!valid) {
            model.addAttribute("message", CAPTCHA_ERROR);
            return "registration_page";
        }
        if (!isValidFields(login, password, confirmPassword, email, firstName,
                latsName, date, role, customErrors)) {
            model.addAttribute("errors", customErrors);
            return "registration_page";
        }
        addUserDataBase(login, password, email, firstName, latsName, date,
                role);

        return "redirect:/login";
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
