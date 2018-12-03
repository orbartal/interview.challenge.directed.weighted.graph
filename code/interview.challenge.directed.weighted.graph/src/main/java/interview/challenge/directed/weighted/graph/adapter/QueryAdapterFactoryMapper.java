package interview.challenge.directed.weighted.graph.adapter;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import interview.challenge.directed.weighted.graph.interactor.dijkstra.QueryAdapterDijkstra;
import interview.challenge.directed.weighted.graph.interactor.pathdistance.QueryAdapterPathDistance;
import interview.challenge.directed.weighted.graph.interactor.pathsbyedgesweight.QueryAdapterPathsByEdgesWeight;
import interview.challenge.directed.weighted.graph.interactor.pathsbynodescounter.QueryAdapterPathsByNodesCounter;

public class QueryAdapterFactoryMapper implements QueryAdapterFactory {

	private static List<QueryAdapter> getAllQueries() {
		QueryAdapter pathDistance = new QueryAdapterPathDistance();
		QueryAdapter dijkstra = new QueryAdapterDijkstra();
		QueryAdapter pathsByNodesCounter = new QueryAdapterPathsByNodesCounter();
		QueryAdapter pathsByEdgesWeight = new QueryAdapterPathsByEdgesWeight();
		return Arrays.asList(pathDistance, dijkstra, pathsByNodesCounter, pathsByEdgesWeight);
	}

	private final List<QueryAdapter> queries;

	public QueryAdapterFactoryMapper() {
		this(getAllQueries());
	}

	public QueryAdapterFactoryMapper(List<QueryAdapter> queries) {
		this.queries = queries;
	}

	public Optional<QueryAdapter> getQuery(String line) {
		return this.queries.stream().filter(c -> c.isMatch(line)).findFirst();
	}

}
