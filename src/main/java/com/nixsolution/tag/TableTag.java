package com.nixsolution.tag;

import com.nixsolution.entity.User;

import javax.servlet.jsp.tagext.TagSupport;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.Iterator;
import java.util.List;

public class TableTag  extends TagSupport {

    private List<User> users;
    private Iterator<User> iterator;

    @Override
    public int doAfterBody() {
        return fillBody();
    }

    @Override
    public int doStartTag() {
        fillBody();
        return EVAL_BODY_INCLUDE;
    }

    private int fillBody() {
        if (iterator == null) {
            return EVAL_PAGE;
        }
        if (iterator.hasNext()) {
            User user = iterator.next();
            pageContext.setAttribute("id", user.getId());
            pageContext.setAttribute("login", user.getLogin());
            pageContext.setAttribute("firstName", user.getFirstName());
            pageContext.setAttribute("lastName", user.getLastName());
            pageContext.setAttribute("age", getYearsBetweenTwoDates(user.getBirthday()));
            pageContext.setAttribute("role", user.getRole().getName());
            return EVAL_BODY_AGAIN;
        } else {
            return EVAL_PAGE;
        }
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
        if (users == null) {
            this.iterator = null;
        } else {
            this.iterator = users.listIterator();
        }
    }

    private int getYearsBetweenTwoDates(Date date) {
        return Period.between(date.toLocalDate(), LocalDate.now()).getYears();
    }
}

