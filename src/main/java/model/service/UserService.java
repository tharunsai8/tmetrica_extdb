package model.service;

import model.domain.entity.User;

import java.util.List;

public interface UserService extends Service {
    List<User> getAll();

    boolean update(User user);

    boolean delete(User user);

    User getById(long id);

    boolean register(User user);

    boolean isExist(User user);

    User getByEmail(String email);
}
