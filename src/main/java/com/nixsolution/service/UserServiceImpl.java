package com.nixsolution.service;

import com.nixsolution.dao.HibernateUserDao;
import com.nixsolution.entity.User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final HibernateUserDao hibernateUserDao;

    public UserServiceImpl(HibernateUserDao hibernateUserDao) {
        this.hibernateUserDao = hibernateUserDao;
    }

    @Override
    @Transactional
    public void save(User user) {
        hibernateUserDao.create(user);
    }

    @Override
    @Transactional
    public void update(User user) {
        hibernateUserDao.update(user);
    }

    @Override
    @Transactional
    public void delete(long id) {
        User user = findById(id);
        hibernateUserDao.remove(user);
    }

    @Override
    @Transactional
    public List<User> findAll() {
        return hibernateUserDao.findAll();
    }

    @Override
    @Transactional
    public User findById(long id) {
        return hibernateUserDao.findById(id);
    }

    @Override
    @Transactional
    public User findByLogin(String login) {
        return hibernateUserDao.findByLogin(login);
    }

    @Override
    @Transactional
    public User findByEmail(String email) {
        return hibernateUserDao.findByEmail(email);
    }
}
