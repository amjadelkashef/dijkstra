package edu.iit.network.dijkstra.model;

import java.util.ArrayList;
import java.util.List;

public class Graph {

    private List<Node> nodes = new ArrayList<Node>();
    private List<Edge> edges = new ArrayList<Edge>();

    public Graph(List<Node> nodes, List<Edge> edges) {
        super();
        this.nodes = nodes;
        this.edges = edges;
    }

    /* GETTERS */
    public List<Node> getNodes() {
        return nodes;
    }

    public List<Edge> getEdges() {
        return edges;
    }

}
