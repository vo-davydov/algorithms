package grokking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GraphAdjacencyListTest {
    private static Graph graph;


    @BeforeEach
    private void createGraph() {
        graph = new Graph();
        graph.addVertex("Bob");
        graph.addVertex("Alice");
        graph.addVertex("Mark");
        graph.addVertex("Rob");
        graph.addVertex("Maria");
        graph.addEdge("Bob", "Alice");
        graph.addEdge("Bob", "Rob");
        graph.addEdge("Alice", "Mark");
        graph.addEdge("Rob", "Mark");
        graph.addEdge("Alice", "Maria");
        graph.addEdge("Rob", "Maria");
    }

    @Test
    public void testAdjacencyList() {
        assertEquals("[Bob, Rob, Maria, Alice, Mark]", Graph.depthFirstTraversal(graph, "Bob").toString());
        assertEquals("[Bob, Alice, Rob, Mark, Maria]", Graph.breadthFirstTraversal(graph, "Bob").toString());
    }

}