package utils.cache;

import model.User;

import java.util.*;

public class DaoCacheLRU extends LinkedHashMap<Integer, User> implements DaoCache  {

    private int maxSize;

    public DaoCacheLRU(int maxCacheSize) {
        super(maxCacheSize, 0.75f, true);
        this.maxSize = maxCacheSize;
    }

    @Override
    public User getUserById(Integer id) {
        User user = get(id);
        put(id, user);
        return user;
    }

    @Override
    public void updateUser(User user) {
        put(user.getId(), user);
    }

    @Override
    public void saveUser(User user) {
        updateUser(user);
    }

    @Override
    public void deleteUser(User user) {
        remove(user.getId());
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, User> eldest){
        if (size() <= maxSize) return false;
        return true;
    }
}
