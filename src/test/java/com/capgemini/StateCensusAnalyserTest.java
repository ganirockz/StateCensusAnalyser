package com.capgemini;

import org.junit.*;

public class StateCensusAnalyserTest {
	@Test
	public void givenStateCensusCSVCheckToEnsureTheNumberOfRecords() {
		String censusFile = "./stateCensus.csv";
		StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
		int numOfEntries = -1;
		try {
			numOfEntries = stateCensusAnalyser.loadIndiaCensusCSV(censusFile);
		} catch (IncorrectCSVFileException e) {
			e.printStackTrace();
		}
		Assert.assertEquals(29, numOfEntries);
	}

	@Test
	public void givenIncorrectStateCensusCSVCheckforHeaderhouldReturnCustomException() {
		String censusFile = "./incorrectStateCensus.csv";
		StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
		int numOfEntries = -1;
		try {
			numOfEntries = stateCensusAnalyser.loadStateCodeCSV(censusFile);
		} catch (IncorrectCSVFileException e) {
			e.printStackTrace();
		}
		Assert.assertEquals(-1, numOfEntries);
	}

}
