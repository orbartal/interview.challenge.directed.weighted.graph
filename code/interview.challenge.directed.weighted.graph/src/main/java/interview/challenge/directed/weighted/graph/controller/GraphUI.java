package interview.challenge.directed.weighted.graph.controller;

public interface GraphUI {

	public void write(String string);

	public String read();
	
	/////
	public void connect();
	
	public void writeRequestForGraph();

	public String readGraphAsString();

	public void writeRequestForCommand();

	public String readCommand();

	public void writeResponseToCommand(String answer);

	public void close();

}
