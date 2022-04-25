package general.tree;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static general.tree.Tree.TraversMode.PRE_ORDER;

class TreeImplTest {

    private final Random random = new Random();
    private final TreeImpl[] trees = new TreeImpl[20];

    /**
     * @return random number from -25 to 25
     */
    private int getRandomNumber() {
        return random.nextInt(25 + 25) - 25;
    }

    private void initTree(int treeMaxLevel) {
        for (int i = 0; i < trees.length; i++) {
            TreeImpl<Integer> tree = new TreeImpl<>();
            trees[i] = tree.setMaxLevel(treeMaxLevel);
            for (int j = 1; j < 16; j++) {
                tree.add(getRandomNumber());
            }
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {4})
    void treeBalanceAnalysis(int treeMaxLevel) {
        initTree(treeMaxLevel);

        double balanced = 0.0;
        for (TreeImpl t : trees) {
            if (isBalance(getNodeList(t))) {
                balanced++;
            }
        }
        double balancedPercent = (balanced / ((double) trees.length / 100.0));
        Assertions.assertTrue(balancedPercent >= 80.0, "Balanced percent is lower than 80%, current balanced percent is: " + balancedPercent);
        System.out.println(balancedPercent);
    }

    private boolean isBalance(List<Node<Integer>> nodes) {
        int min = 0;
        int max = 0;

        Optional<Node<Integer>> minLevelNode = nodes.stream()
                .filter(n -> n.getRightChild() == null && n.getLeftChild() == null)
                .min(Comparator.comparing(Node::getLevel));

        if (minLevelNode.isPresent()) {
            min = minLevelNode.get().getLevel();
        }

        Optional<Node<Integer>> maxLevelNode = nodes.stream()
                .filter(n -> n.getRightChild() == null && n.getLeftChild() == null)
                .max(Comparator.comparing(Node::getLevel));

        if (maxLevelNode.isPresent()) {
            max = maxLevelNode.get().getLevel();
        }

        return (max - min) <= 1;
    }

    private int findLeafMaxLevel() {
        return -1;
    }

    private List<Node<Integer>> getNodeList(TreeImpl t) {
        List<Node<Integer>> nodes = new ArrayList<>();
        t.traverse(PRE_ORDER, nodes);
        return nodes;
    }

    @Test
    void contains() {
    }

    @Test
    void add() {
    }

    @Test
    void remove() {
    }

    @Test
    void isEmpty() {
    }

    @Test
    void size() {
    }

}