package interview.challenge.directed.weighted.graph.model;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GraphTest {

	private static final String NODE_NAME_A = "a";
	private static final String NODE_NAME_B = "b";
	private static final String NODE_NAME_C = "c";
	private static final String NODE_NAME_D = "d";
	private static final String NODE_NAME_E = "e";

	@Test
	void testCreateGraph() {
		// setup
		List<Node> nodes = getNodes1();
		List<Edge> edges = getEdges1(nodes);
		// execute
		Graph graph = new Graph(nodes, edges);
		// verify
		Assertions.assertArrayEquals(nodes.toArray(), graph.getNodes().toArray());
		Assertions.assertArrayEquals(edges.toArray(), graph.getEdges().toArray());
	}

	@Test
	void testCreateGraph2() {
		// setup
		List<Node> nodes = getNodes2();
		List<Edge> edges = getEdges2(nodes);
		// execute
		Graph graph = new Graph(nodes, edges);
		// verify
		Assertions.assertArrayEquals(nodes.toArray(), graph.getNodes().toArray());
		Assertions.assertArrayEquals(edges.toArray(), graph.getEdges().toArray());
	}
	
	@Test
	void testGetEdgeByNodesNamesWithFoundEdgeAB() {
		// setup
		List<Node> nodes = getNodes1();
		List<Edge> edges = getEdges1(nodes);
		Graph graph = new Graph(nodes, edges);
		// execute
		Optional<Edge> pedge = graph.getEdgeByNodesNames(NODE_NAME_A, NODE_NAME_B);
		// verify
		Assertions.assertTrue(pedge.isPresent());
		Edge edgeAB = pedge.get();
		Assertions.assertEquals(edgeAB.getSource().getName(), NODE_NAME_A);
		Assertions.assertEquals(edgeAB.getTarget().getName(), NODE_NAME_B);
	}
	
	@Test
	void testGetEdgeByNodesNamesWithNotFoundEdgeAC() {
		// setup
		List<Node> nodes = getNodes1();
		List<Edge> edges = getEdges1(nodes);
		Graph graph = new Graph(nodes, edges);
		// execute
		Optional<Edge> pedge = graph.getEdgeByNodesNames(NODE_NAME_A, NODE_NAME_C);
		// verify
		Assertions.assertFalse(pedge.isPresent());
	}
	
	@Test
	void testGetEdgeByNodesNamesWithFoundEdgeAC() {
		// setup
		List<Node> nodes = getNodes2();
		List<Edge> edges = getEdges2(nodes);
		Graph graph = new Graph(nodes, edges);
		// execute
		Optional<Edge> pedge = graph.getEdgeByNodesNames(NODE_NAME_A, NODE_NAME_C);
		// verify
		Assertions.assertTrue(pedge.isPresent());
		Edge edgeAC = pedge.get();
		Assertions.assertEquals(edgeAC.getSource().getName(), NODE_NAME_A);
		Assertions.assertEquals(edgeAC.getTarget().getName(), NODE_NAME_C);
	}
	
	@Test
	void testGetEdgesBySourceNameB() {
		// setup
		List<Node> nodes = getNodes1();
		List<Edge> edges = getEdges1(nodes);
		Graph graph = new Graph(nodes, edges);
		// execute
		Map<String, Edge> edgesB = graph.getEdgesBySourceName(NODE_NAME_B);
		// verify
		Assertions.assertEquals(1, edgesB.size());
		Edge edgeBC = edgesB.get(NODE_NAME_C);
		Assertions.assertNotNull(edgeBC);
		Assertions.assertEquals(NODE_NAME_B, edgeBC.getSource().getName());
		Assertions.assertEquals(new Integer(4), edgeBC.getWeight());
	}
	
	@Test
	void testGetEdgesBySourceNameA() {
		// setup
		List<Node> nodes = getNodes1();
		List<Edge> edges = getEdges1(nodes);
		Graph graph = new Graph(nodes, edges);
		// execute
		Map<String, Edge> edgesA = graph.getEdgesBySourceName(NODE_NAME_A);
		// verify
		Assertions.assertEquals(3, edgesA.size());
		Assertions.assertEquals(edgesA.get(NODE_NAME_B).getWeight(), new Integer(5));
		Assertions.assertEquals(edgesA.get(NODE_NAME_D).getWeight(), new Integer(4));
		Assertions.assertEquals(edgesA.get(NODE_NAME_E).getWeight(), new Integer(7));
		for(Edge edge : edgesA.values()) {
			Assertions.assertEquals(NODE_NAME_A, edge.getSource().getName());
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
		Edge ad5 = new Edge(a, d, 4);
		Edge ce2 = new Edge(c, e, 2);
		Edge eb3 = new Edge(e, b, 3);
		Edge ae7 = new Edge(a, e, 7);
		List<Edge> edges = Arrays.asList(ab5, bc4, cd8, dc8, de6, ad5, ce2, eb3, ae7);
		return edges;
	}
	
	private List<Node> getNodes2() {
		Node a = new Node(NODE_NAME_A);
		Node b = new Node(NODE_NAME_B);
		Node c = new Node(NODE_NAME_C);
		Node d = new Node(NODE_NAME_D);
		List<Node> nodes = Arrays.asList(c, a, d, b);
		return nodes;
	}
	
	private List<Edge> getEdges2(List<Node> nodes) {
		Node a = nodes.get(1);
		Node b = nodes.get(3);
		Node c = nodes.get(0);
		Node d = nodes.get(2);
		Edge ab7 = new Edge(a, b, 7);
		Edge ac5 = new Edge(a, c, 5);
		Edge bc4 = new Edge(b, c, 4);
		Edge cd8 = new Edge(c, d, 8);
		Edge dc9 = new Edge(d, c, 9);
		List<Edge> edges = Arrays.asList(ab7, ac5, bc4, cd8, dc9);
		return edges;
	}


}
