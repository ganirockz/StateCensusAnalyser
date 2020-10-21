package com.capgemini;

import com.opencsv.bean.CsvBindByName;

public class StateCodeCSV {
	@CsvBindByName
	public int SrNo;
	@CsvBindByName
	public String StateName;
	@CsvBindByName
	public int TIN;
	@CsvBindByName
	public String StateCode;

	@Override
	public String toString() {
		return SrNo + " " + StateName + " " + TIN + " " + StateCode;
	}
}
