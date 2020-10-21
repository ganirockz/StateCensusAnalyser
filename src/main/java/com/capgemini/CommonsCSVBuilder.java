package com.capgemini;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.Iterator;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

public class CommonsCSVBuilder<E> implements ICSVBuilder<E>{
	@Override
	public <E> Iterator<E> getCSVFileIterator(Reader reader, Class<E> csvClass) throws IncorrectCSVException {
		try {
			CSVParser csvParser = CSVParser.parse(reader, CSVFormat.DEFAULT.withHeaderComments(csvClass));
			return (Iterator<E>) csvParser.iterator();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
