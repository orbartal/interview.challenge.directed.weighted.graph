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
	public void testRead() {
		final String expected = "Hello World";
		provideInput(expected);
		fixture = new GraphConsoleUI();
		String actual = fixture.read();
		Assertions.assertEquals(expected, actual);
	}

	@Test
	public void testWrite() {
		final String expected = "Hello World";
		fixture = new GraphConsoleUI();
		fixture.write(expected);
		String actual = getOutput();
		Assertions.assertEquals(expected + "\n", actual);
	}

}
