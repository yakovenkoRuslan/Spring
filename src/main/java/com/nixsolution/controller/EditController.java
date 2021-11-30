package com.nixsolution.controller;

import com.nixsolution.entity.Role;
import com.nixsolution.entity.User;
import com.nixsolution.service.RoleService;
import com.nixsolution.service.UserService;
import com.nixsolution.user.AddEditUserImpl;
import com.nixsolution.util.FieldsValidator;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/home")
public class EditController {

    private static final String EMAIL_ERROR = "Email exists or wrong email";

    final RoleService roleService;

    final UserService userService;

    public EditController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String displayEditPage(@PathVariable("id") long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("id", id);
        model.addAttribute("login", user.getLogin());
        return "edit_page";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String editUser(@PathVariable("id") long id,
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
        if (!isValidFields(password, confirmPassword, email, firstName,
                latsName, date, role, customErrors)) {
            model.addAttribute("errors", customErrors);
            return "edit_page";
        }
        editUserDataBase(id, password, email, firstName, latsName, date, role);
        if (Objects.equals(getUserLoginById(id),
                SecurityContextHolder.getContext().getAuthentication()
                        .getName())) {
            return "redirect:/logout";
        }
        return "redirect:/home";
    }

    private String getUserLoginById(long id) {
        return userService.findById(id).getLogin();
    }

    private boolean isValidFields(String password, String confirmPassword,
            String email, String firstName, String lastName, String date,
            String role, List<String> customErrors) {
        boolean answer;

        password = FieldsValidator.configureFields(password);
        confirmPassword = FieldsValidator.configureFields(confirmPassword);
        email = FieldsValidator.configureFields(email);
        firstName = FieldsValidator.configureFields(firstName);
        lastName = FieldsValidator.configureFields(lastName);
        date = FieldsValidator.configureFields(date);
        role = FieldsValidator.configureFields(role);

        answer = new AddEditUserImpl().isValidFormFields(null, password,
                confirmPassword, email, firstName, lastName, date, role,
                customErrors);
        if (userService.findByEmail(email) != null) {
            answer = false;
            customErrors.set(3, EMAIL_ERROR);
        }
        return answer;
    }

    private void editUserDataBase(long id, String password, String email,
            String firstName, String lastName, String date, String role) {
        User user = userService.findById(id);
        Role roleUser = roleService.findRoleByName(
                FieldsValidator.setCorrectValue(role,
                        user.getRole().getName()));
        User newUser = new AddEditUserImpl().editUser(user.getLogin(), password,
                email, firstName, lastName, date, roleUser, user);
        userService.update(newUser);
    }

}
