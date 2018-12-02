package interview.challenge.directed.weighted.graph.interactor.dijkstra;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import interview.challenge.directed.weighted.graph.model.Edge;
import interview.challenge.directed.weighted.graph.model.Graph;
import interview.challenge.directed.weighted.graph.model.Node;

public class DijkstraAlgorithmTest {

	private static final String NODE_NAME_A = "a";
	private static final String NODE_NAME_B = "b";
	private static final String NODE_NAME_C = "c";
	private static final String NODE_NAME_D = "d";
	private static final String NODE_NAME_E = "e";
	
	private Graph graph = null;
	private DijkstraAlgorithm fixture = null;

	@BeforeEach
	void setup() {
		List<Node> nodes = getNodes1();
		List<Edge> edges = getEdges1(nodes);
		graph = new Graph(nodes, edges);
		fixture = new DijkstraAlgorithm();
	}
	
	@Test
	void testGetLengthOfShortestPathFromAToC() {
		// execute
		int actual = fixture.getLengthOfShortestPath(graph, NODE_NAME_A, NODE_NAME_C);
		// verify: AB5_BC4
		Assertions.assertEquals(9, actual);
	}
	
	@Test
	void testGetLengthOfShortestPathFromBToB() {
		// execute
		int actual = fixture.getLengthOfShortestPath(graph, NODE_NAME_A, NODE_NAME_C);
		// verify: BC4_CE2_EB3
		Assertions.assertEquals(9, actual);
	}
	
	@Test
	void testGetLengthOfShortestPathFromBToA() {
		try {
			// execute
			fixture.getLengthOfShortestPath(graph, NODE_NAME_B, NODE_NAME_A);
			Assertions.fail();
			// verify
		} catch (RuntimeException e) {
			Assertions.assertEquals("No path was found", e.getMessage());
		}
	}
	
	private List<Node> getNodes1() {
		Node a = new Node(NODE_NAME_A);
		Node b = new Node(NODE_NAME_B);
		Node c = new Node(NODE_NAME_C);
		Node d = new Node(NODE_NAME_D);
		Node e = new Node(NODE_NAME_E);
		List<Node> nodes = Arrays.asList(a, b, c, d, e);
		return nodes;
	}

	private List<Edge> getEdges1(List<Node> nodes) {
		Node a = nodes.get(0);
		Node b = nodes.get(1);
		Node c = nodes.get(2);
		Node d = nodes.get(3);
		Node e = nodes.get(4);
		Edge ab5 = new Edge(a, b, 5);
		Edge bc4 = new Edge(b, c, 4);
		Edge cd8 = new Edge(c, d, 8);
		Edge dc8 = new Edge(d, c, 8);
		Edge de6 = new Edge(d, e, 6);
		Edge ad5 = new Edge(a, d, 5);
		Edge ce2 = new Edge(c, e, 2);
		Edge eb3 = new Edge(e, b, 3);
		Edge ae7 = new Edge(a, e, 7);
		List<Edge> edges = Arrays.asList(ab5, bc4, cd8, dc8, de6, ad5, ce2, eb3, ae7);
		return edges;
	}
}
