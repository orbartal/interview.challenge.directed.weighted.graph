package interview.challenge.directed.weighted.graph.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import interview.challenge.directed.weighted.graph.model.Edge;
import interview.challenge.directed.weighted.graph.model.Node;

public class EdgeTest {

	@Test
	void testCreateEdgeAB5() {
		// setup
		Node source = new Node("A");
		Node target = new Node("B");
		Integer weight = 5;
		// execute
		Edge actual = new Edge(source, target, weight);
		// verify
		Assertions.assertEquals(weight, actual.getWeight());
		Assertions.assertEquals(source, actual.getSource());
		Assertions.assertEquals(target, actual.getTarget());
	}

	@Test
	void testCreateEdgeCD8() {
		// setup
		Node source = new Node("C");
		Node target = new Node("D");
		Integer weight = 8;
		// execute
		Edge actual = new Edge(source, target, weight);
		// verify
		Assertions.assertEquals(weight, actual.getWeight());
		Assertions.assertEquals(source, actual.getSource());
		Assertions.assertEquals(target, actual.getTarget());
	}

	@Test
	void testEdgeEquals() {
		Edge edge1 = new Edge(new Node("A"), new Node("B"), 6);
		Edge edge2 = new Edge(new Node("A"), new Node("B"), 6);
		Assertions.assertEquals(edge1, edge2);
	}

	@Test
	void testEdgeNotEqualsSource() {
		Edge edge1 = new Edge(new Node("A"), new Node("B"), 6);
		Edge edge2 = new Edge(new Node("A2"), new Node("B"), 6);
		Assertions.assertNotEquals(edge1, edge2);
	}

	@Test
	void testEdgeNotEqualsTarget() {
		Edge edge1 = new Edge(new Node("A"), new Node("B"), 6);
		Edge edge2 = new Edge(new Node("A"), new Node("B2"), 6);
		Assertions.assertNotEquals(edge1, edge2);
	}

	@Test
	void testEdgeHashCodeEquals() {
		Edge edge1 = new Edge(new Node("A"), new Node("B"), 6);
		Edge edge2 = new Edge(new Node("A"), new Node("B"), 6);
		Assertions.assertEquals(edge1.hashCode(), edge2.hashCode());
	}

	@Test
	void testEdgeHashCodeNotEqualsSource() {
		Edge edge1 = new Edge(new Node("A"), new Node("B"), 6);
		Edge edge2 = new Edge(new Node("A2"), new Node("B"), 6);
		Assertions.assertNotEquals(edge1.hashCode(), edge2.hashCode());
	}

	@Test
	void testEdgeHashCodeNotEqualsTarget() {
		Edge edge1 = new Edge(new Node("A"), new Node("B"), 6);
		Edge edge2 = new Edge(new Node("A"), new Node("B2"), 6);
		Assertions.assertNotEquals(edge1.hashCode(), edge2.hashCode());
	}

}
