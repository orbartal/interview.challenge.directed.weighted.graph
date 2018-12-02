package interview.challenge.directed.weighted.graph.adapter;

import java.util.Optional;

import interview.challenge.directed.weighted.graph.model.Graph;

public class GraphTextualApi {

	final private GraphStore graphStore;
	final private QueryAdapterFactory queryAdapterFactory;
	final private GraphParser graphParser;

	public GraphTextualApi(GraphParser graphParser, GraphStore graphStore, QueryAdapterFactory queryAdapterFactory) {
		this.graphParser = graphParser;
		this.graphStore = graphStore;
		this.queryAdapterFactory = queryAdapterFactory;
	}

	public long createGraph(String strGraph) {
		Graph graph = graphParser.apply(strGraph);
		return graphStore.save(graph);
	}

	public String query(long grpahId, String input) {
		String line = toStandardizeLine(input);
		Optional<QueryAdapter> pquery = queryAdapterFactory.getQuery(line);
		if (!pquery.isPresent()) {
			throw new RuntimeException("Invalid input query: " + input);
		}
		Optional<Graph> pgraph = graphStore.get(grpahId);
		if (!pgraph.isPresent()) {
			throw new RuntimeException("No graph found with grpah id: " + grpahId);
		}
		QueryAdapter queryAdapter = pquery.get();
		Graph graph = pgraph.get();
		return queryAdapter.execute(graph, line);
	}

	private String toStandardizeLine(String line) {
		line = line.toLowerCase().trim();
		if (line.endsWith(".")) {
			line = line.substring(0, line.length() - 1);
		}
		return line;
	}

}
