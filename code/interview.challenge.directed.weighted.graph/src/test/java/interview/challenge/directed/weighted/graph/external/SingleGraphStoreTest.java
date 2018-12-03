package interview.challenge.directed.weighted.graph.external;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import interview.challenge.directed.weighted.graph.model.Graph;

@ExtendWith(MockitoExtension.class)
public class SingleGraphStoreTest {

	static final long GRAPH_ID = SingleGraphStore.GRAPH_ID;

	@Mock
	private Graph graph;

	private SingleGraphStore fixture;

	@BeforeEach
	void setup() {
		fixture = new SingleGraphStore();
	}

	@Test
	void testEmptyStore() {
		Assertions.assertFalse(fixture.get(GRAPH_ID).isPresent());
	}

	@Test
	void testSaveGraph() {
		long actual = fixture.save(graph);
		Assertions.assertEquals(GRAPH_ID, actual);
	}

	@Test
	void testSaveAndGetGraphWithSameId() {
		long graphId = fixture.save(graph);
		Optional<Graph> pg = fixture.get(graphId);
		Assertions.assertTrue(pg.isPresent());
		Graph graph2 = pg.get();
		Assertions.assertEquals(graph, graph2);
	}

	@Test
	void testSaveAndGetGraphWithWrongId() {
		long graphId = fixture.save(graph);
		long graphId2 = graphId + 1;
		Optional<Graph> pg = fixture.get(graphId2);
		Assertions.assertFalse(pg.isPresent());
	}

}
