package general.tree;

public class Node<T extends Comparable<T>> {
    private T value;
    private Node<T> leftChild;
    private Node<T> rightChild;
    private Boolean isRepeat;
    protected int repeatCount;
    private int level;

    public Node(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public int getRepeatCount() {
        return repeatCount;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Node<T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node<T> leftChild) {
        this.leftChild = leftChild;
    }

    public Node<T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node<T> rightChild) {
        this.rightChild = rightChild;
    }

    public Boolean getRepeat() {
        return isRepeat;
    }

    public void setRepeat(Boolean repeat) {
        isRepeat = repeat;
    }

    public void setRepeatCount(int repeatCount) {
        this.repeatCount = repeatCount;
    }

    public boolean isLeaf() {
        return leftChild == null && rightChild == null;
    }

    public boolean isLeftChild(T value) {
        return getValue().compareTo(value) > 0;
    }

    public boolean hasOnlyOneChild() {
        return rightChild != null ^ leftChild != null;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
