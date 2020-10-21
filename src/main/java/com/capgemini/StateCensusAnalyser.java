package com.capgemini;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;

import com.opencsv.CSVReader;
import com.opencsv.bean.*;

public class StateCensusAnalyser {
	public int loadIndiaCensusCSV(String filePath) throws IncorrectCSVFileException {
		try {
			Reader reader = Files.newBufferedReader(Paths.get(filePath));
			Reader fileReader = Files.newBufferedReader(Paths.get(filePath));
			CSVReader csvReader = new CSVReader(fileReader);
			String[] nextRow = csvReader.readNext();
			if (!((nextRow[0].equals("State")) && (nextRow[1].equals("Population")
					&& (nextRow[2].equals("AreaInSqKm") && (nextRow[3].equals("DensityPerSqKm")))))) {
				throw new IncorrectCSVFileException("Incorrect header");
			}
			fileReader.close();
			csvReader.close();
			Iterator<IndiaCensusCSV> IndiaCensusIterator = new OpenCSVBuilder().getCSVFileIterator(reader,
					IndiaCensusCSV.class);
			return this.getCount(IndiaCensusIterator);
		} catch (IOException e) {
			throw new IncorrectCSVFileException("Please provide the correct csv File");
		}
	}

	public int loadStateCodeCSV(String filePath) throws IncorrectCSVFileException {
		try {
			Reader reader = Files.newBufferedReader(Paths.get(filePath));
			Reader fileReader = Files.newBufferedReader(Paths.get(filePath));
			CSVReader csvReader = new CSVReader(fileReader);
			String[] nextRow = csvReader.readNext();
			if (!((nextRow[0].equals("SrNo")) && (nextRow[1].equals("State Name")
					&& (nextRow[2].equals("TIN") && (nextRow[3].equals("StateCode")))))) {
				csvReader.close();
				throw new IncorrectCSVFileException("Incorrect header");
			}
			fileReader.close();
			csvReader.close();
			Iterator<StateCodeCSV> stateCodeIterator = new OpenCSVBuilder().getCSVFileIterator(reader,
					StateCodeCSV.class);
			return this.getCount(stateCodeIterator);
		} catch (IOException e) {
			throw new IncorrectCSVFileException("The csv file is incorrect");
		}
	}

	private <E> int getCount(Iterator<E> iterator) {
		Iterable<E> csvIterable = () -> iterator;
		int numOfEntries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
		return numOfEntries;
	}
}
