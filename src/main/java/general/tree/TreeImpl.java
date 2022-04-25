package general.tree;

import java.util.List;
import java.util.Stack;

public class TreeImpl<E extends Comparable<E>> implements Tree<E> {

    private Node<E> root;
    private int size;
    private int maxLevel = Integer.MAX_VALUE;

    private class NodeAndParent {
        private Node<E> current;
        private Node<E> parent;
        private int level;

        public NodeAndParent(Node<E> current, Node<E> parent, int level) {
            this.current = current;
            this.parent = parent;
            this.level = level;
        }
    }

    @Override
    public boolean contains(E value) {
        NodeAndParent nodeAndParent = doFind(value);
        return nodeAndParent != null;
    }

    private NodeAndParent doFind(E value) {
        Node<E> current = root;
        Node<E> parent = null;

        int level = 0;

        while (current != null) {
            level++;
            if (current.getValue().equals(value)) {
                break;
            }

            parent = current;

            if (current.isLeftChild(value)) {
                current = current.getLeftChild();
            } else {
                current = current.getRightChild();
            }
        }

        return new NodeAndParent(current, parent, level);
    }

    @Override
    public boolean add(E value) {

        NodeAndParent nodeAndParent = doFind(value);

        if (nodeAndParent.current != null) {
            nodeAndParent.current.repeatCount++;
            return false;
        }

        Node<E> parent = nodeAndParent.parent;
        Node<E> node = new Node<>(value);

        if (parent != null && parent.getLevel() >= maxLevel) {
            return false;
        }

        if (isEmpty()) {
            root = node;
        } else if (parent.isLeftChild(value)) {
            parent.setLeftChild(node);
        } else {
            parent.setRightChild(node);
        }

        if (parent != null) {
            node.setLevel(parent.getLevel() + 1);
        } else {
            node.setLevel(1);
        }

        size++;

        return true;
    }

    @Override
    public boolean remove(E value) {
        NodeAndParent nodeAndParent = doFind(value);

        Node<E> parent = nodeAndParent.parent;
        Node<E> removed = nodeAndParent.current;

        if (removed == null) {
            return false;
        }

        if (removed.isLeaf()) {
            removeLeafNode(removed, parent);
        } else if (removed.hasOnlyOneChild()) {
            removeNodeWithOneChild(removed, parent);
        } else {
            removeNodeWithAllChildren(removed, parent);
            //[1 2 3 4 5] 7 [ 8 9 10 11]
        }

        size--;
        return true;
    }

    private void removeLeafNode(Node<E> removed, Node<E> parent) {
        if (removed == root) {
            root = null;
        } else if (parent.isLeftChild(removed.getValue())) {
            parent.setLeftChild(null);
        } else {
            parent.setRightChild(null);
        }
    }

    private void removeNodeWithOneChild(Node<E> removed, Node<E> parent) {
        Node<E> child = removed.getLeftChild() != null
                ? removed.getLeftChild()
                : removed.getRightChild();

        if (removed.isLeftChild(child.getValue())) {
            removed.setLeftChild(null);
        } else {
            removed.setRightChild(child);
        }

        if (removed == root) {
            root = child;
        } else if (parent.isLeftChild(removed.getValue())) {
            parent.setLeftChild(child);
        } else {
            parent.setRightChild(child);
        }
    }

    private void removeNodeWithAllChildren(Node<E> removed, Node<E> parent) {
        Node<E> successor = getSuccessor(removed);

        if (removed == root) {
            root = successor;
        } else if (parent.isLeftChild(removed.getValue())) {
            parent.setLeftChild(successor);
        } else {
            parent.setRightChild(successor);
        }
    }

    private Node<E> getSuccessor(Node<E> removed) {
        Node<E> successor = removed;
        Node<E> parent = null;
        Node<E> current = removed.getRightChild();

        while (current != null) {
            parent = successor;
            successor = current;
            current = current.getLeftChild();
        }

        if (successor != removed.getRightChild() && parent != null) {
            parent.setLeftChild(successor.getRightChild());
            successor.setRightChild(removed.getRightChild());
        }
        successor.setLeftChild(removed.getLeftChild());

        return successor;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void display() {
        Stack<Node<E>> globalStack = new Stack<>();
        globalStack.push(root);
        int nBlanks = 64;

        boolean isRowEmpty = false;
        System.out.println("................................................................");

        while (!isRowEmpty) {
            Stack<Node<E>> localStack = new Stack<>();

            isRowEmpty = true;
            for (int i = 0; i < nBlanks; i++) {
                System.out.print(" ");
            }

            while (!globalStack.isEmpty()) {
                Node<E> tempNode = globalStack.pop();
                if (tempNode != null) {
                    System.out.print(tempNode.getValue());
                    localStack.push(tempNode.getLeftChild());
                    localStack.push(tempNode.getRightChild());
                    if (tempNode.getLeftChild() != null || tempNode.getRightChild() != null) {
                        isRowEmpty = false;
                    }
                } else {
                    System.out.print("--");
                    localStack.push(null);
                    localStack.push(null);
                }
                for (int j = 0; j < nBlanks * 2 - 2; j++) {
                    System.out.print(" ");
                }
            }

            System.out.println();

            while (!localStack.isEmpty()) {
                globalStack.push(localStack.pop());
            }

            nBlanks /= 2;
        }
        System.out.println("................................................................");
    }

    @Override
    public void traverse(TraversMode mode) {
        traverse(mode, null);
    }

    public void traverse(TraversMode mode, List<Node<E>> nodes) {
        switch (mode) {
            case PRE_ORDER:
                preOrder(root, nodes); //прямой обход
            case IN_ORDER:
                inOrder(root, nodes); //центрированный обход
            case POST_ORDER:
                postOrder(root, nodes); //обратный обход
        }
    }

    private void preOrder(Node<E> current, List<Node<E>> nodes) {
        if (current == null) {
            return;
        }

        nodes.add(current);
        preOrder(current.getLeftChild(), nodes);
        preOrder(current.getRightChild(), nodes);
    }

    private void inOrder(Node<E> current, List<Node<E>> nodes) {
        if (current == null) {
            return;
        }

        inOrder(current.getLeftChild(), nodes);
        nodes.add(current);
        inOrder(current.getRightChild(), nodes);
    }

    private void postOrder(Node<E> current, List<Node<E>> nodes) {
        if (current == null) {
            return;
        }

        postOrder(current.getLeftChild(), nodes);
        postOrder(current.getRightChild(), nodes);
        nodes.add(current);
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public TreeImpl<E> setMaxLevel(int maxLevel) {
        this.maxLevel = maxLevel;
        return this;
    }
}
