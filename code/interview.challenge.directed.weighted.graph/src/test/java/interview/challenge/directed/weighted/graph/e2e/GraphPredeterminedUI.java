package interview.challenge.directed.weighted.graph.e2e;

import java.util.LinkedHashMap;
import java.util.List;

import interview.challenge.directed.weighted.graph.controller.GraphController;
import interview.challenge.directed.weighted.graph.controller.GraphUI;

public class GraphPredeterminedUI implements GraphUI {

	final private String GraphInput;
	final private List<String> commands;
	final private LinkedHashMap<String, String> outputs;

	public GraphPredeterminedUI(String graphInput, List<String> commands) {
		super();
		this.GraphInput = graphInput;
		this.commands = commands;
		this.outputs = new LinkedHashMap<>();
	}

	@Override
	public void connect() {
	}

	@Override
	public void writeRequestForGraph() {
	}

	@Override
	public String readGraphAsString() {
		return this.GraphInput;
	}

	@Override
	public void writeRequestForCommand() {
	}

	@Override
	public String readCommand() {
		int commandIndex = this.outputs.size();
		if (commandIndex >= commands.size()) {
			return GraphController.EXIT;
		}
		return this.commands.get(commandIndex);
	}

	@Override
	public void writeResponseToCommand(String answer) {
		int commandIndex = this.outputs.size();
		String command = this.commands.get(commandIndex);
		this.outputs.put(command, answer);
	}

	@Override
	public void close() {
	}

	public LinkedHashMap<String, String> getResults() {
		return outputs;
	}

}
