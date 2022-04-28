package general.graph;

import java.util.Objects;

public class Vertex<T> {
    private final T t;
    private boolean isVisited;

    public Vertex(T t) {
        this.t = t;
    }

    public T getT() {
        return t;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex<?> vertex = (Vertex<?>) o;
        return t.equals(vertex.t);
    }

    @Override
    public int hashCode() {
        return Objects.hash(t);
    }

}
