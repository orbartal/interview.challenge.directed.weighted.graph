package interview.challenge.directed.weighted.graph.e2e;

import java.util.LinkedHashMap;

public class DataProviderFromString implements DataProvider {

	private String graph = null;
	private LinkedHashMap<String, String> answerByQuery;

	public DataProviderFromString(String input) {
		String[] lines = input.split(System.getProperty("line.separator"));
		graph = lines[0];
		answerByQuery = new LinkedHashMap<String, String>();
		for (int i = 1; i < lines.length; i++) {
			String[] parts = lines[i].split("=");
			answerByQuery.put(parts[0].trim(), parts[1].trim());
		}
	}

	@Override
	public String getGraphAsString() {
		return graph;
	}

	@Override
	public LinkedHashMap<String, String> getMapAnswerByQuery() {
		return answerByQuery;
	}

}
