package com.capgemini;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

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
			Iterable<IndiaCensusCSV> csvIterable = () -> IndiaCensusIterator;
			int numOfEntries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
			return numOfEntries;
		} catch (IOException e) {
			throw new IncorrectCSVFile("Please provide the correct csv File");
		} catch (IllegalStateException e) {
			throw new IncorrectCSVFile("please provide the correct details in file");
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
