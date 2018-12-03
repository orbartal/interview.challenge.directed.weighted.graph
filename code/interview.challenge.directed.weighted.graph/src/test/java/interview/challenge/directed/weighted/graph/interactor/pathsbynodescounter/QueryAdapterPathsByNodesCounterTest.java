package interview.challenge.directed.weighted.graph.interactor.pathsbynodescounter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import interview.challenge.directed.weighted.graph.model.Graph;

@ExtendWith(MockitoExtension.class)
public class QueryAdapterPathsByNodesCounterTest {

	@Mock
	private Graph graph;

	@Mock
	private PathsByNodesCounterAlgorithm algorithm;

	@InjectMocks
	private QueryAdapterPathsByNodesCounter fixture;

	@Test
	void testIsMatchValidInputMaximum() {
		String input = "The number of trips starting at C and ending at C with a maximum of 3 stops".toLowerCase();
		boolean actual = fixture.isMatch(input);
		Assertions.assertTrue(actual);
	}

	@Test
	void testIsMatchValidInputExactly() {
		String input = "The number of trips starting at A and ending at C with exactly 4 stops".toLowerCase();
		boolean actual = fixture.isMatch(input);
		Assertions.assertTrue(actual);
	}

	@Test
	void testIsMatchInValidInput() {
		String input = "The distance of the route A-E-B-C-D".toLowerCase();
		boolean actual = fixture.isMatch(input);
		Assertions.assertFalse(actual);
	}

	@Test
	void testExecuteValidMaximum() {
		// setup
		String line = "The number of trips starting at C and ending at C with a maximum of 3 stops".toLowerCase();
		Integer expected = 2;
		Mockito.when(algorithm.getNumberOfPaths(graph, "c", "c", 1, 3)).thenReturn(expected);
		// execute
		String actual = fixture.execute(graph, line);
		// verify
		Assertions.assertEquals(expected + "", actual);
	}

	@Test
	void testExecuteExactly() {
		// setup
		String line = "The number of trips starting at A and ending at C with exactly 4 stops".toLowerCase();
		Integer expected = 3;
		Mockito.when(algorithm.getNumberOfPaths(graph, "a", "c", 4, 4)).thenReturn(expected);
		// execute
		String actual = fixture.execute(graph, line);
		// verify
		Assertions.assertEquals(expected + "", actual);
	}

}
