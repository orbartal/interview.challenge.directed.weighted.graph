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
		try {
			graphUI.connect();
			graphUI.writeRequestForGraph();
			String strGraph = graphUI.readGraphAsString();
			long graphId = graphApi.createGraph(strGraph);
			while (true) {
				graphUI.writeRequestForCommand();
				String query = graphUI.readCommand();
				if (EXIT.equalsIgnoreCase(query)) {
					break;
				}
				String answer = graphApi.query(graphId, query);
				graphUI.writeResponseToCommand(answer);
			}
		} finally {
			graphUI.close();
		}
	}

}
