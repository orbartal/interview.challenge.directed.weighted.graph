package interview.challenge.directed.weighted.graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ApiGraphQueriesTest {

	private ApiGraphQueries fixture;
	
	@BeforeEach
	void setup() {
		fixture = new ApiGraphQueries();
	}

	@Test
	void testSingleSuccessTest() {
		String actual = fixture.query("Some invalid query");
		Assertions.assertEquals("Invalid query", actual);
	}

}
