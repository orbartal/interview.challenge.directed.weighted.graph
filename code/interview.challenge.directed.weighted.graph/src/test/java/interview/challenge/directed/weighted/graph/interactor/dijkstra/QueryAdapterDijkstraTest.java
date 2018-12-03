package interview.challenge.directed.weighted.graph.interactor.dijkstra;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import interview.challenge.directed.weighted.graph.model.Graph;

@ExtendWith(MockitoExtension.class)
public class QueryAdapterDijkstraTest {

	@Mock
	private Graph graph;

	@Mock
	private DijkstraAlgorithm algorithm;

	@InjectMocks
	private QueryAdapterDijkstra fixture;

	@Test
	void testIsMatchValidInput1() {
		String input = "The length of the shortest route (in terms of distance to travel) from A to C".toLowerCase();
		boolean actual = fixture.isMatch(input);
		Assertions.assertTrue(actual);
	}

	@Test
	void testIsMatchValidInput2() {
		String input = "The length of the shortest route (in terms of distance to travel) from B to B".toLowerCase();
		boolean actual = fixture.isMatch(input);
		Assertions.assertTrue(actual);
	}

	@Test
	void testIsMatchInValidInput() {
		String input = "The distance of the route A-E-D".toLowerCase();
		boolean actual = fixture.isMatch(input);
		Assertions.assertFalse(actual);
	}

	@Test
	void testExecuteValid1() {
		// setup
		String line = "The length of the shortest route (in terms of distance to travel) from A to C".toLowerCase()
				.trim();
		String start = "a";
		String end = "c";
		Integer expected = 9;
		Mockito.when(algorithm.getLengthOfShortestPath(graph, start, end)).thenReturn(expected);
		// execute
		String actual = fixture.execute(graph, line);
		// verify
		Assertions.assertEquals(expected + "", actual);
	}

	@Test
	void testExecuteValid2() {
		// setup
		String line = "The length of the shortest route (in terms of distance to travel) from B to B".toLowerCase();
		String start = "b";
		String end = "b";
		Integer expected = 9;
		Mockito.when(algorithm.getLengthOfShortestPath(graph, start, end)).thenReturn(expected);
		// execute
		String actual = fixture.execute(graph, line);
		// verify
		Assertions.assertEquals(expected + "", actual);
	}

	@Test
	void testExecuteInValid() {
		// setup
		String line = "The length of the shortest route (in terms of distance to travel) from B to A".toLowerCase();
		String start = "b";
		String end = "a";
		String expected = "NO SUCH ROUTE";
		Mockito.when(algorithm.getLengthOfShortestPath(graph, start, end))
				.thenThrow(new RuntimeException("No path was found"));
		// execute
		String actual = fixture.execute(graph, line);
		// verify
		Assertions.assertEquals(expected, actual);
	}

}
