package interview.challenge.directed.weighted.graph.e2e;

import java.util.LinkedHashMap;

public class PredefinedDataProvider implements DataProvider {
	
	public String getGraphAsString() {
		return "Graph: AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";
	}
	
	public LinkedHashMap<String, String> getMapAnswerByQuery() {
		String[][] qa = new String[][] {
			  { "The distance of the route A-B-C", "9" }, 
			  { "The distance of the route A-D", "5" }, 
			  { "The distance of the route A-D-C", "13" }, 
			  { "The distance of the route A-E-B-C-D", "22" }, 
			  {	"The distance of the route A-E-D", "NO SUCH ROUTE" }, 
			  { "The number of trips starting at C and ending at C with a maximum of 3 stops", "2" }, 
			  { "The number of trips starting at A and ending at C with exactly 4 stops", "3" }, 
			  { "The length of the shortest route (in terms of distance to travel) from A to C", "9" }, 
			  { "The length of the shortest route (in terms of distance to travel) from B to B", "9" }, 
			  { "The number of different routes from C to C with a distance of less than 30", "7" }
			};
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		for (int i = 0; i < qa.length; i++) {
			map.put(qa[i][0], qa[i][1]);
		}
		return map;
	}

}
