package edu.sjsu.hemepathcounter.model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ButtonHolder implements JSONable {
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

	public ButtonHolder() {
		defaultButtons = new ArrayList<CellButton>();
		highYieldButtons = new ArrayList<CellButton>();
		midYieldButtons = new ArrayList<CellButton>();
		lowYieldButtons = new ArrayList<CellButton>();
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

	@Override
	public JSONObject toJSONObject() throws JSONException {
		JSONObject jo = new JSONObject();
		JSONArray defaultArray = new JSONArray();
		for (CellButton c : defaultButtons) {
			defaultArray.put(c.toJSONObject());
		}
		jo.put("defaultButtons", defaultArray);
		
		JSONArray highArray = new JSONArray();
		for (CellButton c : highYieldButtons) {
			highArray.put(c.toJSONObject());
		}
		jo.put("highYieldButtons", highArray);
		
		JSONArray midArray = new JSONArray();
		for (CellButton c : midYieldButtons) {
			midArray.put(c.toJSONObject());
		}
		jo.put("midYieldButtons", midArray);
		
		JSONArray lowArray = new JSONArray();
		for (CellButton c : lowYieldButtons) {
			lowArray.put(c.toJSONObject());
		}
		jo.put("lowYieldButtons", lowArray);
		
		JSONArray customArray = new JSONArray();
		for (CellButton c : customButtons) {
			customArray.put(c.toJSONObject());
		}
		jo.put("customButtons", customArray);
		return jo;
	}

	@Override
	public void fromJSONObject(JSONObject src) throws JSONException {
		JSONArray defaultArray = src.getJSONArray("defaultButtons");
		this.defaultButtons = new ArrayList<CellButton>();
		for(int i = 0; i<defaultArray.length();i++){
			CellButton c = new CellButton();
			c.fromJSONObject(defaultArray.getJSONObject(i));
			defaultButtons.add(c);
		}
		
		JSONArray highYieldArray = src.getJSONArray("highYieldButtons");
		this.highYieldButtons = new ArrayList<CellButton>();
		for(int i = 0; i<highYieldArray.length();i++){
			CellButton c = new CellButton();
			c.fromJSONObject(highYieldArray.getJSONObject(i));
			highYieldButtons.add(c);
		}
		
		JSONArray midYieldArray = src.getJSONArray("midYieldButtons");
		this.midYieldButtons = new ArrayList<CellButton>();
		for(int i = 0; i<midYieldArray.length();i++){
			CellButton c = new CellButton();
			c.fromJSONObject(midYieldArray.getJSONObject(i));
			midYieldButtons.add(c);
		}
		
		JSONArray lowYieldArray = src.getJSONArray("lowYieldButtons");
		this.lowYieldButtons = new ArrayList<CellButton>();
		for(int i = 0; i<lowYieldArray.length();i++){
			CellButton c = new CellButton();
			c.fromJSONObject(lowYieldArray.getJSONObject(i));
			lowYieldButtons.add(c);
		}
		
		JSONArray customArray = src.getJSONArray("customButtons");
		this.customButtons = new ArrayList<CellButton>();
		for(int i = 0; i<customArray.length();i++){
			CellButton c = new CellButton();
			c.fromJSONObject(customArray.getJSONObject(i));
			customButtons.add(c);
		}
		
	}

}
