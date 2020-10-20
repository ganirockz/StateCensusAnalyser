package com.capgemini;

import org.junit.*;

public class CSVStatesTest {
	@Test
	public void givenStateCensusCSVCheckToEnsureTheNumberOfRecords() {
		String censusFile = "./incorrectStateCensus.csv";
		CSVStates stateCodeAnalyser = new CSVStates();
		int numOfEntries = -1;
		try {
			numOfEntries = stateCodeAnalyser.loadCSV(censusFile);
		} catch (IncorrectCSVFile e) {
			e.printStackTrace();
		}
		Assert.assertEquals(-1, numOfEntries);
	}
}
