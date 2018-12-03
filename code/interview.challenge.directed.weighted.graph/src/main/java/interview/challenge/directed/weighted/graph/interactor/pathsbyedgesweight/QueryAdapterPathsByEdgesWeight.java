package interview.challenge.directed.weighted.graph.interactor.pathsbyedgesweight;

import java.util.regex.Pattern;

import interview.challenge.directed.weighted.graph.adapter.QueryAdapter;
import interview.challenge.directed.weighted.graph.model.Graph;

public class QueryAdapterPathsByEdgesWeight implements QueryAdapter {

	static private final String lineRegex = "the number of different routes from [a-z] to [a-z] with a distance of less than [0-9]+";
	private final PathsByEdgesWeightAlgorithm algorithm;

	public QueryAdapterPathsByEdgesWeight() {
		this(new PathsByEdgesWeightAlgorithm());
	}

	public QueryAdapterPathsByEdgesWeight(PathsByEdgesWeightAlgorithm algorithm) {
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
		int max = Integer.parseInt(args[3])-1;//Including max
		return algorithm.getNumberOfPaths(graph, start, end, min, max);
	}

	private String[] getParams(String line) {
		String[] parts = line.split(" ");
		String number = parts[parts.length - 1];
		String min = "1", max = number;
		String[] result = { parts[6], parts[8], min, max };
		return result;
	}

}
