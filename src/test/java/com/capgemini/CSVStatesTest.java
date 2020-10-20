package com.capgemini;

import org.junit.*;

public class CSVStatesTest {
	@Test
	public void givenStateCensusCSVCheckToEnsureTheNumberOfRecords() {
		String censusFile = "./stateCode.csv";
		CSVStates stateCodeAnalyser = new CSVStates();
		int numOfEntries = stateCodeAnalyser.loadCSV(censusFile);
		Assert.assertEquals(37, numOfEntries);
	}
}
