package dao.user;

import model.User;

/**
 * User DAO Interface.
 */
public interface UserDao {

    /**
     * Get user by id
     * @param id user id
     * @return - User with given id
     */
    User getUserById(Integer id);

    /**
     * Save new User
     * @param user user to save
     */
    void save(User user);

    /**
     * Delete User
     * @param user user to delete
     */
    void delete(User user);

    /**
     * Update User
     * @param user user to update
     */
    void update(User user);
}
