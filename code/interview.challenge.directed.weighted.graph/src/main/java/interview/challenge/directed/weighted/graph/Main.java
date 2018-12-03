package interview.challenge.directed.weighted.graph;

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

public class Main {

	public static void main(String[] args) {
		GraphUI ui = new GraphConsoleUI();
		GraphStore db = new SingleGraphStore();
		QueryAdapterFactory factory = new QueryAdapterFactoryMapper();
		GraphParser graphParser = new GraphFromStringOfEdges();
		GraphTextualApi graphApi = new GraphTextualApi(graphParser, db, factory);
		GraphController contoller = new GraphController(ui, graphApi);
		contoller.start();
		// ui.close();
	}

}
