package service.user;

import dao.user.UserDao;
import dao.user.UserDaoImpl;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {

    private UserServiceImpl userService;
    private UserDaoImpl userDao;

    @BeforeEach
    void setUp() {
        userDao = new UserDaoImpl();
        userService = new UserServiceImpl(userDao);
    }

    @Test
    void checkGetUserById() {
        User user = userService.get(1);
    }
}