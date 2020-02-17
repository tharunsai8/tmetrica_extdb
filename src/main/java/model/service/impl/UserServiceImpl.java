package model.service.impl;

import model.dao.impl.UserDao;
import model.domain.entity.User;
import model.domain.exceptions.NotUniqueEmailException;
import model.service.UserService;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;

/**
 * The type User service.
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    /**
     * Instantiates a new User service.
     */
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

    @Override
    public List<User> getAllByActivity(long activityId) {
        return userDao.getAllByActivity(activityId);
    }


    @Override
    public User getByEmail(String email) {
        return userDao.getByEmail(email);
    }

    @Override
    public User validateUser(String email, String password) {
        User user = userDao.getByEmail(email);
        if (user != null) {
            return (BCrypt.checkpw(password, user.getPassword())) ? user : null;
        }
        return null;

    }

}
