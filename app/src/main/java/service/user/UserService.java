package service.user;

import model.User;

/**
 * User Service Interface.
 */
public interface UserService {

    /**
     * Get user by id
     * @param id user id
     * @return - User with given id
     */
    User get(Integer id);

    /**
     * Save new User
     * @param user user to save
     */
    void post(User user);

    /**
     * Delete User
     * @param user user to delete
     */
    void delete(User user);

    /**
     * Update User
     * @param user user to update
     */
    void put(User user);
}
