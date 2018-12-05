package interview.challenge.directed.weighted.graph.e2e;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

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
import interview.challenge.directed.weighted.graph.external.SingleGraphStore;

public class BackendEndToEndTest {

	@Test
	public void testWithDataProviderHardCoding() {
		testGraphControllerWithDataProvider(new DataProviderHardCoding());
	}
	
	@Test
	public void testWithDataProviderFromFile() throws IOException, URISyntaxException {
		File file = Paths.get("src", "test", "resources", "test_e2e_input.txt").toFile();
		testGraphControllerWithDataProvider(new DataProviderFromFile(file));
	}

	private void testGraphControllerWithDataProvider(DataProvider dataProvider) {
		// setup
		GraphPredeterminedUI ui = getGraphPredeterminedUI(dataProvider);
		GraphController fixture = getGraphController(ui);
		// execute
		fixture.start();
		// verify
		LinkedHashMap<String, String> expected = dataProvider.getMapAnswerByQuery();
		LinkedHashMap<String, String> actual = ui.getResults();
		Assertions.assertEquals(expected, actual);

	}

	private GraphPredeterminedUI getGraphPredeterminedUI(DataProvider dataProvider) {
		String inputGraph = dataProvider.getGraphAsString();
		LinkedHashMap<String, String> inputQA = dataProvider.getMapAnswerByQuery();
		List<String> inputCommands = new ArrayList<>(inputQA.keySet());
		GraphPredeterminedUI ui = new GraphPredeterminedUI(inputGraph, inputCommands);
		return ui;
	}

	private GraphController getGraphController(GraphUI ui) {
		GraphStore db = new SingleGraphStore();
		QueryAdapterFactory factory = new QueryAdapterFactoryMapper();
		GraphParser graphParser = new GraphFromStringOfEdges();
		GraphTextualApi graphApi = new GraphTextualApi(graphParser, db, factory);
		return new GraphController(ui, graphApi);
	}

}
