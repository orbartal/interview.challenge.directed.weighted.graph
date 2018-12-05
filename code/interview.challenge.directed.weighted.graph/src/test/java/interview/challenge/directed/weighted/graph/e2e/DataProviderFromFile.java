package interview.challenge.directed.weighted.graph.e2e;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.LinkedHashMap;

public class DataProviderFromFile implements DataProvider {

	private DataProviderFromString dataProvider = null;

	public DataProviderFromFile(File file) throws IOException {
		String content = new String(Files.readAllBytes(file.toPath()));
		dataProvider = new DataProviderFromString(content);
	}

	@Override
	public String getGraphAsString() {
		return dataProvider.getGraphAsString();
	}

	@Override
	public LinkedHashMap<String, String> getMapAnswerByQuery() {
		return dataProvider.getMapAnswerByQuery();
	}

}
