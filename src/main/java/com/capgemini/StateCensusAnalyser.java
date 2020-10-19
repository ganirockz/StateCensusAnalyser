package com.capgemini;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

import com.opencsv.CSVReader;
import com.opencsv.bean.*;

public class StateCensusAnalyser {
	public int loadCSV(String filePath) throws IncorrectCSVFile {
		try {
			Reader reader = Files.newBufferedReader(Paths.get(filePath));
			Reader fileReader = Files.newBufferedReader(Paths.get(filePath));
			CSVReader csvReader = new CSVReader(fileReader);
			String[] nextRow = csvReader.readNext();
			if (!((nextRow[0].equals("State")) && (nextRow[1].equals("Population")
					&& (nextRow[2].equals("AreaInSqKm") && (nextRow[3].equals("DensityPerSqKm")))))) {
				throw new IncorrectCSVFile("Incorrect header");
			}
			fileReader.close();
			csvReader.close();
			CsvToBeanBuilder<IndiaCensusCSV> csvToBeanBuilder = new CsvToBeanBuilder<IndiaCensusCSV>(reader);
			csvToBeanBuilder.withType(IndiaCensusCSV.class);
			csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
			CsvToBean<IndiaCensusCSV> csvToBean = csvToBeanBuilder.build();
			Iterator<IndiaCensusCSV> IndiaCensusIterator = csvToBean.iterator();
			int numOfEntries = 0;
			while (IndiaCensusIterator.hasNext()) {
				numOfEntries++;
				IndiaCensusCSV censusData = IndiaCensusIterator.next();
				if ((censusData.state == null) || (censusData.population == 0) || (censusData.areaInSqkm == 0)
						|| (censusData.densityPerSqKm == 0)) {
					throw new IncorrectCSVFile("Please correct the details in csv file");
				}
			}
			reader.close();
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
