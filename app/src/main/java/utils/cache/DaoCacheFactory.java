package utils.cache;

public class DaoCacheFactory {

    public DaoCacheLFU createLFUCache() {
        return new DaoCacheLFU(5);
    }

    public DaoCacheLRU createLRUCache() {
        return new DaoCacheLRU(5);
    }
}
