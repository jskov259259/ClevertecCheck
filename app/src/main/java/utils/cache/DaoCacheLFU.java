package utils.cache;

import model.User;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DaoCacheLFU implements DaoCache {

    private Map<Integer, User> valueMap = new HashMap<>();
    private Map<Integer, Integer> frequencyMap = new HashMap<>();
    private final int size;

    public DaoCacheLFU (int capacity) {
        this.size = capacity;
    }

   public User get(Integer id) {
        if (valueMap.containsKey(id) == false) return null;
        frequencyMap.put(id, frequencyMap.get(id) + 1);
        return valueMap.get(id);
   }

   public void put(User user) {

        if (valueMap.containsKey(user.getId()) == false) {
            valueMap.put(user.getId(), user);
            frequencyMap.put(user.getId(), 1);
        }
        else {
            if (valueMap.size() == size) {
                Comparator<? super Map.Entry<Integer, Integer>> valueComparator = (entry1,
                                                                                   entry2) -> entry1.getValue().compareTo(entry2.getValue());

                Optional<Map.Entry<Integer, Integer>> minValue = frequencyMap.entrySet()
                        .stream().min(valueComparator);
                frequencyMap.remove(minValue.get().getKey());
                valueMap.remove(minValue.get().getKey());
            }
            valueMap.put(user.getId(), user);
            frequencyMap.put(user.getId(), frequencyMap.get(user.getId()) + 1);
        }
   }

    public void display() {
        System.out.println(valueMap.keySet().stream()
                .map(key -> key + "=" + valueMap.get(key))
                .collect(Collectors.joining(", ", "{", "}")));
    }
    @Override
    public User getUserById(Integer id) {
        return null;
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void saveUser(User user) {

    }

    @Override
    public void deleteUser(User user) {

    }
}
