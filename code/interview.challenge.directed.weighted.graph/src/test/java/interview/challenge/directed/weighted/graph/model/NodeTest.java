package interview.challenge.directed.weighted.graph.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import interview.challenge.directed.weighted.graph.model.Node;

public class NodeTest {
	
	@Test
	void testCreateNodA() {
		String name = "A";
		Node node = new Node(name);
		Assertions.assertEquals(name.toLowerCase(), node.getName());
	}
	
	@Test
	void testCreateNodB() {
		String name = "B";
		Node node = new Node(name);
		Assertions.assertEquals(name.toLowerCase(), node.getName());
	}
	
	@Test
	void testNodeEquals() {
		String name = "A";
		Node node1 = new Node(name);
		Node node2 = new Node(name);
		Assertions.assertEquals(node1, node2);
	}
	
	@Test
	void testNodeNotEquals() {
		Node node1 = new Node("A");
		Node node2 = new Node("B");
		Assertions.assertNotEquals(node1, node2);
	}
	
	@Test
	void testNodeHashCodeEquals() {
		String name = "A";
		Node node1 = new Node(name);
		Node node2 = new Node(name);
		Assertions.assertEquals(node1.hashCode(), node2.hashCode());
	}
	
	@Test
	void testNodeHashCodeNotEquals() {
		Node node1 = new Node("A");
		Node node2 = new Node("B");
		Assertions.assertNotEquals(node1.hashCode(), node2.hashCode());
	}
	
}
