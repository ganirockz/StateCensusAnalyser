package com.capgemini;

import com.opencsv.bean.*;

public class IndiaCensusCSV {
	@CsvBindByName
	String state;
	@CsvBindByName
	int population;
	@CsvBindByName
	int areaInSqkm;
	@CsvBindByName
	int densityPerSqKm;

	public String toString() {
		return state + " " + population + " " + areaInSqkm + " " + densityPerSqKm;
	}
}
