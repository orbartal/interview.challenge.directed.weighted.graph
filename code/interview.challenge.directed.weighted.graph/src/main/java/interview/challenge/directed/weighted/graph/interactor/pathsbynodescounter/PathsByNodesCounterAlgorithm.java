package interview.challenge.directed.weighted.graph.interactor.pathsbynodescounter;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import interview.challenge.directed.weighted.graph.model.Edge;
import interview.challenge.directed.weighted.graph.model.Graph;
import interview.challenge.directed.weighted.graph.model.Node;

public class PathsByNodesCounterAlgorithm {
	public int getNumberOfPaths(Graph graph, String start, String end, int min, int max) {
		List<Node> nodes = graph.getNodes();
		Map<String, Integer> nodeNameToIndex = getIndexByNameMap(nodes);
		int[][] pathsCounter = new int[nodes.size()][max + 2];
		int startIndex = nodeNameToIndex.get(start);
		pathsCounter[startIndex][0] = 1; // The distance from each node to itself is zero with empty path.
		for (int d = 0; d <= max; d++) {
			for (int n = 0; n < nodes.size(); n++) {
				Node node = nodes.get(n);
				Map<String, Edge> edgesByTargetName = graph.getEdgesBySourceName(node.getName());
				for (Entry<String, Edge> pair : edgesByTargetName.entrySet()) {
					String targetName = pair.getKey();
					int targetIndex = nodeNameToIndex.get(targetName);
					pathsCounter[targetIndex][d + 1] += pathsCounter[n][d];
				}
			}
		}
		int endIndex = nodeNameToIndex.get(end);
		int sum = 0;
		for (int i = min; i <= max; i++) {
			sum = sum + pathsCounter[endIndex][i];
		}
		return sum;
	}

	private Map<String, Integer> getIndexByNameMap(List<Node> nodes) {
		return IntStream.range(0, nodes.size()).boxed()
				.collect(Collectors.toMap(i -> nodes.get(i).getName(), Function.identity()));
	}

}
