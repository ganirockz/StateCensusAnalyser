package com.capgemini;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

import com.opencsv.bean.*;

public class StateCensusAnalyser {
	public int loadCSV(String filePath) throws IncorrectCSVFile {
		try {
			Reader reader = Files.newBufferedReader(Paths.get(filePath));
			CsvToBeanBuilder<IndiaCensusCSV> csvToBeanBuilder = new CsvToBeanBuilder<IndiaCensusCSV>(reader);
			csvToBeanBuilder.withType(IndiaCensusCSV.class);
			csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
			CsvToBean<IndiaCensusCSV> csvToBean = csvToBeanBuilder.build();
			Iterator<IndiaCensusCSV> IndiaCensusIterator = csvToBean.iterator();
			int numOfEntries = 0;
			while (IndiaCensusIterator.hasNext()) {
				numOfEntries++;
				IndiaCensusCSV censusData = IndiaCensusIterator.next();
			}
			return numOfEntries;
		} catch (IOException e) {
			throw new IncorrectCSVFile("Please provide the correct csv File");
		}
	}

	public static void main(String[] args) {
		String stateCensusFile = "./stateCensus.csv";
		StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
		try {
			System.out.println(stateCensusAnalyser.loadCSV(stateCensusFile));
		} catch (IncorrectCSVFile e) {
			e.printStackTrace();
		}
	}
}
