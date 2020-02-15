package model.dao.impl;

import model.dao.AbstractJDBCDao;
import model.dao.EntityMapper;
import model.domain.entity.User;
import model.domain.enums.Role;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserDao extends AbstractJDBCDao<User> {
    private final String ID = "id";
    private final String EMAIL = "email";
    private final String PASSWORD = "password";
    private final String NAME = "name";
    private static ResourceBundle bundle = ResourceBundle.getBundle("database/queries");

    @Override
    public User getById(long id) {
        return getById(bundle.getString("user.get.id"),
                ps -> ps.setLong(1, id),
                getMapper());
    }

    @Override
    public List<User> getAll() {
        return getAll(bundle.getString("user.get.all"),
                getMapper());
    }

    @Override
    public boolean create(User user) {
        long id = createUpdateWithReturn((bundle.getString("user.add")),
                (ps -> {
                    ps.setString(1, user.getEmail());
                    ps.setString(2, BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12)));
                    ps.setString(3, user.getName());
                }));
        if (id > 0) {
            user.getRoles().forEach(role ->
                    createUpdate(bundle.getString("role.add"), ps -> {
                        ps.setLong(1, id);
                        ps.setObject(2, role);
                    })
            );
            return true;
        }
        return false;
    }

    public User getByEmail(String email) {
        return getById(bundle.getString("user.get.email"),
                ps -> ps.setString(1, email),
                getMapper());
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    public List<User> getAllByActivity(long activityId) {
        return getAllWithCondition(bundle.getString("users.get.by.activity"), getMapper(), ps -> {
            ps.setLong(1, activityId);
        });
    }

    @Override
    public boolean remove(User user) {
        return false;
    }

    @Override
    public EntityMapper<User> getMapper() {
        return resultSet -> {
            Set<Role> roles = Stream.of(resultSet.getString("roles").split(", "))
                    .map(Role::valueOf)
                    .collect(Collectors.toSet());
            return new User(
                    resultSet.getLong(ID),
                    resultSet.getString(EMAIL),
                    resultSet.getString(PASSWORD),
                    resultSet.getString(NAME),
                    roles
            );
        };
    }


}
