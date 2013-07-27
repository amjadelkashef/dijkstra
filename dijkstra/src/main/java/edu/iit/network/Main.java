package edu.iit.network;

import static edu.iit.network.lsp.PrinterUtils.printOriginalRoutingTable;
import static edu.iit.network.lsp.PrinterUtils.printRoutingTableForRouter;
import static edu.iit.network.lsp.PrinterUtils.printShortestPathAndCost;
import static edu.iit.network.lsp.PrinterUtils.showUsage;
import static edu.iit.network.lsp.ValidationUtils.checkIfRouterSelectedIsValid;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.iit.network.dijkstra.DijikstraAlgorithm;
import edu.iit.network.dijkstra.model.Graph;
import edu.iit.network.dijkstra.model.Node;
import edu.iit.network.lsp.GraphBuilder;
import edu.iit.network.lsp.LSPException;
import edu.iit.network.lsp.OriginalRoutingTableLoader;
import edu.iit.network.lsp.OriginalRoutingTableParser;


public class Main {

	public static void main(String[] args)
	{
		String input = null;
		Scanner reader = new Scanner(System.in);
		showUsage();

		List<List<Integer>> matrix = new ArrayList<List<Integer>>();
		do
		{
			try
			{
				input = reader.next();
				if ("1".equals(input))
				{
					matrix = loadMatrixFile();
				} else if ("2".equals(input))
				{
					chooseRouterAndShowRoutingTable(matrix);
				} else if ("3".equals(input))
				{
					processAndShowShortestPath(matrix);
				} else if ("exit".equalsIgnoreCase(input))
				{
					System.out.println("Prompt: Bye bye");
				} else
				{
					System.out.println("ERROR: Unknown option :" + input);
				}
			} catch (LSPException e)
			{
				System.out.println("ERROR: " + e.getMessage());
				showUsage();
			}
		} while (!input.equalsIgnoreCase("exit"));

		reader.close();
	}

	private static List<List<Integer>> loadMatrixFile()
	{
		Scanner reader = new Scanner(System.in);
		System.out.println("Prompt: Please load original routing table data file:");
		String input = reader.next();

		OriginalRoutingTableLoader loader = new OriginalRoutingTableLoader();
		File file = loader.load(input);

		OriginalRoutingTableParser parser = new OriginalRoutingTableParser();
		List<List<Integer>> matrix = parser.parse(file);

		printOriginalRoutingTable(matrix);

		return matrix;
	}

	private static void chooseRouterAndShowRoutingTable(List<List<Integer>> matrix)
	{
		Scanner reader = new Scanner(System.in);
		System.out.println("Prompt: Please select a router");
		String input = reader.next();

		checkIfRouterSelectedIsValid(input, matrix);

		DijikstraAlgorithm algo = initAlgoWithMatrix(matrix);

		Node selectedRouter = createNode(input);
		algo.execute(selectedRouter);
		printRoutingTableForRouter(selectedRouter, algo.getDistances());
	}

	private static void processAndShowShortestPath(List<List<Integer>> matrix)
	{
		Scanner reader = new Scanner(System.in);
		System.out.println("Prompt: Please input the source and the destination router number");
		String input = reader.next();
		checkIfRouterSelectedIsValid(input, matrix);
		Node sourceNode = createNode(input);
		input = reader.next();
		checkIfRouterSelectedIsValid(input, matrix);
		Node destinationNode = createNode(input);

		DijikstraAlgorithm algo = initAlgoWithMatrix(matrix);

		algo.execute(sourceNode);
		List<Node> path = algo.getPath(destinationNode);

		printShortestPathAndCost(path, algo.getDistanceOfPath(path));
	}

	private static Node createNode(String id)
	{
		return new Node("R" + id);
	}

	private static DijikstraAlgorithm initAlgoWithMatrix(List<List<Integer>> matrix)
	{
		GraphBuilder builder = new GraphBuilder(matrix);
		Graph graph = builder.buildGraph();
		DijikstraAlgorithm algo = new DijikstraAlgorithm(graph);
		return algo;
	}
}
