package edu.iit.network.lsp;

import java.util.ArrayList;
import java.util.List;

import edu.iit.network.dijkstra.model.Edge;
import edu.iit.network.dijkstra.model.Graph;
import edu.iit.network.dijkstra.model.Node;


public class GraphBuilder {

    private List<List<Integer>> originalMatrix = new ArrayList<List<Integer>>();

    public GraphBuilder(List<List<Integer>> originalMatrix) {
        super();
        this.originalMatrix = originalMatrix;
    }

    public Graph buildGraph() {
        List<Node> nodes = createNodes();
        List<Edge> edges = createEdges(nodes);
        Graph graph = new Graph(nodes, edges);
        return graph;

    }

    private List<Edge> createEdges(List<Node> nodes) {
        List<Edge> edges = new ArrayList<Edge>();
        for (int i = 0; i < nodes.size(); i++) {
            for (int j = 0; j < nodes.size(); j++) {
                if (!nodes.get(i).equals(nodes.get(j)) && originalMatrix.get(i).get(j) != -1) {
                    Edge e = new Edge(nodes.get(i).getId() + nodes.get(j).getId(), nodes.get(i), nodes.get(j), originalMatrix.get(i).get(j));
                    edges.add(e);
                }
            }
        }
        return edges;
    }

    private List<Node> createNodes() {
        List<Node> nodes = new ArrayList<Node>();
        for (int i = 0; i < originalMatrix.size(); i++) {
            nodes.add(new Node("R" + (i + 1)));
        }
        return nodes;
    }
}
