package utils.cache;

import model.User;

public interface UserDaoCache {

    User getUserById(Integer id);

    void saveUser(User user);

    void updateUser(User user);

    void deleteUser(User user);
}
