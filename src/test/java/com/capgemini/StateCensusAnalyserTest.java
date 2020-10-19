package com.capgemini;
import org.junit.*;
public class StateCensusAnalyserTest {
	@Test
	public void givenStateCensusCSVCheckToEnsureTheNumberOfRecords() {
		String censusFile = "./stateCensus.csv";
		StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
		int numOfEntries = stateCensusAnalyser.loadCSV(censusFile);
		Assert.assertEquals(29, numOfEntries);
	}
}
