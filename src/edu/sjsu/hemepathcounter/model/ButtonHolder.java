package edu.sjsu.hemepathcounter.model;

import java.util.ArrayList;

public class ButtonHolder {
	ArrayList<CellButton> defaultButtons;
	ArrayList<CellButton> highYieldButtons;
	ArrayList<CellButton> midYieldButtons;
	ArrayList<CellButton> lowYieldButtons;
	ArrayList<CellButton> customButtons;

	public ButtonHolder(ArrayList<CellButton> def, ArrayList<CellButton> high,
			ArrayList<CellButton> mid, ArrayList<CellButton> low) {
		defaultButtons = def;
		highYieldButtons = high;
		midYieldButtons = mid;
		lowYieldButtons = low;
		customButtons = new ArrayList<CellButton>();
	}

	public ArrayList<CellButton> getDefaultButtons() {
		return defaultButtons;
	}

	public ArrayList<CellButton> getHighYieldButtons() {
		return highYieldButtons;
	}

	public ArrayList<CellButton> getMidYieldButtons() {
		return midYieldButtons;
	}

	public ArrayList<CellButton> getLowYieldButtons() {
		return lowYieldButtons;
	}

	public ArrayList<CellButton> getCustomButtons() {
		return customButtons;
	}

	public void addCustomButton(CellButton newButton) {
		customButtons.add(newButton);
	}

}
