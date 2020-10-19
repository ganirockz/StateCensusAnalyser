package com.capgemini;

import org.junit.*;

public class StateCensusAnalyserTest {
	@Test
	public void givenStateCensusCSVCheckToEnsureTheNumberOfRecords() {
		String censusFile = "./stateCensus.csv";
		StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
		int numOfEntries = -1;
		try {
			numOfEntries = stateCensusAnalyser.loadCSV(censusFile);
		} catch (IncorrectCSVFile e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertEquals(29, numOfEntries);
	}

	@Test
	public void givenIncorrectStateCensusCSVCheckforCustomException() {
		String censusFile = "./tateCensus.csv";
		StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
		int numOfEntries = -1;
		try {
			numOfEntries = stateCensusAnalyser.loadCSV(censusFile);
		} catch (IncorrectCSVFile e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertEquals(-1, numOfEntries);
	}

}
