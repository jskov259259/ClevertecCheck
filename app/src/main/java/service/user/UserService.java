package service.user;

import model.User;

public interface UserService {

    User get(Integer id);

    void post(User user);

    void delete(User user);

    void put(User user);
}
