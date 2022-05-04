package general.hashtable;

import general.hashtable.domain.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashTableImplTest {
    private static final HashTable<Product, Integer> hashTable = new HashTableImpl<>(5);
    private final Product orange = new Product(1, "Orange");
    private final Product banana = new Product(77, "Banana");
    private final Product carrot = new Product(67, "Carrot");
    private final Product lemon = new Product(60, "Lemon");
    private final Product milk = new Product(51, "Milk");
    private final Product potato = new Product(31, "Potato");
    private final Product megaPotato = new Product(31, "MegaPotato");

    @BeforeEach
    void init() {
        hashTable.put(orange, 150);
        hashTable.put(banana, 100);
        hashTable.put(carrot, 228);
        hashTable.put(lemon, 250);
        hashTable.put(milk, 120);
        hashTable.put(potato, 67);
        hashTable.put(megaPotato, 999);
    }

    @Test
    void get() {
        assertEquals(67, hashTable.get(potato));
        assertEquals(228, hashTable.get(carrot));
        assertEquals(120, hashTable.get(milk));
        assertEquals(100, hashTable.get(banana));
        assertNotEquals(999, hashTable.get(banana));
        assertNull(hashTable.get(new Product(77, "Gold")));
    }

    @Test
    void remove() {
        assertEquals(67, hashTable.remove(potato));
        assertNull(hashTable.get(potato));

        assertEquals(228, hashTable.remove(carrot));
        assertNull(hashTable.get(carrot));

        assertEquals(120, hashTable.remove(milk));
        assertNull(hashTable.get(milk));

        assertEquals(100, hashTable.remove(banana));
        assertNull(hashTable.get(banana));
    }

}