package model.service.impl;

import model.dao.impl.UserDao;
import model.domain.entity.User;
import model.domain.exceptions.NotUniqueEmailException;
import model.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public UserServiceImpl() {
        userDao = new UserDao();
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public boolean update(User user) {
        return userDao.update(user);
    }

    @Override
    public boolean delete(User user) {
        return userDao.remove(user);
    }

    @Override
    public User getById(long id) {
        return userDao.getById(id);
    }

    @Override
    public boolean register(User user) {
        if (isExist(user)) {
            throw new NotUniqueEmailException(user.getEmail());
        }
        return userDao.create(user);
    }

    @Override
    public boolean isExist(User user) {
        List<User> users = getAll();

        if (users != null) {
            return users.stream()
                    .anyMatch(u -> u.getEmail().equals(user.getEmail()));
        } else return false;
    }

    //TODO try to use DaoFactory and use userDao.getById as on 59 line

    @Override
    public User getByEmail(String email) {
        return userDao.getByEmail(email);
    }
//
//    public User getByEmail(String email) {
//        return userDao.getById(bundle.getString("user.get.email"),
//                ps -> ps.setString(1, email),
//                userDao.getMapper());
//    }
}
