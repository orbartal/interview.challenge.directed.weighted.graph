package interview.challenge.directed.weighted.graph.interactor.pathsbyedgesweight;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import interview.challenge.directed.weighted.graph.model.Graph;

@ExtendWith(MockitoExtension.class)
public class QueryAdapterPathsByEdgesWeightTest {

	@Mock
	private Graph graph;

	@Mock
	private PathsByEdgesWeightAlgorithm algorithm;

	@InjectMocks
	private QueryAdapterPathsByEdgesWeight fixture;

	@Test
	void testIsMatchValidInputMaximum() {
		String input = "The number of different routes from C to C with a distance of less than 30".toLowerCase();
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
		String line = "The number of different routes from C to C with a distance of less than 30".toLowerCase();
		Integer expected = 7;
		Mockito.when(algorithm.getNumberOfPaths(graph, "c", "c", 1, 29)).thenReturn(expected);
		// execute
		String actual = fixture.execute(graph, line);
		// verify
		Assertions.assertEquals(expected + "", actual);
	}

}
