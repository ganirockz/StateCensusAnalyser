package com.capgemini;

import java.io.Reader;
import java.util.*;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class OpenCSVBuilder<E> implements ICSVBuilder {

	public Iterator<E> getCSVFileIterator(Reader reader, Class csvClass) throws IncorrectCSVException {
		return ((CsvToBean<E>) this.getCSVFileIterator(reader, csvClass)).iterator();
	}

	public List<E> getCSVFileList(Reader reader, Class csvClass) throws IncorrectCSVException {
		return this.getCSVBean(reader, csvClass).parse();
	}

	private CsvToBean<E> getCSVBean(Reader reader, Class csvClass) throws IncorrectCSVException {
		try {
			CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder<E>(reader);
			csvToBeanBuilder.withType(csvClass);
			csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
			return csvToBeanBuilder.build();
		} catch (IllegalStateException e) {
			throw new IncorrectCSVException("Unable to Parse");
		}
	}
}
