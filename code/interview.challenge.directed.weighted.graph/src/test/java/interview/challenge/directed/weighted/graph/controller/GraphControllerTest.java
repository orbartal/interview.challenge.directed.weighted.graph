package interview.challenge.directed.weighted.graph.controller;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import interview.challenge.directed.weighted.graph.adapter.GraphTextualApi;

@ExtendWith(MockitoExtension.class)
public class GraphControllerTest {

	static final long GRAPH_ID = 1L;
	static final String EXIT = GraphController.EXIT;

	@Mock
	private GraphUI graphUI;
	@Mock
	private GraphTextualApi graphApi;

	@InjectMocks
	private GraphController fixture;

	@Captor
	ArgumentCaptor<Integer> graphIdCaptor;
	@Captor
	ArgumentCaptor<String> queriesCaptor;
	@Captor
	ArgumentCaptor<String> writeCaptor;

	@Test
	void testStartWithoutAnyQueries() {
		// setup
		String graph = "Graph: AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";
		Mockito.when(graphUI.read()).thenReturn(graph).thenReturn(EXIT);
		Mockito.when(graphApi.createGraph(graph)).thenReturn(GRAPH_ID);
		// execute
		fixture.start();
		// verify
		Mockito.verify(graphUI).write("Insert graph:");
		Mockito.verify(graphUI).write("Insert query:");
		Mockito.verify(graphUI, Mockito.times(2)).read();
		Mockito.verify(graphApi).createGraph(graph);
	}

	@Test
	void testStartWith3Queries() {
		// setup
		final String graph = "Graph: AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";
		final String[] q = { "q1", "q2", "q3" };
		final String[] a = { "a1", "a2", "a3" };
		Answer<String> answer = new Answer<String>() {
			public String answer(InvocationOnMock invocation) throws Throwable {
				String input = (String) invocation.getArguments()[1];
				for (int i = 0; i < q.length; i++) {
					if (q[i].equals(input)) {
						return a[i];
					}
				}
				return "Invalid input";
			}
		};
		Mockito.when(graphUI.read()).thenReturn(graph).thenReturn(q[0]).thenReturn(q[1]).thenReturn(q[2])
				.thenReturn(EXIT);
		Mockito.when(graphApi.createGraph(graph)).thenReturn(GRAPH_ID);
		Mockito.when(graphApi.query(Mockito.eq(GRAPH_ID), Mockito.anyString())).then(answer);
		// execute
		fixture.start();
		// verify
		Mockito.verify(graphUI).write("Insert graph:");
		Mockito.verify(graphUI, Mockito.times(8)).write(writeCaptor.capture());
		Mockito.verify(graphUI, Mockito.times(5)).read();
		Mockito.verify(graphApi).createGraph(graph);
		Mockito.verify(graphApi, Mockito.times(3)).query(graphIdCaptor.capture(), queriesCaptor.capture());

		List<String> writeMessags = writeCaptor.getAllValues();
		List<String> queriesMessags = queriesCaptor.getAllValues();
		List<Integer> graphIds = graphIdCaptor.getAllValues();

		String[] expectedWrites = { "Insert graph:", "Insert query:", "Answer: a1", "Insert query:", "Answer: a2",
				"Insert query:", "Answer: a3", "Insert query:" };
		Assertions.assertArrayEquals(expectedWrites, writeMessags.toArray());
		Assertions.assertArrayEquals(q, queriesMessags.toArray());
		Long[] ids = { 1L, 1L, 1L };
		Assertions.assertArrayEquals(ids, graphIds.toArray());
	}

}
