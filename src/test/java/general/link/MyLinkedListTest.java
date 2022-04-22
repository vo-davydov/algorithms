package general.link;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Iterator;
import java.util.Objects;

class MyLinkedListTest {
    private static final String[] STRINGS = new String[]{"ZERO", "FIRST", "SECOND", "THIRD"};
    private static final MyLinkedList<String> MY_LINKED_LIST = new MyLinkedList<>();
    private static int size;

    @BeforeEach
    void init() {
        MY_LINKED_LIST.insert(STRINGS);
        size = MY_LINKED_LIST.getSize();
    }

    @AfterEach
    void clearAll() {
        MY_LINKED_LIST.clear();
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "1", "2", "3"})
    void insert(String item) {
        MY_LINKED_LIST.insert(item);
        Assertions.assertEquals(MY_LINKED_LIST.find(item), item);
        Assertions.assertEquals(MY_LINKED_LIST.getCurrent().getItem(), item);
        Assertions.assertEquals(MY_LINKED_LIST.getSize(), size + 1);
    }

    @ParameterizedTest
    @ValueSource(strings = {"ZERO", "FIRST", "SECOND", "THIRD"})
    void delete(String item) {
        MY_LINKED_LIST.delete(item);
        Assertions.assertNull(MY_LINKED_LIST.find(item));
        Assertions.assertEquals(MY_LINKED_LIST.getSize(), size - 1);

        MyLinkedList<String> linkedList = new MyLinkedList<>();
        linkedList.insert(item);
        linkedList.delete(item);
        Assertions.assertTrue(linkedList.isEmpty());

        linkedList.insert(item); // size 1
        linkedList.insert(item); // size 2
        linkedList.insert(item); // size 3
        linkedList.delete(item); // size 2
        Assertions.assertFalse(linkedList.isEmpty());
        Assertions.assertEquals(2, linkedList.getSize());

    }

    @Test
    void deleteFirst() {
        MY_LINKED_LIST.delete();
        Assertions.assertEquals(MY_LINKED_LIST.getSize(), size - 1);
    }

    @Test
    void clear() {
        MY_LINKED_LIST.clear();
        Assertions.assertEquals(MY_LINKED_LIST.getSize(), 0);
        Assertions.assertNull(MY_LINKED_LIST.getFirst());
        Assertions.assertNull(MY_LINKED_LIST.getLast());
        Assertions.assertNull(MY_LINKED_LIST.getCurrent());
        Assertions.assertTrue(MY_LINKED_LIST.isEmpty());
    }

    @Test
    void getFirst() {
        Assertions.assertEquals(STRINGS[STRINGS.length - 1], MY_LINKED_LIST.getCurrent().getItem());
        Assertions.assertEquals(STRINGS[STRINGS.length - 1], MY_LINKED_LIST.getFirst().getItem());
    }

    @Test
    void getLast() {
        Assertions.assertEquals(STRINGS[0], MY_LINKED_LIST.getLast().getItem());
    }

    @ParameterizedTest
    @ValueSource(strings = {"ZERO", "FIRST", "SECOND", "THIRD"})
    void getCurrentValue(String item) {
        MY_LINKED_LIST.insert(item);
        Assertions.assertEquals(item, MY_LINKED_LIST.getFirst().getItem());
        Assertions.assertEquals(item, MY_LINKED_LIST.getCurrentValue());
    }


    @ParameterizedTest
    @ValueSource(strings = {"ZERO", "FIRST", "SECOND", "THIRD"})
    void findNode(String item) {
        Assertions.assertNotNull(MY_LINKED_LIST.findNode(item));
        Assertions.assertEquals(item, Objects.requireNonNull(MY_LINKED_LIST.findNode(item)).getItem());
    }

    @Test
    void iterator() {
        Iterator<MyLinkedList.Node<String>> iterator = MY_LINKED_LIST.iterator();
        Assertions.assertDoesNotThrow(() -> {
            while (iterator.hasNext()) {
                System.out.println(MY_LINKED_LIST.getCurrentValue());
                iterator.next();
            }
        });
    }
}