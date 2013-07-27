package edu.iit.network.dijkstra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import edu.iit.network.dijkstra.model.Edge;
import edu.iit.network.dijkstra.model.Graph;
import edu.iit.network.dijkstra.model.Node;


public class DijikstraAlgorithm {

	private List<Node> nodes = new ArrayList<Node>();
	private List<Edge> edges = new ArrayList<Edge>();
	private Set<Node> settledNodes = new HashSet<Node>();
	private Set<Node> unSettledNodes = new HashSet<Node>();
	private Map<Node, Integer> distances = new TreeMap<Node, Integer>();;
	private Map<Node, Node> predecessors = new HashMap<Node, Node>();

	// For test purposes
	DijikstraAlgorithm()
	{
	}

	public DijikstraAlgorithm(Graph graph)
	{
		this.nodes = new ArrayList<Node>(graph.getNodes());
		this.edges = new ArrayList<Edge>(graph.getEdges());
	}

	/**
	 * Builds the routing table of a given node.
	 * 
	 * @param source
	 */
	public void execute(Node source)
	{
		reset();
		distances.put(source, 0);
		unSettledNodes.add(source);
		while (unSettledNodes.size() > 0)
		{
			Node node = getClosestNodeFromSource(unSettledNodes);
			settledNodes.add(node);
			unSettledNodes.remove(node);
			findMinimalDistances(node);
		}
	}

	/**
	 * Returns the path from the source to the target
	 * 
	 */
	public LinkedList<Node> getPath(Node target)
	{
		LinkedList<Node> path = new LinkedList<Node>();
		Node step = target;
		if (predecessors.get(step) == null)
		{
			return null;
		}
		path.add(step);
		while (predecessors.get(step) != null)
		{
			step = predecessors.get(step);
			path.add(step);
		}
		Collections.reverse(path);
		return path;
	}

	/**
	 * Gets the total cost of a path
	 * 
	 * @param path
	 * @return
	 */
	public int getDistanceOfPath(List<Node> path)
	{
		return getShortestDistanceFromSource(path.get(path.size() - 1));
	}

	void reset()
	{
		settledNodes.clear();
		unSettledNodes.clear();
		distances.clear();
		predecessors.clear();
	}

	/**
	 * Gets the shortest distance between the source and the destination
	 * 
	 * @param destination
	 *            - Destination node
	 * @return shortest distance between the source and the destination if it
	 *         exists, the highest integer value otherwise.
	 */
	int getShortestDistanceFromSource(Node destination)
	{
		Integer d = distances.get(destination);
		if (d == null)
		{
			return Integer.MAX_VALUE;
		}
		return d;
	}

	/**
	 * Gets the closest Node from the source selected amoung a set of given
	 * nodes.
	 * 
	 * @param nodes
	 * @return
	 */
	Node getClosestNodeFromSource(Set<Node> nodes)
	{
		Node minimum = null;
		for (Node node : nodes)
		{
			if (minimum == null)
			{
				minimum = node;
			} else
			{
				if (getShortestDistanceFromSource(node) < getShortestDistanceFromSource(minimum))
				{
					minimum = node;
				}
			}
		}
		return minimum;
	}

	/**
	 * Checks if a node is already settled (processed)
	 * 
	 * @param node
	 * @return
	 */
	boolean isSettled(Node node)
	{
		return settledNodes.contains(node);
	}

	/**
	 * Retrieves the neighbor nodes of a given node out of the unsettled nodes.
	 * 
	 * @param node
	 * @return
	 */
	List<Node> getNeighborsNotSettled(Node node)
	{
		ArrayList<Node> neighbors = new ArrayList<Node>();
		for (Edge edge : this.edges)
		{
			if (node.equals(edge.getSource()) && !isSettled(edge.getDestination()))
			{
				neighbors.add(edge.getDestination());
			}
		}
		return neighbors;
	}

	/**
	 * Gets the distance between two nodes.
	 * 
	 * @param node
	 * @param target
	 * @return
	 */
	int getDistance(Node node, Node target)
	{
		for (Edge edge : edges)
		{
			if (edge.getSource().equals(node) && edge.getDestination().equals(target))
			{
				return edge.getWeight();
			}
		}
		throw new RuntimeException("Should not happen");
	}

	/**
	 * Updates the distances between the source and the not settled neighboors
	 * of a node if its lower than the ones computed previously
	 * 
	 * @param node
	 */
	void findMinimalDistances(Node node)
	{
		List<Node> adjacentNodes = getNeighborsNotSettled(node);
		for (Node target : adjacentNodes)
		{
			if (getShortestDistanceFromSource(target) > getShortestDistanceFromSource(node) + getDistance(node, target))
			{
				distances.put(target, getShortestDistanceFromSource(node) + getDistance(node, target));
				predecessors.put(target, node);
				unSettledNodes.add(target);
			}
		}

	}

	/* FOR TEST PURPOSES */
	DijikstraAlgorithm addDistance(Node node, Integer distance)
	{
		distances.put(node, distance);
		return this;
	}

	/* FOR TEST PURPOSES */
	DijikstraAlgorithm addSettledNode(Node node)
	{
		settledNodes.add(node);
		return this;
	}

	/* GETTERS */
	public List<Node> getNodes()
	{
		return nodes;
	}

	public List<Edge> getEdges()
	{
		return edges;
	}

	public Map<Node, Integer> getDistances()
	{
		return distances;
	}
}
