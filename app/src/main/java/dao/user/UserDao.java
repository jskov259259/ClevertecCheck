package dao.user;

import model.User;

public interface UserDao {

    User getUserById(Integer id);

    void save(User user);

    void delete(User user);

    void update(User user);
}
