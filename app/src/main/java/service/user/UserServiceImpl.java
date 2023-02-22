package service.user;

import dao.user.UserDao;
import model.User;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User get(Integer id) {
        return userDao.getUserById(id);
    }

    @Override
    public void post(User user) {
        userDao.save(user);
    }

    @Override
    public void delete(User user) {
        userDao.delete(user);
    }

    @Override
    public void put(User user) {
        userDao.update(user);
    }
}
