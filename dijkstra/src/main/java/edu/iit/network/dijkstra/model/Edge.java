package edu.iit.network.dijkstra.model;

public class Edge {

    private String id;
    private Node source;
    private Node destination;
    private int weight;

    public Edge(String id, Node source, Node destination, int weight) {
        super();
        this.id = id;
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return source + " " + destination;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        Edge rhs = (Edge) obj;
        if (id == null) {
            return false;
        }
        return id.equalsIgnoreCase(rhs.getId());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    /* GETTERS AND SETTERS */
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Node getSource() {
        return source;
    }

    public void setSource(Node source) {
        this.source = source;
    }

    public Node getDestination() {
        return destination;
    }

    public void setDestination(Node destination) {
        this.destination = destination;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
