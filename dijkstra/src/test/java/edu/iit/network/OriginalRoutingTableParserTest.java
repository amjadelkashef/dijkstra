package edu.iit.network;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.net.URISyntaxException;
import java.util.List;


import org.junit.Before;
import org.junit.Test;

import edu.iit.network.lsp.OriginalRoutingTableParser;

public class OriginalRoutingTableParserTest {

	private OriginalRoutingTableParser parser;

	@Before
	public void setUp()
	{
		parser = new OriginalRoutingTableParser();
	}

	@Test
	public void testParse_OK() throws URISyntaxException
	{
		File ortFile = TestUtils.getNetworkFileFromClasspath();
		List<List<Integer>> matrix = parser.parse(ortFile);
		assertEquals(TestUtils.getMatrix(), matrix);
	}

}
