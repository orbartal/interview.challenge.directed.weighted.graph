package interview.challenge.directed.weighted.graph.interactor.pathdistance;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import interview.challenge.directed.weighted.graph.model.Graph;

@ExtendWith(MockitoExtension.class)
public class QueryAdapterPathDistanceTest {

	@Mock
	private Graph graph;

	@Mock
	private PathDistanceAlgorithm algorithm;

	@InjectMocks
	private QueryAdapterPathDistance fixture;

	@Test
	void testIsMatchValidInput1() {
		String input = "The distance of the route A-B-C".toLowerCase();
		boolean actual = fixture.isMatch(input);
		Assertions.assertTrue(actual);
	}

	@Test
	void testIsMatchValidInput2() {
		String input = "The distance of the route A-E-B-C-D".toLowerCase();
		boolean actual = fixture.isMatch(input);
		Assertions.assertTrue(actual);
	}

	@Test
	void testIsMatchInValidInput() {
		String input = "The number of trips starting at C and ending at C with a maximum of 3 stops".toLowerCase();
		boolean actual = fixture.isMatch(input);
		Assertions.assertFalse(actual);
	}

	@Test
	void testExecuteValid1() {
		// setup
		String line = "The distance of the route A-E-B-C-D".toLowerCase().trim();
		String[] nodes = { "a", "e", "b", "c", "d" };
		Integer expected = 22;
		Mockito.when(algorithm.getRouteDistance(graph, nodes)).thenReturn(expected);
		// execute
		String actual = fixture.execute(graph, line);
		// verify
		Assertions.assertEquals(expected + "", actual);
	}

	@Test
	void testExecuteValid2() {
		// setup
		String line = "The distance of the route A-D-C".toLowerCase().trim();
		String[] nodes = { "a", "d", "c" };
		Integer expected = 13;
		Mockito.when(algorithm.getRouteDistance(graph, nodes)).thenReturn(expected);
		// execute
		String actual = fixture.execute(graph, line);
		// verify
		Assertions.assertEquals(expected + "", actual);
	}

	@Test
	void testExecuteInValid() {
		// setup
		String line = "The distance of the route A-E-D".toLowerCase().trim();
		String[] nodes = { "a", "e", "d" };
		String expected = "NO SUCH ROUTE";
		Mockito.when(algorithm.getRouteDistance(graph, nodes)).thenThrow(new RuntimeException("Edge not found"));
		// execute
		String actual = fixture.execute(graph, line);
		// verify
		Assertions.assertEquals(expected, actual);
	}

}
