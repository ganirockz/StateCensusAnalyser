package com.capgemini;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public interface ICSVBuilder<E> {
	public <E> Iterator<E> getCSVFileIterator(Reader reader, Class<E> csvClass) throws IncorrectCSVException;

	public List<E> getCSVFileList(Reader reader, Class csvClass) throws IncorrectCSVException;

}
