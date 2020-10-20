package com.capgemini;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

import com.opencsv.bean.*;

public class CSVStates {
	public int loadCSV(String filePath) throws IncorrectCSVFile {
		try {
			Reader reader = Files.newBufferedReader(Paths.get(filePath));
			CsvToBeanBuilder<StateCodeCSV> csvToBeanBuilder = new CsvToBeanBuilder<StateCodeCSV>(reader);
			csvToBeanBuilder.withType(StateCodeCSV.class);
			csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
			CsvToBean<StateCodeCSV> csvToBean = csvToBeanBuilder.build();
			Iterator<StateCodeCSV> stateCodeIterator = csvToBean.iterator();
			Iterable<StateCodeCSV> csvIterable = () -> stateCodeIterator;
			int numOfEntries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
			return numOfEntries;
		} catch (IOException e) {
			throw new IncorrectCSVFile("The csv file is incorrect");
		} catch (IllegalStateException e) {
			throw new IncorrectCSVFile("The csv file is doesn't have required data");
		}
	}

	public static void main(String[] args) {
		String stateCodeFile = "./stateCode.csv";
		CSVStates csvStates = new CSVStates();
		try {
			System.out.println(csvStates.loadCSV(stateCodeFile));
		} catch (IncorrectCSVFile e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
