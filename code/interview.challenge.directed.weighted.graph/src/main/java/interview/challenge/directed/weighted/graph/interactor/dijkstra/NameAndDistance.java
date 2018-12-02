package interview.challenge.directed.weighted.graph.interactor.dijkstra;

import java.util.Objects;

import interview.challenge.directed.weighted.graph.model.Node;

class NameAndDistance implements Comparable<NameAndDistance> {
	private boolean hasDistance;
	private String name;
	private Integer distance;

	NameAndDistance(Node node) {
		this(node.getName());
	}

	NameAndDistance(String name) {
		this.name = name;
		this.distance = Integer.MAX_VALUE;
		this.hasDistance = false;
	}

	NameAndDistance(String name, Integer distance) {
		this.name = name;
		this.distance = distance;
		this.hasDistance = true;
	}

	public boolean getHasDistance() {
		return hasDistance;
	}

	public String getName() {
		return name;
	}

	public Integer getDistance() {
		return distance;
	}

	@Override
	public int compareTo(NameAndDistance other) {
		int result = this.distance.compareTo(other.distance);
		if (result == 0) {
			return this.name.compareTo(other.name);
		}
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof NameAndDistance)) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		NameAndDistance other = (NameAndDistance) obj;
		return Objects.equals(this.name, other.name) && Objects.equals(this.distance, other.distance);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.name, this.distance);
	}
}
