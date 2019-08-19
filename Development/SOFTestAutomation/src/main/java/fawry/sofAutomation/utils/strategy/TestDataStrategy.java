package fawry.sofAutomation.utils.strategy;

import java.util.ArrayList;


public interface TestDataStrategy {
	
	//public void connectToTestDataSource(String connectionProperties);
	
	public ArrayList<ArrayList<Object>> loadTestData(String connectionProperties);
	
	
}
