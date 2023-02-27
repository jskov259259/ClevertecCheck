package utils.cache;

import model.User;

import java.util.*;

public class UserDaoCacheLRU extends LinkedHashMap<Integer, User> implements UserDaoCache {

    /** Field max collection size */
    private int maxSize;

    /**
     * Constructor - new User Dao Cache LRU object
     * @param maxCacheSize - max size
     */
    public UserDaoCacheLRU(int maxCacheSize) {
        super(maxCacheSize, 0.75f, true);
        this.maxSize = maxCacheSize;
    }

    /**
     * Get user by id
     * @param id - user id
     * @return - User with given id or null if the user is not present
     */
    @Override
    public User getUserById(Integer id) {
        User user = get(id);
        if (user == null) return null;
        put(id, user);
        return user;
    }

    /**
     * Update User in collection. If user is not present, creates new one.
     * @param user user to update
     */
    @Override
    public void updateUser(User user) {
        put(user.getId(), user);
    }

    /**
     * Save new User in collection. If user present, update.
     * @param user user to save
     */
    @Override
    public void saveUser(User user) {
        updateUser(user);
    }

    /**
     * Delete User from collection
     * @param user user to delete
     */
    @Override
    public void deleteUser(User user) {
        remove(user.getId());
    }

    /**
     * Override method from basic class to control max size collection
     * @param eldest - eldest entry to remove
     */
    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, User> eldest){
        if (size() <= maxSize) return false;
        return true;
    }
}
