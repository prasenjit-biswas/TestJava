package utility;

import java.util.HashMap;
import java.util.Map;

public class TestMap {
 //
	public static void main(String[] args) {
		Map<String, Long> testMap = new HashMap<String, Long>();
		testMap.put("a", 5l);
		testMap.put("a", 15l);
		System.out.println(" for a : "+testMap.get("a"));
		System.out.println(" for not a : "+testMap.get("b"));
	}
}
