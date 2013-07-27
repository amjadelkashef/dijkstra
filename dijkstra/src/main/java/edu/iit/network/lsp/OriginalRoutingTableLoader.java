package edu.iit.network.lsp;

import java.io.File;

public class OriginalRoutingTableLoader {

	public File load(String originalRoutingTableFilePath)
	{
		File originalRoutingTableFile = new File(originalRoutingTableFilePath);
		checkIfFileIsCorrect(originalRoutingTableFilePath, originalRoutingTableFile);
		return originalRoutingTableFile;
	}

	private void checkIfFileIsCorrect(String originalRoutingTableFilePath, File originalRoutingTableFile)
	{
		checkIfTextFormat(originalRoutingTableFilePath);
		checksIfFileExists(originalRoutingTableFilePath, originalRoutingTableFile);
	}

	private void checkIfTextFormat(String originalRoutingTableFilePath)
	{
		if (!originalRoutingTableFilePath.endsWith(".txt"))
		{
			throw new LSPException("The file: " + originalRoutingTableFilePath + " is not a text file.");
		}
	}

	private void checksIfFileExists(String originalRoutingTableFilePath, File originalRoutingTableFile)
	{
		if (originalRoutingTableFile == null || !originalRoutingTableFile.exists())
		{
			throw new LSPException("The file: " + originalRoutingTableFilePath + " does not exist.");
		}
	}
}
