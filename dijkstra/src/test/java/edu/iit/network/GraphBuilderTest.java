package edu.iit.network;

import static org.junit.Assert.assertEquals;

import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.iit.network.dijkstra.model.Edge;
import edu.iit.network.dijkstra.model.Graph;
import edu.iit.network.dijkstra.model.Node;
import edu.iit.network.lsp.GraphBuilder;

public class GraphBuilderTest {

	private GraphBuilder builder;
	private Graph graph;

	@Before
	public void setUp()
	{
		List<List<Integer>> matrix = initMatrix();
		this.graph = initExpectedGraph();
		this.builder = new GraphBuilder(matrix);
	}

	@Test
	public void testBuildGraph()
	{
		Graph actual = builder.buildGraph();
		assertEquals(4, actual.getNodes().size());
		assertEquals(8, actual.getEdges().size());
		assertEquals(graph.getNodes(), actual.getNodes());
		assertEquals(graph.getEdges(), actual.getEdges());
		assertEquals(8, actual.getEdges().get(3).getWeight());
		assertEquals(2, actual.getEdges().get(7).getWeight());
	}

	private Graph initExpectedGraph()
	{
		Node r1 = new Node("R1");
		Node r2 = new Node("R2");
		Node r3 = new Node("R3");
		Node r4 = new Node("R4");

		Edge r1r2 = new Edge("R1R2", r1, r2, 2);
		Edge r1r3 = new Edge("R1R3", r1, r3, 5);

		Edge r2r1 = new Edge("R2R1", r2, r1, 2);
		Edge r2r3 = new Edge("R2R3", r2, r3, 8);

		Edge r3r1 = new Edge("R3R1", r3, r1, 5);
		Edge r3r2 = new Edge("R3R2", r3, r2, 8);
		Edge r3r4 = new Edge("R3R4", r3, r4, 2);

		Edge r4r3 = new Edge("R4R3", r4, r3, 2);

		List<Node> nodes = Arrays.asList(r1, r2, r3, r4);
		List<Edge> edges = Arrays.asList(r1r2, r1r3, r2r1, r2r3, r3r1, r3r2, r3r4, r4r3);
		Graph graph = new Graph(nodes, edges);
		return graph;
	}

	private List<List<Integer>> initMatrix()
	{
		List<Integer> line1 = Arrays.asList(0, 2, 5, -1);
		List<Integer> line2 = Arrays.asList(2, 0, 8, -1);
		List<Integer> line3 = Arrays.asList(5, 8, 0, 2);
		List<Integer> line4 = Arrays.asList(-1, -1, 2, 0);
		List<List<Integer>> matrix = new ArrayList<List<Integer>>();
		matrix.add(line1);
		matrix.add(line2);
		matrix.add(line3);
		matrix.add(line4);
		return matrix;
	}
}
