package edu.iit.network.dijkstra;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import org.junit.Before;
import org.junit.Test;

import edu.iit.network.dijkstra.DijikstraAlgorithm;
import edu.iit.network.dijkstra.model.Edge;
import edu.iit.network.dijkstra.model.Graph;
import edu.iit.network.dijkstra.model.Node;

public class DijikstraAlgorithmTest {

    private DijikstraAlgorithm algo;

    @Before
    public void setUp() {
        this.algo = new DijikstraAlgorithm();
    }

    @Test
    public void testGetShortestDistanceFromSource() {
        Node node1 = new Node("1");
        algo.addDistance(node1, 2);
        int actual = this.algo.getShortestDistanceFromSource(node1);
        assertEquals(2, actual);

        Node node2 = new Node("2");
        actual = this.algo.getShortestDistanceFromSource(node2);
        assertEquals(Integer.MAX_VALUE, actual);
    }

    @Test
    public void testGetClosestNodeFromSource() {
        Node node1 = new Node("1");
        Node node2 = new Node("2");
        Node node3 = new Node("3");
        algo.addDistance(node1, 3).addDistance(node2, 1).addDistance(node3, 2);
        Set<Node> nodes = new HashSet<Node>();
        nodes.add(node1);
        nodes.add(node2);
        nodes.add(node3);

        Node actual = algo.getClosestNodeFromSource(nodes);
        assertEquals(node2, actual);
    }

    @Test
    public void testIsSettled() {
        Node node1 = new Node("1");
        Node node2 = new Node("2");
        algo.addSettledNode(node1);
        assertTrue(algo.isSettled(node1));
        assertFalse(algo.isSettled(node2));
    }

    @Test
    public void testGetNeighborsNotSettled() {
        Node a = new Node("A");
        Node b = new Node("B");
        Node c = new Node("C");
        Node d = new Node("D");
        Node e = new Node("E");
        Edge ab = new Edge("ab", a, b, 2);
        Edge ac = new Edge("ac", a, c, 7);
        Edge ae = new Edge("ae", a, e, 4);
        Edge cd = new Edge("cd", c, d, 3);
        List<Node> nodes = Arrays.asList(a, b, c, d, e);
        List<Edge> edges = Arrays.asList(ab, ac, ae, cd);
        Graph graph = new Graph(nodes, edges);
        this.algo = new DijikstraAlgorithm(graph);
        algo.addSettledNode(e);

        List<Node> actualNeighbors = algo.getNeighborsNotSettled(new Node("A"));
        assertNotNull(actualNeighbors);
        assertEquals(2, actualNeighbors.size());
        assertTrue(actualNeighbors.contains(new Node("B")));
        assertTrue(actualNeighbors.contains(new Node("C")));
    }

    @Test
    public void testExecute() {
        Node r1 = new Node("R1");
        Node r2 = new Node("R2");
        Node r3 = new Node("R3");
        Node r4 = new Node("R4");
        Node r5 = new Node("R5");

        Edge r1r2 = new Edge("r1r2", r1, r2, 2);
        Edge r1r3 = new Edge("r1r3", r1, r3, 5);
        Edge r1r4 = new Edge("r1r4", r1, r4, 1);

        Edge r2r1 = new Edge("r2r1", r2, r1, 2);
        Edge r2r3 = new Edge("r2r3", r2, r3, 8);
        Edge r2r4 = new Edge("r2r4", r2, r4, 7);
        Edge r2r5 = new Edge("r2r5", r2, r5, 9);

        Edge r3r1 = new Edge("r3r1", r3, r1, 5);
        Edge r3r2 = new Edge("r3r2", r3, r2, 8);
        Edge r3r5 = new Edge("r3r5", r3, r5, 4);

        Edge r4r1 = new Edge("r4r1", r4, r1, 1);
        Edge r4r2 = new Edge("r4r2", r4, r2, 7);
        Edge r4r5 = new Edge("r4r5", r4, r5, 2);

        Edge r5r2 = new Edge("r5r2", r5, r2, 9);
        Edge r5r3 = new Edge("r5r3", r5, r3, 4);
        Edge r5r4 = new Edge("r5r4", r5, r4, 2);

        List<Node> nodes = Arrays.asList(r1, r2, r3, r4, r5);
        List<Edge> edges = Arrays.asList(r1r2, r1r3, r1r4, r2r1, r2r3, r2r4, r2r5, r3r1, r3r2, r3r5, r4r1, r4r2, r4r5, r5r2, r5r3, r5r4);
        Graph graph = new Graph(nodes, edges);
        DijikstraAlgorithm algo = new DijikstraAlgorithm(graph);

        algo.execute(r4);
        List<Node> path = algo.getPath(r3);
        List<Node> expected = Arrays.asList(r4, r1, r3);
        assertEquals(expected, path);
        assertEquals(6, algo.getDistanceOfPath(path));

        algo.execute(r1);
        path = algo.getPath(r5);
        expected = Arrays.asList(r1, r4, r5);
        assertEquals(expected, path);
        assertEquals(3, algo.getDistanceOfPath(path));
    }

}
