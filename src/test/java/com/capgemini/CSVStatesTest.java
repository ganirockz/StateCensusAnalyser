package com.capgemini;

import org.junit.*;

public class CSVStatesTest {
	@Test
	public void givenStateCensusCSVCheckToEnsureTheNumberOfRecords() {
		String censusFile = "./stateCode.csv";
		StateCensusAnalyser stateCodeAnalyser = new StateCensusAnalyser();
		int numOfEntries = -1;
		try {
			numOfEntries = stateCodeAnalyser.loadStateCodeCSV(censusFile);
		} catch (IncorrectCSVFileException e) {
			e.printStackTrace();
		}
		Assert.assertEquals(37, numOfEntries);
	}
}
