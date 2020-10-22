package com.capgemini;

import org.junit.*;

import com.google.gson.Gson;

public class StateCensusAnalyserTest {
	@Test
	public void givenStateCensusCSVCheckToEnsureTheNumberOfRecords() {
		String censusFile = "./stateCensus.csv";
		StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
		int numOfEntries = -1;
		try {
			numOfEntries = stateCensusAnalyser.loadIndiaCensusCSV(censusFile);
		} catch (IncorrectCSVException e) {
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
		} catch (IncorrectCSVException e) {
			e.printStackTrace();
		}
		Assert.assertEquals(-1, numOfEntries);
	}

	@Test
	public void givenIndiaCensusData_WhenSortedOnState_ShouldReturnSortedResult() {
		String censusFile = "./stateCensus.csv";
		StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
		String sortedData = null;
		try {
			int numOfRecords = censusAnalyser.loadIndiaCensusCSV(censusFile);
			sortedData = censusAnalyser.getStateWiseSortedCensusData();
		} catch (IncorrectCSVException e) {
			e.printStackTrace();
		}
		System.out.println(sortedData);
		IndiaCensusCSV[] censusCsv = new Gson().fromJson(sortedData, IndiaCensusCSV[].class);
		Assert.assertEquals("Andhra Pradesh", censusCsv[0].state);
	}

	@Test
	public void givenIndiaCensusData_WhenSortedOnPopulation_ShouldReturnSortedResult() {
		String censusFile = "./stateCensus.csv";
		StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
		String sortedData = null;
		try {
			int numOfRecords = censusAnalyser.loadIndiaCensusCSV(censusFile);
			sortedData = censusAnalyser.getPopulationWiseSortedCensusData();
		} catch (IncorrectCSVException e) {
			e.printStackTrace();
		}
		System.out.println(sortedData);
		IndiaCensusCSV[] censusCsv = new Gson().fromJson(sortedData, IndiaCensusCSV[].class);
		Assert.assertEquals("Uttar Pradesh", censusCsv[0].state);
	}
}
