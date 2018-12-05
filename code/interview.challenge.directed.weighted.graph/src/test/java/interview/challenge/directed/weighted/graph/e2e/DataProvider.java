package interview.challenge.directed.weighted.graph.e2e;

import java.util.LinkedHashMap;

public interface DataProvider {
	public String getGraphAsString();

	public LinkedHashMap<String, String> getMapAnswerByQuery();
}
