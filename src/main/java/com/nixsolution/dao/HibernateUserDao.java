package com.nixsolution.dao;

import com.nixsolution.entity.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HibernateUserDao {

    private final SessionFactory sessionFactory;

    public HibernateUserDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void create(User entity) {
        Session session = sessionFactory.getCurrentSession();
        session.save(entity);
    }

    public void update(User entity) {
        Session session = sessionFactory.getCurrentSession();
        session.update(entity);
    }

    public void remove(User entity) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(entity);
    }

    public List<User> findAll() {
        List<User> answer;
        Session session = sessionFactory.getCurrentSession();
        answer = session.createCriteria(User.class).list();
        return answer;
    }

    public User findById(long id) {
        User user;
        Session session = sessionFactory.getCurrentSession();
        user = session.get(User.class, id);
        return user;
    }

    public User findByLogin(String login) {
        User user;
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.like("login", login));
        if (criteria.list().isEmpty()) {
            return null;
        }
        user = (User) criteria.list().get(0);
        return user;
    }

    public User findByEmail(String email) {
        User user;
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.like("email", email));
        if (criteria.list().isEmpty()) {
            return null;
        }
        user = (User) criteria.list().get(0);
        return user;
    }
}


