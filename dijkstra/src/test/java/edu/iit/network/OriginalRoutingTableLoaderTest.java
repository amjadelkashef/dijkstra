package edu.iit.network;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.net.URISyntaxException;


import org.junit.Before;
import org.junit.Test;

import edu.iit.network.lsp.LSPException;
import edu.iit.network.lsp.OriginalRoutingTableLoader;

public class OriginalRoutingTableLoaderTest {

	private OriginalRoutingTableLoader loader;

	@Before
	public void setUp()
	{
		loader = new OriginalRoutingTableLoader();
	}

	@Test
	public void testLoad_OK() throws URISyntaxException
	{
		File loadedFile = loader.load(TestUtils.getNetworkFilePathFromClasspath());
		assertNotNull(loadedFile);
		assertTrue(loadedFile.exists());
	}

	@Test(expected = LSPException.class)
	public void testFileNotFound()
	{
		loader.load("imaginary-file.txt");
	}

	@Test(expected = LSPException.class)
	public void testFileNotInTextFormat()
	{
		loader.load("not-a-text-file");
	}
}
