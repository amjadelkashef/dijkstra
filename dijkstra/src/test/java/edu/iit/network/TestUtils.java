package edu.iit.network;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestUtils {

	private static final String ORT_FILENAME = "network.txt";

	public static String getNetworkFilePathFromClasspath() throws URISyntaxException
	{
		File file = new File(TestUtils.class.getResource(ORT_FILENAME).toURI());
		return file.getAbsolutePath();
	}

	public static File getNetworkFileFromClasspath() throws URISyntaxException
	{
		return new File(TestUtils.class.getResource(ORT_FILENAME).toURI());
	}

	public static List<List<Integer>> getMatrix()
	{
		List<List<Integer>> matrix = new ArrayList<List<Integer>>();
		matrix.add(Arrays.asList(0, 2, 5, 1, -1));
		matrix.add(Arrays.asList(2, 0, 8, 7, 9));
		matrix.add(Arrays.asList(5, 8, 0, -1, 4));
		matrix.add(Arrays.asList(1, 7, -1, 0, 2));
		matrix.add(Arrays.asList(-1, 9, 4, 2, 0));
		return matrix;
	}
}
