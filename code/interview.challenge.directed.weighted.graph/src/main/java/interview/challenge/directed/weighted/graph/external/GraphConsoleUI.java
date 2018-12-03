package interview.challenge.directed.weighted.graph.external;

import java.util.Scanner;

import interview.challenge.directed.weighted.graph.controller.GraphUI;

public class GraphConsoleUI implements GraphUI{
	private Scanner scanner = null;

	public GraphConsoleUI() {
		scanner = new Scanner(System.in);
	}

	public String read() {
		return scanner.nextLine();
	}

	public void write(String s) {
		System.out.print(s + "\n");
	}

	public void close() {
		scanner.close();
	}

}
