package interview.challenge.directed.weighted.graph.controller;

import interview.challenge.directed.weighted.graph.adapter.GraphTextualApi;

public class GraphController {

	public static final String EXIT = "Exit";

	private final GraphUI graphUI;
	private final GraphTextualApi graphApi;

	public GraphController(GraphUI ui, GraphTextualApi graphApi) {
		this.graphUI = ui;
		this.graphApi = graphApi;
	}

	public void start() {
		graphUI.write("Insert graph:");
		String strGraph = graphUI.read();
		long graphId = graphApi.createGraph(strGraph);
		while (true) {
			graphUI.write("Insert query:");
			String query = graphUI.read();
			if (EXIT.equalsIgnoreCase(query)) {
				break;
			}
			String answer = graphApi.query(graphId, query);
			graphUI.write("Answer: " + answer);
		}
	}

}
