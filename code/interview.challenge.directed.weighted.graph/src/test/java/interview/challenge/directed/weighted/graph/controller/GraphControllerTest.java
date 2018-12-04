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
	private ArgumentCaptor<Integer> graphIdCaptor;
	@Captor
	private ArgumentCaptor<String> queriesCaptor;
	@Captor
	private ArgumentCaptor<String> writeCaptor;

	@Test
	void testStartWithoutAnyQueries() {
		// setup
		String graph = "Graph: AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";
		Mockito.when(graphUI.readGraphAsString()).thenReturn(graph);
		Mockito.when(graphUI.readCommand()).thenReturn(EXIT);
		Mockito.when(graphApi.createGraph(graph)).thenReturn(GRAPH_ID);
		// execute
		fixture.start();
		// verify
		Mockito.verify(graphUI).connect();
		Mockito.verify(graphUI).writeRequestForGraph();
		Mockito.verify(graphUI).readGraphAsString();
		Mockito.verify(graphApi).createGraph(graph);
		Mockito.verify(graphUI).writeRequestForCommand();
		Mockito.verify(graphUI).readCommand();
		Mockito.verify(graphUI).close();
	}

	@Test
	void testStartWith3Queries() {
		// setup
		String graph = "Graph: AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";
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

		Mockito.when(graphUI.readGraphAsString()).thenReturn(graph);
		Mockito.when(graphApi.createGraph(graph)).thenReturn(GRAPH_ID);
		Mockito.when(graphUI.readCommand()).thenReturn(q[0]).thenReturn(q[1]).thenReturn(q[2]).thenReturn(EXIT);
		Mockito.when(graphApi.query(Mockito.eq(GRAPH_ID), Mockito.anyString())).then(answer);

		// execute
		fixture.start();

		// verify
		Mockito.verify(graphUI).connect();
		Mockito.verify(graphUI).writeRequestForGraph();
		Mockito.verify(graphUI).readGraphAsString();
		Mockito.verify(graphApi).createGraph(graph);
		Mockito.verify(graphUI, Mockito.times(4)).writeRequestForCommand();
		Mockito.verify(graphUI, Mockito.times(4)).readCommand();
		Mockito.verify(graphApi, Mockito.times(3)).query(graphIdCaptor.capture(), queriesCaptor.capture());
		Mockito.verify(graphUI, Mockito.times(3)).writeResponseToCommand(writeCaptor.capture());
		Mockito.verify(graphUI).close();

		List<String> writeMessags = writeCaptor.getAllValues();
		List<String> queriesMessags = queriesCaptor.getAllValues();
		List<Integer> graphIds = graphIdCaptor.getAllValues();

		Assertions.assertArrayEquals(a, writeMessags.toArray());
		Assertions.assertArrayEquals(q, queriesMessags.toArray());
		Long[] ids = { 1L, 1L, 1L };
		Assertions.assertArrayEquals(ids, graphIds.toArray());
	}

}
