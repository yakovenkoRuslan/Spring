package com.nixsolution.user;

import com.nixsolution.entity.Role;
import com.nixsolution.entity.User;
import com.nixsolution.util.FieldsValidator;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

public class AddEditUserImpl implements AddEditUser {

    private static final String LOGIN_ERROR = "Wrong login, or login exists";
    private static final String PASSWORD_ERROR = "Wrong password";
    private static final String CONFIRM_PASSWORD_ERROR = "Password mismatch";
    private static final String EMAIL_ERROR = "Email exists or wrong email";
    private static final String NAME_ERROR = "Wrong name";
    private static final String DATE_ERROR = "Wrong date";

    @Override
    public boolean isValidFormFields(String login, String password,
            String confirmPassword, String email, String firstName,
            String lastName, String date, String role,
            List<String> customErrors) {
        boolean answer = true;
        if (login != null && !FieldsValidator.isValidLogin(login)) {
            customErrors.set(0, LOGIN_ERROR);
        }
        if (password != null && !FieldsValidator.isValidPassword(password)) {
            customErrors.set(1, PASSWORD_ERROR);
            answer = false;
        }
        if (confirmPassword != null && !Objects.equals(password,
                confirmPassword)) {
            customErrors.set(2, CONFIRM_PASSWORD_ERROR);
            answer = false;
        }
        if (email != null && !FieldsValidator.isValidEmail(email)) {
            customErrors.set(3, EMAIL_ERROR);
            answer = false;
        }
        if (firstName != null && !FieldsValidator.isValidName(firstName)) {
            customErrors.set(4, NAME_ERROR);
            answer = false;
        }
        if (lastName != null && !FieldsValidator.isValidName(lastName)) {
            customErrors.set(5, NAME_ERROR);
            answer = false;
        }
        if (date != null && !FieldsValidator.isValidDate(date)) {
            customErrors.set(6, DATE_ERROR);
        }
        return answer;
    }

    @Override
    public User createUser(String login, String password,
             String email, String firstName,
            String lastName, String date, Role role) {
        User newUser = new User();
        newUser.setLogin(login);
        newUser.setPassword(password);
        newUser.setEmail(email);
        newUser.setFirstName(firstName);
        newUser.setBirthday(Date.valueOf(date));
        newUser.setLastName(lastName);
        newUser.setRole(role);
        return newUser;
    }

    @Override
    public User editUser(String login, String password,
            String email, String firstName, String lastName, String date,
            Role role, User user) {
        User newUser = new User();
        newUser.setId(user.getId());
        newUser.setLogin(user.getLogin());
        newUser.setPassword(FieldsValidator.setCorrectPassword(password,
                user.getPassword()));
        newUser.setEmail(
                FieldsValidator.setCorrectValue(email, user.getEmail()));
        newUser.setFirstName(FieldsValidator.setCorrectValue(firstName,
                user.getFirstName()));
        newUser.setBirthday(Date.valueOf(FieldsValidator.setCorrectValue(date,
                user.getBirthday().toString())));
        newUser.setLastName(
                FieldsValidator.setCorrectValue(lastName, user.getLastName()));
        newUser.setRole(role);
        return newUser;
    }

}
