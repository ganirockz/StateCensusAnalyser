package com.capgemini;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

import com.opencsv.bean.*;

public class CSVStates {
	public int loadCSV(String filePath) {
		try {
			Reader reader = Files.newBufferedReader(Paths.get(filePath));
			CsvToBeanBuilder<StateCodeCSV> csvToBeanBuilder = new CsvToBeanBuilder<StateCodeCSV>(reader);
			csvToBeanBuilder.withType(StateCodeCSV.class);
			csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
			CsvToBean<StateCodeCSV> csvToBean = csvToBeanBuilder.build();
			Iterator<StateCodeCSV> stateCodeIterator = csvToBean.iterator();
			int numOfEntries = 0;
			while (stateCodeIterator.hasNext()) {
				numOfEntries++;
				StateCodeCSV codeData = stateCodeIterator.next();
			}
			return numOfEntries;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static void main(String[] args) {
		String stateCodeFile = "./stateCode.csv";
		CSVStates csvStates = new CSVStates();
		System.out.println(csvStates.loadCSV(stateCodeFile));
	}

}
