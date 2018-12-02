package interview.challenge.directed.weighted.graph.model;

import java.util.Objects;

public final class Node {
	final private String name;

	public Node(String name) {
		this.name = name.toLowerCase().trim();
	}

	public String getName() {
		return name;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Node)) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		Node other = (Node) obj;
		return Objects.equals(this.name, other.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
	
	@Override
	public String toString() {
		return name;
	}

}
