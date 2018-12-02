package interview.challenge.directed.weighted.graph.adapter;

import java.util.Optional;

public interface QueryAdapterFactory {
	public Optional<QueryAdapter> getQuery(String line);
}
