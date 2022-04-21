package general.link;

import java.util.Iterator;

public final class MyLinkedList<T> {
    private Node<T> current;
    private Node<T> first;
    private Node<T> last;
    private int size;

    public MyLinkedList() {
    }

    @SafeVarargs
    public MyLinkedList(T... args) {
        insert(args);
    }

    public boolean isEmpty() {
        return first == null && current == null && last == null && size == 0;
    }

    /**
     * Insert item into MyLinkedList, inserted item is always equals current item
     */
    public void insert(T item) {
        Node<T> node = new Node<>(item);
        if (first == null) {
            last = node;
        } else {
            first.setPrevious(node);
        }

        node.setNext(first);
        first = node;
        current = node;
        size++;
    }

    @SafeVarargs
    public final void insert(T... args) {
        for (T t : args) {
            insert(t);
        }
    }

    /**
     * delete first element
     *
     * @return deleted Node
     */
    public Node<T> delete() {
        Node<T> node = first;
        first = first.getNext();
        if (first == null) {
            clear();
            return node;
        }
        first.setPrevious(null);
        current = first;
        size--;
        return node;
    }

    /**
     * delete first element if item equals T
     *
     * @return deleted Node
     */
    public Node<T> delete(T t) {
        Node<T> node = findNode(t);

        if (node == null) {
            return null;
        }

        if (size == 1) {
            clear();
            return node;
        }

        Node<T> previous = node.getPrevious();

        if (previous == null) {
            first = node.getNext();
            size--;
            return node;
        }

        if (node == last) {
            last = previous;
            last.setNext(null);
        } else {
            previous.setNext(node.getNext());
        }

        size--;
        return node;

    }

    /**
     * finds first node by item
     *
     * @return node
     */
    public Node<T> findNode(T item) {
        Node<T> current = first;
        while (current != null) {
            if (current.getItem() != null && current.getItem().equals(item)) {
                return current;
            }
            current = current.getNext();
        }

        return null;
    }

    /**
     * find first node by item
     *
     * @return node
     */
    public T find(T item) {
        Node<T> current = first;
        while (current != null) {
            if (current.getItem() != null && current.getItem().equals(item)) {
                return current.getItem();
            }
            current = current.getNext();
        }

        return null;
    }

    public void display() {
        Node<T> current = first;
        while (current != null) {
            current.display();
            current = current.next;
        }
    }

    public void clear() {
        first = null;
        last = null;
        current = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public Node<T> getFirst() {
        return first;
    }

    public Node<T> getLast() {
        return last;
    }

    public Node<T> getCurrent() {
        return current;
    }

    public T getCurrentValue() {
        if (current != null) {
            return current.getItem();
        }

        return null;
    }

    private Node<T> setCurrent(Node<T> node) {
        return current = node;
    }


    protected static class Node<T> {
        private final T item;
        private Node<T> next;
        private Node<T> previous;

        public Node(T item) {
            this.item = item;
        }

        public T getItem() {
            return item;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }

        public Node<T> getPrevious() {
            return previous;
        }

        public void setPrevious(Node<T> previous) {
            this.previous = previous;
        }

        public void display() {
            System.out.println(this);
        }

    }

    public Iterator<MyLinkedList.Node<T>> iterator() {
        return new MyLinkedListIterator<>(this);
    }

    private static class MyLinkedListIterator<T> implements Iterator<MyLinkedList.Node<T>> {
        private final MyLinkedList<T> myLinkedList;

        private MyLinkedListIterator(MyLinkedList<T> myLinkedList) {
            this.myLinkedList = myLinkedList;
        }

        @Override
        public boolean hasNext() {
            return myLinkedList.getCurrent() != null;
        }

        @Override
        public Node<T> next() {
            return myLinkedList.setCurrent(myLinkedList.getCurrent().getNext());
        }

        @Override
        public void remove() {
            myLinkedList.delete(myLinkedList.getCurrent().getItem());
        }
    }
}
