package dao.user;

import model.User;

import java.util.*;

public class UserDaoImpl implements UserDao {

    private static Map<Integer, User> users = new HashMap<>();

    static {
        initializeUsers();
    }

    private static void initializeUsers() {

        users.put(1, new User(1, "Jack"));
        users.put(2, new User(2, "Bob"));
        users.put(3, new User(3, "Don"));
        users.put(4, new User(4, "Curt"));
    }

    @Override
    public User getUserById(Integer id) {
        return users.get(id);
    }

    @Override
    public void save(User user) {
        users.put(user.getId(), user);
    }

    @Override
    public void delete(User user) {
        users.remove(user.getId());
    }

    @Override
    public void update(User user) {
        users.put(user.getId(), user);
    }
}
