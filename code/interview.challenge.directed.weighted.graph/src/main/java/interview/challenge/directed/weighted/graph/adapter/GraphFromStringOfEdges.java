package interview.challenge.directed.weighted.graph.adapter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import interview.challenge.directed.weighted.graph.model.Edge;
import interview.challenge.directed.weighted.graph.model.Graph;
import interview.challenge.directed.weighted.graph.model.Node;

public class GraphFromStringOfEdges implements GraphParser {

	@Override
	public Graph apply(String input) {
		try {
			return parse(input);
		}catch(Exception e) {
			throw new RuntimeException("Invalid graph string: " + input);
		}
	}
	
	public Graph parse(String input) {
		String[] args = input.split(":"); //Assume Graph: edges
		input = args[1].toLowerCase().trim();
		String[] parts = input.split(", ");
		List<Node> nodes = getNodes(parts);
		List<Edge> edges = getEdgs(nodes, parts);
		return new Graph(nodes, edges);
	}

	private List<Node> getNodes(String[] edges) {
		Set<String> names = new HashSet<String>();
		for (String edge : edges) {
			names.add(edge.substring(0, 1));
			names.add(edge.substring(1, 2));
		}
		List<Node> nodes = new ArrayList<Node>();
		for (String name : names) {
			nodes.add(new Node(name));
		}
		return nodes;
	}

	private List<Edge> getEdgs(List<Node> nodes, String[] parts) {
		Map<String, Node> nodesByName = nodes.stream().collect(Collectors.toMap(x -> x.getName(), x -> x));
		List<Edge> edges = new ArrayList<Edge>();
		for (String part : parts) {
			Node source = nodesByName.get(part.substring(0, 1));
			Node target = nodesByName.get(part.substring(1, 2));
			Integer weight = Integer.parseInt(part.substring(2, 3));
			Edge edge = new Edge(source, target, weight);
			edges.add(edge);
		}
		return edges;
	}

}
