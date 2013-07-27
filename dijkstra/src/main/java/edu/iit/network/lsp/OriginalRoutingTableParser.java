package edu.iit.network.lsp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OriginalRoutingTableParser {

	public List<List<Integer>> parse(File ortFile)
	{
		try
		{
			List<List<Integer>> matrix = parseFile(ortFile);
			return matrix;
		} catch (FileNotFoundException e)
		{
			throw new LSPException(e);
		} catch (IOException e)
		{
			throw new LSPException("An error occured during parsing of file: " + ortFile.getName(), e);
		}
	}

	private List<List<Integer>> parseFile(File ortFile) throws IOException
	{
		List<List<Integer>> matrix = new ArrayList<List<Integer>>();
		FileReader fr = new FileReader(ortFile);
		try
		{
			BufferedReader br = new BufferedReader(fr);
			try
			{
				String l;
				while ((l = br.readLine()) != null)
				{
					String[] costs = l.split("\\s+");
					List<Integer> line = new ArrayList<Integer>();
					for (String cost : costs)
					{
						line.add(parseInteger(cost));
					}
					matrix.add(line);
				}
			} finally
			{
				if (br != null)
				{
					br.close();
				}
			}
		} finally
		{
			if (fr != null)
			{
				fr.close();
			}
		}
		return matrix;
	}

	private int parseInteger(String cost)
	{
		try
		{
			return Integer.parseInt(cost.trim());
		} catch (NumberFormatException e)
		{
			throw new LSPException("The original routing table file contains a non-integer character: " + cost);
		}
	}
}
