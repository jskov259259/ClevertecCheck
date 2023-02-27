package utils.cache;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

public class UserDaoCacheFactory {

    private String algorithm;
    private int maxSize;

    public UserDaoCacheFactory() {
        readProperties();
    }

    public UserDaoCache createCache() {
        if ("LFU".equals(algorithm)) return createLFUCache();
        return createLRUCache();
    }

    private UserDaoCacheLFU createLFUCache() {
        return new UserDaoCacheLFU(maxSize);
    }

    private UserDaoCacheLRU createLRUCache() {
        return new UserDaoCacheLRU(maxSize);
    }

    private void readProperties() {
        Yaml yaml = new Yaml();
        InputStream inputStream = UserDaoCacheFactory.class
                .getClassLoader()
                .getResourceAsStream("application.yml");
        Map<String, Object> map = yaml.load(inputStream);
        maxSize = (Integer) map.getOrDefault("size", 5);
        algorithm = (String) map.getOrDefault("algorithm", "LRU");
    }
}
