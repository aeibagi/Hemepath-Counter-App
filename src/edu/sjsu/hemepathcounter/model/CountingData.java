package edu.sjsu.hemepathcounter.model;

import java.util.ArrayList;
import java.util.List;

public class CountingData {
	public List<CellData> cells;
	public int total;
	
	public CountingData() {
		//pre-define database for testing purpose
		cells = new ArrayList<CellData>();
		cells.add(new CellData("Lymphs"));
		cells.add(new CellData("Monos"));
		cells.add(new CellData("Eos"));
		cells.add(new CellData("Basos"));
		cells.add(new CellData("PMNs"));
		cells.add(new CellData("Bands"));
		cells.add(new CellData("Blasts"));
		cells.add(new CellData("Retics"));
	}
}
