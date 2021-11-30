package com.nixsolution.service;

import com.nixsolution.dao.HibernateRoleDao;
import com.nixsolution.entity.Role;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    final HibernateRoleDao hibernateRoleDao;

    public RoleServiceImpl(HibernateRoleDao hibernateRoleDao) {
        this.hibernateRoleDao = hibernateRoleDao;
    }


    @Override
    @Transactional
    public Role findRoleByName(String name) {
        return hibernateRoleDao.findByName(name);
    }

    @Override
    @Transactional
    public void save(Role role) {
        hibernateRoleDao.create(role);
    }

    @Override
    @Transactional
    public void update(Role role) {
        hibernateRoleDao.update(role);
    }

    @Override
    @Transactional
    public void delete(long id) {
        Role role = hibernateRoleDao.findById(id);
        hibernateRoleDao.remove(role);
    }

    @Override
    @Transactional
    public List<Role> findAll() {
        return hibernateRoleDao.findAll();
    }

    @Override
    @Transactional
    public Role findById(long id) {
        return hibernateRoleDao.findById(id);
    }

    @Override
    @Transactional
    public Role findByName(String name) {
        return hibernateRoleDao.findByName(name);
    }

}
