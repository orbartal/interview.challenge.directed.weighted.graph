package interview.challenge.directed.weighted.graph.external;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GraphConsoleUITest {
	private final InputStream systemIn = System.in;
	private final PrintStream systemOut = System.out;

	private ByteArrayInputStream testIn;
	private ByteArrayOutputStream testOut;

	private GraphConsoleUI fixture;

	@BeforeEach
	public void setUpOutput() {
		testOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(testOut));
	}

	private void provideInput(String data) {
		testIn = new ByteArrayInputStream(data.getBytes());
		System.setIn(testIn);
	}

	private String getOutput() {
		return testOut.toString();
	}

	@AfterEach
	public void restoreSystemInputOutput() {
		System.setIn(systemIn);
		System.setOut(systemOut);
	}
	
	@Test
	public void testWriteRequestForGraph() {
		final String expected = "Insert graph:";
		fixture = new GraphConsoleUI();
		fixture.connect();
		fixture.writeRequestForGraph();
		fixture.close();
		String actual = getOutput();
		Assertions.assertEquals(expected + "\n", actual);
	}
	
	@Test
	public void testWriteRequestForCommand() {
		final String expected = "Insert query:";
		fixture = new GraphConsoleUI();
		fixture.connect();
		fixture.writeRequestForCommand();
		fixture.close();
		String actual = getOutput();
		Assertions.assertEquals(expected + "\n", actual);
	}
	
	@Test
	public void testWriteResponseToCommand() {
		final String answer = "9";
		final String expected = "Answer: " + answer;
		fixture = new GraphConsoleUI();
		fixture.connect();
		fixture.writeResponseToCommand(answer);
		fixture.close();
		String actual = getOutput();
		Assertions.assertEquals(expected + "\n", actual);
	}
	
	@Test
	public void testReadGraphAsString() {
		final String expected = "Graph: AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";
		provideInput(expected);
		fixture = new GraphConsoleUI();
		fixture.connect();
		String actual = fixture.readGraphAsString();
		fixture.close();
		Assertions.assertEquals(expected, actual);
	}
	
	@Test
	public void testReadCommand() {
		final String expected = "the distance of the route A-B-C";
		provideInput(expected);
		fixture = new GraphConsoleUI();
		fixture.connect();
		String actual = fixture.readCommand();
		fixture.close();
		Assertions.assertEquals(expected, actual);
	}

}
