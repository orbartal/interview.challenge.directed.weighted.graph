package interview.challenge.directed.weighted.graph.adapter;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import interview.challenge.directed.weighted.graph.interactor.dijkstra.QueryAdapterDijkstra;
import interview.challenge.directed.weighted.graph.interactor.pathdistance.QueryAdapterPathDistance;
import interview.challenge.directed.weighted.graph.interactor.pathsbyedgesweight.QueryAdapterPathsByEdgesWeight;
import interview.challenge.directed.weighted.graph.interactor.pathsbynodescounter.QueryAdapterPathsByNodesCounter;

public class QueryAdapterFactoryMapperTest {

	private QueryAdapterFactoryMapper fixture;

	@BeforeEach
	void setup() {
		fixture = new QueryAdapterFactoryMapper();
	}

	@Test
	void testGetQueryPathDistance() {
		// setup
		String input = "The distance of the route A-B-C".toLowerCase();
		// execute
		Optional<QueryAdapter> actual = fixture.getQuery(input);
		// verify
		Assertions.assertTrue(actual.isPresent());
		Assertions.assertEquals(QueryAdapterPathDistance.class, actual.get().getClass());
	}

	@Test
	void testGetQueryDijkstra() {
		// setup
		String input = "The length of the shortest route (in terms of distance to travel) from A to C".toLowerCase();
		// execute
		Optional<QueryAdapter> actual = fixture.getQuery(input);
		// verify
		Assertions.assertTrue(actual.isPresent());
		Assertions.assertEquals(QueryAdapterDijkstra.class, actual.get().getClass());
	}

	@Test
	void testGetQueryPathsByNodesCounter() {
		// setup
		String input = "The number of trips starting at C and ending at C with a maximum of 3 stops".toLowerCase();
		// execute
		Optional<QueryAdapter> actual = fixture.getQuery(input);
		// verify
		Assertions.assertTrue(actual.isPresent());
		Assertions.assertEquals(QueryAdapterPathsByNodesCounter.class, actual.get().getClass());
	}

	@Test
	void testGetQueryPathsByEdgesWeight() {
		// setup
		String input = "The number of different routes from C to C with a distance of less than 30".toLowerCase();
		// execute
		Optional<QueryAdapter> actual = fixture.getQuery(input);
		// verify
		Assertions.assertTrue(actual.isPresent());
		Assertions.assertEquals(QueryAdapterPathsByEdgesWeight.class, actual.get().getClass());
	}

}
