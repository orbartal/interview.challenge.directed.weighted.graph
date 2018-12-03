package interview.challenge.directed.weighted.graph.interactor.pathsbynodescounter;

import java.util.regex.Pattern;

import interview.challenge.directed.weighted.graph.adapter.QueryAdapter;
import interview.challenge.directed.weighted.graph.model.Graph;

public class QueryAdapterPathsByNodesCounter implements QueryAdapter {

	static private final String lineRegex = "the number of trips starting at [a-z] and ending at [a-z] with( a)? (exactly|maximum)( of)? [0-9]+ stops";
	private final PathsByNodesCounterAlgorithm algorithm;

	public QueryAdapterPathsByNodesCounter() {
		this(new PathsByNodesCounterAlgorithm());
	}

	public QueryAdapterPathsByNodesCounter(PathsByNodesCounterAlgorithm algorithm) {
		this.algorithm = algorithm;
	}

	@Override
	public boolean isMatch(String line) {
		return Pattern.matches(lineRegex, line);
	}

	@Override
	public String execute(Graph graph, String line) {
		String[] args = getParams(line);
		int result = execute(graph, args);
		return result + "";
	}

	private int execute(Graph graph, String[] args) {
		String start = args[0];
		String end = args[1];
		int min = Integer.parseInt(args[2]);
		int max = Integer.parseInt(args[3]);
		return algorithm.getNumberOfPaths(graph, start, end, min, max);
	}

	private String[] getParams(String line) {
		String[] parts = line.split(" ");
		String number = parts[parts.length-2];
		String min = null, max=null;
		if (line.contains("exactly")) {
			min = max = number;
		}else if (line.contains("maximum")) {
			min = "1";
			max = number;
		}
		String[] result = {parts[6], parts[10], min, max};
		return result;
	}

}
