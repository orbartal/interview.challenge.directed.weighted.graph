package interview.challenge.directed.weighted.graph.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class Graph {
	final private List<Node> nodes;
	final private List<Edge> edges;
	final private Map<String, Map<String, Edge>> edgesByNodes;
	
	public Graph(List<Node> nodes, List<Edge> edges) {
		super();
		this.nodes = nodes;
		this.edges = edges;
		this.edgesByNodes = getEdgesByNodes();
	}
	
	private Map<String, Map<String, Edge>> getEdgesByNodes() {
		Map<String, Map<String, Edge>> edgesByNode = new HashMap<String, Map<String, Edge>>();
		for (Edge edge : edges) {
			String source = edge.getSource().getName();
			String target = edge.getTarget().getName();
			Map<String, Edge> edgesBySourceNode = edgesByNode.get(source);
			if (edgesBySourceNode==null) {
				edgesBySourceNode = new HashMap<String, Edge>();
				edgesByNode.put(source, edgesBySourceNode);
			}
			edgesBySourceNode.put(target, edge);
		}
		return edgesByNode;
	}

	public List<Node> getNodes() {
		return Collections.unmodifiableList(nodes);
	}

	public List<Edge> getEdges() {
		return Collections.unmodifiableList(edges);
	}

	public Optional<Edge> getEdgeByNodesNames(String source, String target) {
		Map<String, Edge> edgesBySourceNode = edgesByNodes.get(source);
		if (edgesBySourceNode==null) {
			return Optional.empty();
		}
		Edge edge = edgesBySourceNode.get(target);
		if (edge==null) {
			return Optional.empty();
		}
		return Optional.of(edge);
	}
	
	public Map<String, Edge> getEdgesBySourceName(String source) {
		Map<String, Edge> edgesBySourceNode = edgesByNodes.get(source);
		if (edgesBySourceNode==null) {
			return new HashMap<String, Edge>();
		}
		return edgesBySourceNode;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Graph)) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		Graph other = (Graph) obj;
		return Objects.equals(this.nodes, other.nodes) && Objects.equals(this.edges, other.edges);

	}

	@Override
	public int hashCode() {
		//Currently both nodes and edges are immutable 
		return Objects.hash(this.nodes, this.edges);
	}
	
	@Override
	public String toString() {
		return edges.toString();
	}
	
}
