package com.capgemini;

import org.junit.*;

import com.google.gson.Gson;

public class CSVStatesTest {
	@Test
	public void givenStateCensusCSVCheckToEnsureTheNumberOfRecords() {
		String censusFile = "./stateCode.csv";
		StateCensusAnalyser stateCodeAnalyser = new StateCensusAnalyser();
		int numOfEntries = -1;
		try {
			numOfEntries = stateCodeAnalyser.loadStateCodeCSV(censusFile);
		} catch (IncorrectCSVException e) {
			e.printStackTrace();
		}
		Assert.assertEquals(37, numOfEntries);
	}

	@Test
	public void givenStateCodeData_WhenSortedOnState_ShouldReturnSortedResult() {
		String censusFile = "./stateCode.csv";
		StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
		String sortedData = null;
		try {
			int numOfRecords = censusAnalyser.loadStateCodeCSV(censusFile);
			sortedData = censusAnalyser.getStateCodeWiseSortedCensusData();
		} catch (IncorrectCSVException e) {
			e.printStackTrace();
		}
		System.out.println(sortedData);
		StateCodeCSV[] censusCsv = new Gson().fromJson(sortedData, StateCodeCSV[].class);
		Assert.assertEquals("AD", censusCsv[0].StateCode);
	}
}
