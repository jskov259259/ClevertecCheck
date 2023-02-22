package utils.cache;

import model.User;

import java.util.Optional;

public interface DaoCache {

    User getUserById(Integer id);

    void saveUser(User user);

    void updateUser(User user);

    void deleteUser(User user);
}
