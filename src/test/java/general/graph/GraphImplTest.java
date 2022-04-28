package general.graph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GraphImplTest {

    private static final Graph<String> graphs = new GraphImpl<>(10);

    private static final String[] cites = new String[]{
            "Toronto", "Vancouver", "Montreal",
            "Victoria", "Halifax", "Quebec City",
            "Calgary", "Ottawa", "Edmonton",
            "Saskatoon"
    };


    @BeforeEach
    void initGraph() {
        for (String cite : cites) {
            graphs.addVertex(cite);
        }

        graphs.addEdge(cites[0], cites[1], 10);
        graphs.addEdge(cites[1], cites[2], 15);
        graphs.addEdge(cites[2], cites[9], 7);
        graphs.addEdge(cites[0], cites[3], 12);
        graphs.addEdge(cites[3], cites[4], 11);
        graphs.addEdge(cites[4], cites[9], 20);
        graphs.addEdge(cites[0], cites[5], 2);
        graphs.addEdge(cites[5], cites[6], 7);
        graphs.addEdge(cites[6], cites[9], 6);
        graphs.addEdge(cites[0], cites[7], 8);
        graphs.addEdge(cites[7], cites[8], 9);
        graphs.addEdge(cites[8], cites[9], 1);
    }

    /**
     * Toronto--10-->Vancouver--15-->Montreal--7-->Saskatoon (Weight is 32)
     * <p>
     * Toronto--12-->Victoria--11-->Halifax--20-->Saskatoon (Weigh is 43)
     * <p>
     * Toronto--2-->Quebec City--7-->Calgary--6-->Saskatoon (Weigh is 15)
     * <p>
     * Toronto--8-->Ottawa--9-->Edmonton--1-->Saskatoon (Weigh is 19)
     * <p>
     */
    @Test
    void findMin() {
        assertEquals(15, graphs.findMin(cites[0], cites[9]));
    }

}