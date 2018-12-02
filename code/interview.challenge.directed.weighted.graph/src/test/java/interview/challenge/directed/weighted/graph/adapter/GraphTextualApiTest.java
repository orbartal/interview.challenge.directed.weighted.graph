package interview.challenge.directed.weighted.graph.adapter;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import interview.challenge.directed.weighted.graph.model.Graph;

@ExtendWith(MockitoExtension.class)
public class GraphTextualApiTest {

	static final long GRAPH_ID = 1L;

	@Mock
	private GraphParser graphParser;
	@Mock
	private GraphStore graphStore;
	@Mock
	private QueryAdapterFactory queryAdapterFactory;

	@Mock
	private Graph graph;
	@Mock
	private QueryAdapter queryAdapter;

	@InjectMocks
	private GraphTextualApi fixture;

	@Test
	void testCreateGraphForValidInput() {
		// setup
		String input = "Graph: AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";
		Mockito.when(graphParser.apply(input)).thenReturn(graph);
		Mockito.when(graphStore.save(graph)).thenReturn(GRAPH_ID);
		// execute
		long actual = fixture.createGraph(input);
		// verify
		Assertions.assertEquals(GRAPH_ID, actual);
		Mockito.verify(graphParser).apply(input);
		Mockito.verify(graphStore).save(graph);
	}

	@Test
	void testCreateGraphForInvalidInput() {
		// setup
		String message = "invalid input";
		String input = "Graph: AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";
		Mockito.when(graphParser.apply(input)).thenThrow(new RuntimeException(message));
		try {
			// execute
			fixture.createGraph(input);
			Assertions.fail();
			// verify
		} catch (RuntimeException e) {
			Assertions.assertEquals(message, e.getMessage());
		}
	}

	@Test
	void testQueryWithValidParams() {
		// setup
		String input = "The distance of the route A-B-C".toLowerCase();
		Optional<QueryAdapter> pquery = Optional.of(queryAdapter);
		Optional<Graph> pgraph = Optional.of(graph);
		String ouput = "9";

		Mockito.when(queryAdapterFactory.getQuery(input)).thenReturn(pquery);
		Mockito.when(graphStore.get(GRAPH_ID)).thenReturn(pgraph);
		Mockito.when(queryAdapter.execute(graph, input)).thenReturn(ouput);

		// execute
		String actual = fixture.query(GRAPH_ID, input);
		// verify
		Assertions.assertEquals(ouput, actual);
		Mockito.verify(queryAdapterFactory).getQuery(input);
		Mockito.verify(graphStore).get(GRAPH_ID);
		Mockito.verify(queryAdapter).execute(graph, input);
	}

	@Test
	void testQueryWithInValidQueryLine() {
		// setup
		String input = "Invalid query".toLowerCase();
		Optional<QueryAdapter> pquery = Optional.empty(); // This cause the error.
		Mockito.when(queryAdapterFactory.getQuery(input)).thenReturn(pquery);
		try {
			// execute
			fixture.query(GRAPH_ID, input);
			Assertions.fail();
			// verify
		} catch (RuntimeException e) {
			Assertions.assertEquals("Invalid input query: " + input, e.getMessage());
		}
	}

	@Test
	void testQueryWithInValidGraphId() {
		// setup
		String input = "The distance of the route A-B-C".toLowerCase();
		Optional<QueryAdapter> pquery = Optional.of(queryAdapter);
		Optional<Graph> pgraph = Optional.empty(); // This cause the error.

		Mockito.when(queryAdapterFactory.getQuery(input)).thenReturn(pquery);
		Mockito.when(graphStore.get(GRAPH_ID)).thenReturn(pgraph);

		try {
			// execute
			fixture.query(GRAPH_ID, input);
			Assertions.fail();
			// verify
		} catch (RuntimeException e) {
			Assertions.assertEquals("No graph found with grpah id: " + GRAPH_ID, e.getMessage());
		}
	}

}
