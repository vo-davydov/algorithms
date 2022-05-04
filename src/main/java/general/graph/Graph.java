package general.graph;

public interface Graph<T> {

    void addVertex(T t);

    boolean addEdge(T start, T second, int weight, T... others);

    boolean addEdge(T start, T second, int weight);

    int getSize();

    void display();

    /**
     * Depth-first search, DFS
     */
    void dfs(T start);

    /**
     * Find minimum weight from start to end
     */
    int findMin(T start, T end);

    /**
     * Breadth-first search, BFS
     */
    void bfs(T start);

}
