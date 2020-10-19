package com.capgemini;
import com.opencsv.bean.*;

public class IndiaCensusCSV {
	@CsvBindByName
	String state;
	@CsvBindByName
	int population;
	@CsvBindByName
	int areaPerSqkm;
	@CsvBindByName
	int densityPerSqKm;
}
