package com.nixsolution.dao;

import com.nixsolution.entity.Role;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HibernateRoleDao {

    private final SessionFactory sessionFactory;

    public HibernateRoleDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void create(Role entity) {
        Session session = sessionFactory.getCurrentSession();
        session.save(entity);
    }

    public void update(Role entity) {
        Session session = sessionFactory.getCurrentSession();
        session.update(entity);
    }

    public void remove(Role entity) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(entity);
    }

    public List<Role> findAll() {
        List<Role> answer = (List<Role>) sessionFactory.getCurrentSession()
                .createQuery("From Role").list();
        return answer;
    }

    public Role findById(long id) {
        return sessionFactory.getCurrentSession().get(Role.class, id);
    }

    public Role findByName(String name) {
        Role role;
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Role.class);
        criteria.add(Restrictions.like("name", name));
        role = (Role) criteria.list().get(0);
        return role;
    }
}


