package com.nixsolution.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FieldsValidator {
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final Pattern PATTERN = Pattern.compile(EMAIL_PATTERN);
    private static final String PASSWORD_PATTERN = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}";
    private static final String LOGIN_PATTERN = "^[A-Za-z]([.A-Za-z0-9-]{1,18})([A-Za-z0-9])$";
    private static final String DATE_PATTERN = "yyyy-MM-dd";
    private static final String NAME_REGEX = "[0-9 -+.$@#/|]+";

    public static boolean isValidEmail(String email) {
        Matcher matcher = PATTERN.matcher(email);
        return matcher.matches();
    }

    public static boolean isValidPassword(String password) {
        return password.matches(PASSWORD_PATTERN);
    }

    public static boolean isValidLogin(String login) {
        return login.matches(LOGIN_PATTERN);
    }

    public static boolean isValidDate(String date) {
        try {
            DateFormat df = new SimpleDateFormat(DATE_PATTERN);
            df.setLenient(false);
            Date date1 = df.parse(date);
            return isAvailableDateValues(date1);
        } catch (ParseException e) {
            return false;
        }
    }

    public static boolean isValidName(String firstName) {
        return firstName.split(NAME_REGEX).length == 1;
    }

    private static boolean isAvailableDateValues(Date date) {
        if (date.after(new Date()) || date.getYear() < (new Date().getYear() - 100)) {
            return false;
        }
        return true;
    }

    public static String setCorrectValue(String newValue, String oldValue) {
        if (newValue.replaceAll("\\s+", "").length() != 0) {
            return newValue;
        }
        return oldValue;
    }

    public static String setCorrectPassword(String password, String oldPassword) {
        if (password.replaceAll("\\s+", "").length() != 0) {
            return new BCryptPasswordEncoder().encode(password);
        }
        return oldPassword;
    }

    public static String configureFields(String value) {
        if (value.replaceAll("\\s+", "").length() == 0) {
            value = null;
        }
        return value;
    }
}
