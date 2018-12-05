package interview.challenge.directed.weighted.graph.e2e;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import interview.challenge.directed.weighted.graph.adapter.GraphFromStringOfEdges;
import interview.challenge.directed.weighted.graph.adapter.GraphParser;
import interview.challenge.directed.weighted.graph.adapter.GraphStore;
import interview.challenge.directed.weighted.graph.adapter.GraphTextualApi;
import interview.challenge.directed.weighted.graph.adapter.QueryAdapterFactory;
import interview.challenge.directed.weighted.graph.adapter.QueryAdapterFactoryMapper;
import interview.challenge.directed.weighted.graph.controller.GraphController;
import interview.challenge.directed.weighted.graph.controller.GraphUI;
import interview.challenge.directed.weighted.graph.external.GraphConsoleUI;
import interview.challenge.directed.weighted.graph.external.SingleGraphStore;

public class ConsoleEndToEndTest {

	private final InputStream systemIn = System.in;
	private final PrintStream systemOut = System.out;

	private ByteArrayInputStream testIn;
	private ByteArrayOutputStream testOut;

	@AfterEach
	public void restoreSystemInputOutput() {
		System.setIn(systemIn);
		System.setOut(systemOut);
	}

	@Test
	public void testWithDataProviderHardCoding() {
		testGraphControllerWithDataProvider(new DataProviderHardCoding());
	}

	@Test
	public void testWithDataProviderFromFile() throws IOException, URISyntaxException {
		File file = Paths.get("src", "test", "resources", "test_e2e_input.txt").toFile();
		testGraphControllerWithDataProvider(new DataProviderFromFile(file));
	}

	public void testGraphControllerWithDataProvider(DataProvider dataProvider) {
		// setup
		setInput(getInputDataAsString(dataProvider));
		setUpOutput();
		GraphController fixture = getGraphController(new GraphConsoleUI());
		// execute
		fixture.start();
		// verify
		String actualOutput = getOutput();
		String expectedOutput = getExpectedOutput(dataProvider);
		Assertions.assertEquals(actualOutput, expectedOutput);
	}

	private String getInputDataAsString(DataProvider dataProvider) {
		String inputGraph = dataProvider.getGraphAsString();
		LinkedHashMap<String, String> inputQA = dataProvider.getMapAnswerByQuery();
		List<String> inputCommands = new ArrayList<>(inputQA.keySet());
		StringBuilder sb = new StringBuilder();
		sb.append(inputGraph + "\n");
		for (String command : inputCommands) {
			sb.append(command + "\n");
		}
		sb.append(GraphController.EXIT + "\n");
		String input = sb.toString();
		return input;
	}

	private GraphController getGraphController(GraphUI ui) {
		GraphStore db = new SingleGraphStore();
		QueryAdapterFactory factory = new QueryAdapterFactoryMapper();
		GraphParser graphParser = new GraphFromStringOfEdges();
		GraphTextualApi graphApi = new GraphTextualApi(graphParser, db, factory);
		return new GraphController(ui, graphApi);
	}

	private String getExpectedOutput(DataProvider dataProvider) {
		List<String> expectedOutputs = new ArrayList<>(dataProvider.getMapAnswerByQuery().values());
		StringBuilder sb = new StringBuilder();
		sb.append("Insert graph:\n");
		for (String command : expectedOutputs) {
			sb.append("Insert query:\n");
			sb.append("Answer: " + command + "\n");
		}
		sb.append("Insert query:\n");
		String expectedOutput = sb.toString();
		return expectedOutput;
	}

	private void setInput(String data) {
		testIn = new ByteArrayInputStream(data.getBytes());
		System.setIn(testIn);
	}

	public void setUpOutput() {
		testOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(testOut));
	}

	private String getOutput() {
		return testOut.toString();
	}

}
