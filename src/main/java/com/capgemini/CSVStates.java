package com.capgemini;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

import com.opencsv.CSVReader;
import com.opencsv.bean.*;

public class CSVStates {
	public int loadCSV(String filePath) throws IncorrectCSVFile {
		try {
			Reader reader = Files.newBufferedReader(Paths.get(filePath));
			Reader fileReader = Files.newBufferedReader(Paths.get(filePath));
			CSVReader csvReader = new CSVReader(fileReader);
			String[] nextRow = csvReader.readNext();
			if (!((nextRow[0].equals("SrNo")) && (nextRow[1].equals("State Name")
					&& (nextRow[2].equals("TIN") && (nextRow[3].equals("StateCode")))))) {
				csvReader.close();
				throw new IncorrectCSVFile("Incorrect header");
			}
			fileReader.close();
			CsvToBeanBuilder<StateCodeCSV> csvToBeanBuilder = new CsvToBeanBuilder<StateCodeCSV>(reader);
			csvToBeanBuilder.withType(StateCodeCSV.class);
			csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
			CsvToBean<StateCodeCSV> csvToBean = csvToBeanBuilder.build();
			Iterator<StateCodeCSV> stateCodeIterator = csvToBean.iterator();
			Iterable<StateCodeCSV> csvIterable = () -> stateCodeIterator;
			int numOfEntries = 0;
			while (stateCodeIterator.hasNext()) {
				numOfEntries++;
				StateCodeCSV stateCodeData = stateCodeIterator.next();
				if ((stateCodeData.SrNo < 0) || (stateCodeData.StateName == null) || (stateCodeData.TIN < 0)
						|| (stateCodeData.StateCode == null)) {
					throw new IncorrectCSVFile("Please correct the details in csv file");
				}
			}
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
