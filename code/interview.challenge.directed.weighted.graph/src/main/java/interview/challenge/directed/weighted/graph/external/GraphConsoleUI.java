package interview.challenge.directed.weighted.graph.external;

import java.util.Scanner;

import interview.challenge.directed.weighted.graph.controller.GraphUI;

public class GraphConsoleUI implements GraphUI {
	private Scanner scanner = null;

	private String read() {
		return scanner.nextLine();
	}

	private void write(String s) {
		System.out.print(s + "\n");
	}

	public void close() {
		if (scanner != null) {
			scanner.close();
		}
		scanner = null;
	}

	@Override
	public void connect() {
		scanner = new Scanner(System.in);
	}

	@Override
	public void writeRequestForGraph() {
		write("Insert graph:");
	}

	@Override
	public String readGraphAsString() {
		return read();
	}

	@Override
	public void writeRequestForCommand() {
		write("Insert query:");
	}

	@Override
	public String readCommand() {
		return read();
	}

	@Override
	public void writeResponseToCommand(String answer) {
		write("Answer: " + answer);
	}

}
