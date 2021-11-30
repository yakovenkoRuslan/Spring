package com.nixsolution.user;

import com.nixsolution.entity.Role;
import com.nixsolution.entity.User;

import java.util.List;

public interface AddEditUser {

     boolean isValidFormFields(String login, String password, String confirmPassword, String email,
            String firstName, String lastName, String date, String role, List<String> customErrors);

     User createUser(String login, String password, String email,
             String firstName, String lastName, String date, Role role);

     User editUser(String login, String password, String email,
             String firstName, String lastName, String date, Role role, User user);
}
