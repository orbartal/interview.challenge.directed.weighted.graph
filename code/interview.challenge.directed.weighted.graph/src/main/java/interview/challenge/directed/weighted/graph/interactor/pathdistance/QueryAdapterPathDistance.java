package interview.challenge.directed.weighted.graph.interactor.pathdistance;

import java.util.regex.Pattern;

import interview.challenge.directed.weighted.graph.adapter.QueryAdapter;
import interview.challenge.directed.weighted.graph.model.Graph;

public class QueryAdapterPathDistance implements QueryAdapter {

	static private final String routeDistanceRegex = "the distance of the route ([a-z]\\-)*[a-z]";
	private final PathDistanceAlgorithm algorithm;

	public QueryAdapterPathDistance() {
		this(new PathDistanceAlgorithm());
	}

	public QueryAdapterPathDistance(PathDistanceAlgorithm algorithm) {
		this.algorithm = algorithm;
	}

	@Override
	public boolean isMatch(String line) {
		return Pattern.matches(routeDistanceRegex, line);
	}

	@Override
	public String execute(Graph graph, String line) {
		String[] nodes = getParamsFromRouteDistanceQuery(line);
		try {
			int result = this.algorithm.getRouteDistance(graph, nodes);
			return result + "";
		} catch (Exception e) {
			return "NO SUCH ROUTE";
		}
	}

	public String[] getParamsFromRouteDistanceQuery(String input) {
		String line = input.toLowerCase().trim();
		String args = line.substring(line.lastIndexOf(' ') + 1);
		return args.split("-");
	}

}
