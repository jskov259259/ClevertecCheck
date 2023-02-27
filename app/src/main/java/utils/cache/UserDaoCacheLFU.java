package utils.cache;

import model.User;

import java.util.*;
import java.util.stream.Collectors;

public class UserDaoCacheLFU implements UserDaoCache {

    /** Map with user IDs and users */
    private Map<Integer, User> valueMap = new HashMap<>();
    /** Map with user IDs and count of usage */
    private Map<Integer, Integer> countMap = new HashMap<>();
    /** Map with count of usage and list with users */
    private TreeMap<Integer, List<User>> frequencyMap = new TreeMap<>();
    /** Field max collection size */
    private final int maxSize;

    /**
     * Constructor - new User Dao Cache LFU object
     * @param maxCacheSize - max size
     */
    public UserDaoCacheLFU(int maxCacheSize) {
        this.maxSize = maxCacheSize;
    }

    /**
     * Get user by id
     * @param id - user id
     * @return - User with given id or null if the user is not present
     */
    @Override
    public User getUserById(Integer id) {

        if (!valueMap.containsKey(id) || maxSize == 0) return null;

        int frequency = countMap.get(id);
        User userValue = frequencyMap.get(frequency).stream().filter(user -> user.getId() == id)
                .findFirst().get();
       frequencyMap.get(frequency).remove(userValue);
       if (frequencyMap.get(frequency).size() == 0)
           frequencyMap.remove(frequency);

       frequencyMap.computeIfAbsent(frequency + 1, v -> new LinkedList<>()).add(userValue);
       countMap.put(id, frequency + 1);
        return valueMap.get(id);
   }

    /**
     * Save new User in collections. If user present, update.
     * @param user user to save
     */
   @Override
   public void saveUser(User user) {

        if (valueMap.containsKey(user.getId()) == false && maxSize > 0) {
            if (valueMap.size() == maxSize) {
                Integer lowestCount = frequencyMap.firstKey();
                User userToDelete = frequencyMap.get(lowestCount).remove(0);

                if (frequencyMap.get(lowestCount).size() == 0)
                    frequencyMap.remove(lowestCount);

                valueMap.remove(userToDelete.getId());
                countMap.remove(userToDelete.getId());
            }

            valueMap.put(user.getId(), user);
            countMap.put(user.getId(), 1);
            frequencyMap.computeIfAbsent(1, v -> new LinkedList<>()).add(user);
        } else if (maxSize > 0) {
                valueMap.put(user.getId(), user);

                Integer frequency = countMap.get(user.getId());
                User userValue = frequencyMap.get(frequency).stream().filter(u -> u.getId() == user.getId())
                        .findFirst().get();
                frequencyMap.get(frequency).remove(userValue);

                if (frequencyMap.get(frequency).size() == 0)
                    frequencyMap.remove(frequency);

                frequencyMap.computeIfAbsent(frequency + 1, v -> new LinkedList<>()).add(userValue);
                countMap.put(user.getId(), frequency + 1);
            }
        }

    /**
     * Update User in collections. If user is not present, creates new one.
     * @param user user to update
     */
    @Override
    public void updateUser(User user) {
        saveUser(user);
    }

    /**
     * Delete User from all collections
     * @param user user to delete
     */
    @Override
    public void deleteUser(User user) {

        valueMap.remove(user.getId());
        int frequency = countMap.get(user.getId());
        countMap.remove(user.getId());
        frequencyMap.get(frequency).remove(user);
    }

    public int size() {
        return valueMap.size();
    }

    public void display() {
        System.out.println(valueMap.keySet().stream()
                .map(key -> key + "=" + valueMap.get(key))
                .collect(Collectors.joining(", ", "{", "}")));
    }
}
