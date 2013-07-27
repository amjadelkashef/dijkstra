package edu.iit.network.lsp;

import java.util.List;
import java.util.Map;

import edu.iit.network.dijkstra.model.Node;


public class PrinterUtils {

	public static void showUsage()
	{
		System.out.println("1- Load File");
		System.out.println("2- Building Routing Table for Each Router");
		System.out.println("3- Out Optimal Path and Minumum Cost");
	}

	public static void printOriginalRoutingTable(List<List<Integer>> matrix)
	{
		System.out.println("Prompt: Original routing table is as follow: ");
		for (List<Integer> line : matrix)
		{
			for (Integer cost : line)
			{
				System.out.print(" " + cost + "   ");
			}
			System.out.println();
		}
	}

	public static void printRoutingTableForRouter(Node router, Map<Node, Integer> distances)
	{
		System.out.println("Prompt: The routing table for router " + router.getId() + ": ");
		System.out.println(distances);
	}

	public static void printShortestPathAndCost(List<Node> path, int cost)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("Prompt: The shortest path from ");
		sb.append(path.get(0).getId());
		sb.append(" to ");
		sb.append(path.get(path.size() - 1).getId());
		sb.append(" is: ");
		for (int i = 0; i < path.size(); i++)
		{
			sb.append(path.get(i).getId());
			if (i < path.size() - 1)
			{
				sb.append("-");
			}
		}
		sb.append(", the total cost is ");
		sb.append(cost);
		System.out.println(sb.toString());
	}
}
