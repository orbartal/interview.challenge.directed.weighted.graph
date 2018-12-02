package interview.challenge.directed.weighted.graph.model;

import java.util.Objects;

public final class Edge {
	final private Node source;
	final private Node target;
	final private Integer weight;

	public Edge(Node source, Node target, Integer weight) {
		this.source = source;
		this.target = target;
		this.weight = weight;
	}

	public Integer getWeight() {
		return weight;
	}

	public Node getSource() {
		return source;
	}

	public Node getTarget() {
		return target;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Edge)) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		Edge other = (Edge) obj;
		return Objects.equals(this.source, other.source) && Objects.equals(this.target, other.target);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.source, this.target);
	}

	@Override
	public String toString() {
		return this.source.getName() + this.target.getName() + this.weight;
	}

}
