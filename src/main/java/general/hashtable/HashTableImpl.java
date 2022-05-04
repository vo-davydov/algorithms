package general.hashtable;

import java.util.LinkedList;
import java.util.List;

public class HashTableImpl<K, V> implements HashTable<K, V> {

    private final List<Item<K, V>>[] data;
    private final Item<K, V> emptyItem;
    private int size;

    public HashTableImpl(int capacity) {
        this.data = new LinkedList[capacity * 2];

        for (int i = 0; i < data.length; i++) {
            this.data[i] = new LinkedList<>();
        }

        emptyItem = new Item<>(null, null);
    }

    public HashTableImpl() {
        this(16);
    }

    static class Item<K, V> implements Entry<K, V> {
        private final K key;
        private V value;

        public Item(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public void setValue(V value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return String.format("key: %s -> value: %s", key, value);
        }
    }

    @Override
    public boolean put(K key, V value) {
        if (size() == data.length) {
            return false;
        }

        int indexFromHashFunc = hashFunc(key);

        data[indexFromHashFunc].add(new Item<>(key, value));
        size++;

        return true;
    }

    private boolean isKeysEquals(Item<K, V> item, K key) {
        if (item == emptyItem) {
            return false;
        }
        return (item.getKey() == null) ? (key == null) : (item.getKey().equals(key));
    }

    private int hashFunc(K key) {
        return Math.abs(key.hashCode() % data.length);
    }

    @Override
    public V get(K key) {
        int index = hashFunc(key);

        for (Item<K, V> item : data[index]) {
            if (isKeysEquals(item, key)) {
                return item.getValue();
            }
        }

        return null;
    }

    @Override
    public V remove(K key) {
        int index = hashFunc(key);

        if (index == -1) {
            return null;
        }

        for (Item<K, V> item : data[index]) {
            if (isKeysEquals(item, key)) {
                data[index].remove(item);
                return item.getValue();
            }
        }

        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void display() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            sb.append(String.format("%s = [%s]%n", i, data[i]));
        }

        return sb.toString();
    }
}
