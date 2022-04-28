package general.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.Stack;

public class GraphImpl<T> implements Graph<T> {

    private final List<Vertex<T>> vertexList;
    private final List<Edge> edgeList;
    private final int[][] adjMatrix;

    public GraphImpl(int maxVertexCount) {
        this.vertexList = new ArrayList<>(maxVertexCount);
        this.edgeList = new ArrayList<>();
        this.adjMatrix = new int[maxVertexCount][maxVertexCount];
    }

    @Override
    public void addVertex(T t) {
        vertexList.add(new Vertex(t));
    }

    @SafeVarargs
    @Override
    public final boolean addEdge(T start, T second, int weight, T... others) {
        boolean result = addEdge(start, second, weight);

        for (T t : others) {
            result &= addEdge(start, t, weight);
        }

        return result;
    }

    @Override
    public boolean addEdge(T start, T second, int weight) {
        int startIndex = indexOf(start);
        int endIndex = indexOf(second);

        if (startIndex == -1 || endIndex == -1) {
            return false;
        }

        adjMatrix[startIndex][endIndex] = weight;

        Edge edge = new Edge(start, second, weight);
        edgeList.add(edge);

        return true;
    }

    private int indexOf(T t) {
        for (int i = 0; i < vertexList.size(); i++) {
            if (vertexList.get(i).getT().equals(t)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int getSize() {
        return vertexList.size();
    }

    @Override
    public void display() {
        System.out.println(this);
    }

    @Override
    public void dfs(T start) {
        int startIndex = indexOf(start);

        if (startIndex == 0) {
            throw new IllegalArgumentException("Vertex is not correct: " + start);
        }

        Stack<Vertex<T>> stack = new Stack<>();
        Vertex<T> vertex = vertexList.get(startIndex);

        visitVertex(stack, vertex);

        while (!stack.isEmpty()) {
            vertex = getNearUnvisitedVertex(stack.peek());
            if (vertex == null) {
                stack.pop();
            } else {
                visitVertex(stack, vertex);
            }
        }
    }

    @Override
    public int findMin(T start, T end) {
        Stack<Edge> stack = new Stack<>();

        edgeList.stream()
                .filter(e -> start.equals(e.getStart()))
                .forEach(stack::push);

        List<Integer> weights = new ArrayList<>();

        int count = 0;
        while (!stack.isEmpty()) {
            Edge current = stack.peek();

            if (current != null && !current.isVisited()) {
                count += current.getWeight();
            }

            Edge next = getNearUnvisitedEdge(current.getEnd());

            if (end.equals(current.getEnd())) {
                weights.add(count);
            }

            if (next == null || next.isVisited()) {
                count -= current.getWeight();
                stack.pop();
            } else {
                stack.push(next);
            }

            current.setVisited(true);
        }

        for (Edge e : edgeList) {
            e.setVisited(false);
        }

        return weights.stream().mapToInt(v -> v).min().orElse(-1);
    }

    private Vertex<T> getNearUnvisitedVertex(Vertex<T> vertex) {
        int currentIndex = vertexList.indexOf(vertex);

        for (int i = 0; i < getSize(); i++) {
            if (adjMatrix[currentIndex][i] != 0 && !vertexList.get(i).isVisited()) {
                return vertexList.get(i);
            }
        }

        return null;
    }

    private Edge getNearUnvisitedEdge(T start) {
        Optional<Edge> edge = edgeList.stream()
                .filter(e -> !e.isVisited && start.equals(e.getStart()))
                .findFirst();

        return edge.orElse(null);

    }

    private void visitVertex(Stack<Vertex<T>> stack, Vertex<T> vertex) {
        System.out.println(vertex.getT() + " ");
        stack.push(vertex);
        vertex.setVisited(true);
    }

    private void visitVertex(Queue<Vertex<T>> queue, Vertex<T> vertex) {
        System.out.println(vertex.getT() + " ");
        queue.add(vertex);
        vertex.setVisited(true);
    }

    @Override
    public void bfs(T start) {
        int startIndex = indexOf(start);

        if (startIndex == -1) {
            throw new IllegalArgumentException("Vertex is not correct: " + start);
        }

        Queue<Vertex<T>> queue = new LinkedList<>();
        Vertex<T> vertex = vertexList.get(startIndex);

        visitVertex(queue, vertex);

        while (!queue.isEmpty()) {
            vertex = getNearUnvisitedVertex(queue.peek());
            if (vertex == null) {
                queue.remove();
            } else {
                visitVertex(queue, vertex);
            }
        }
    }

    private class Edge implements Comparable<Edge> {
        private final T start;
        private final T end;
        private final int weight;
        private boolean isVisited;

        public Edge(T start, T end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        public T getStart() {
            return start;
        }

        public T getEnd() {
            return end;
        }

        public boolean isVisited() {
            return isVisited;
        }


        public void setVisited(boolean visited) {
            isVisited = visited;
        }

        public int getWeight() {
            return weight;
        }


        @Override
        public int compareTo(Edge o) {
            return Integer.compare(o.getWeight(), this.weight);
        }
    }
}
