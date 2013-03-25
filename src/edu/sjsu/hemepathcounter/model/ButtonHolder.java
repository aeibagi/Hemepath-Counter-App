package edu.sjsu.hemepathcounter.model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import edu.sjsu.hemepathcounter.Custom_Modify_ButtonActivity;

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
	
	public void remove(CellButton ButtonToRemove)
	{
		if(defaultButtons.contains(ButtonToRemove))
		{
			defaultButtons.remove(defaultButtons.indexOf(ButtonToRemove));
		}
		else if(highYieldButtons.contains(ButtonToRemove))
		{
			highYieldButtons.remove(highYieldButtons.indexOf(ButtonToRemove));
		}
		else if(midYieldButtons.contains(ButtonToRemove))
		{
			midYieldButtons.remove(midYieldButtons.indexOf(ButtonToRemove));
		}
		else if(lowYieldButtons.contains(ButtonToRemove))
		{
			lowYieldButtons.remove(lowYieldButtons.indexOf(ButtonToRemove));
		}
		else if(customButtons.contains(ButtonToRemove))
		{
			customButtons.remove(customButtons.indexOf(ButtonToRemove));
		}
	}
	
	public void renameButton(CellButton itemSelectedforContextMenuOption,
			String newName) {
		
		if(defaultButtons.contains(itemSelectedforContextMenuOption))
		{
			defaultButtons.get(defaultButtons.indexOf(itemSelectedforContextMenuOption)).setName(newName);
		}
		else if(highYieldButtons.contains(itemSelectedforContextMenuOption))
		{
			highYieldButtons.get(highYieldButtons.indexOf(itemSelectedforContextMenuOption)).setName(newName);
		}
		else if(midYieldButtons.contains(itemSelectedforContextMenuOption))
		{
			midYieldButtons.get(midYieldButtons.indexOf(itemSelectedforContextMenuOption)).setName(newName);
		}
		else if(lowYieldButtons.contains(itemSelectedforContextMenuOption))
		{
			lowYieldButtons.get(lowYieldButtons.indexOf(itemSelectedforContextMenuOption)).setName(newName);
		}
		else if(customButtons.contains(itemSelectedforContextMenuOption))
		{
			customButtons.get(customButtons.indexOf(itemSelectedforContextMenuOption)).setName(newName);
		}
	}
	
	public void changeSoundofButton(CellButton itemSelectedforContextMenuOption,
			Integer newSound) {
		
		if(defaultButtons.contains(itemSelectedforContextMenuOption))
		{
			defaultButtons.get(defaultButtons.indexOf(itemSelectedforContextMenuOption)).setSound(newSound);
		}
		else if(highYieldButtons.contains(itemSelectedforContextMenuOption))
		{
			highYieldButtons.get(highYieldButtons.indexOf(itemSelectedforContextMenuOption)).setSound(newSound);
		}
		else if(midYieldButtons.contains(itemSelectedforContextMenuOption))
		{
			midYieldButtons.get(midYieldButtons.indexOf(itemSelectedforContextMenuOption)).setSound(newSound);
		}
		else if(lowYieldButtons.contains(itemSelectedforContextMenuOption))
		{
			lowYieldButtons.get(lowYieldButtons.indexOf(itemSelectedforContextMenuOption)).setSound(newSound);
		}
		else if(customButtons.contains(itemSelectedforContextMenuOption))
		{
			customButtons.get(customButtons.indexOf(itemSelectedforContextMenuOption)).setSound(newSound);
		}
	}

	public void changeColorofButton(CellButton itemSelectedforContextMenuOption,
			Integer newColor) {
		
		if(defaultButtons.contains(itemSelectedforContextMenuOption))
		{
			defaultButtons.get(defaultButtons.indexOf(itemSelectedforContextMenuOption)).setColor(newColor);
		}
		else if(highYieldButtons.contains(itemSelectedforContextMenuOption))
		{
			highYieldButtons.get(highYieldButtons.indexOf(itemSelectedforContextMenuOption)).setColor(newColor);
		}
		else if(midYieldButtons.contains(itemSelectedforContextMenuOption))
		{
			midYieldButtons.get(midYieldButtons.indexOf(itemSelectedforContextMenuOption)).setColor(newColor);
		}
		else if(lowYieldButtons.contains(itemSelectedforContextMenuOption))
		{
			lowYieldButtons.get(lowYieldButtons.indexOf(itemSelectedforContextMenuOption)).setColor(newColor);
		}
		else if(customButtons.contains(itemSelectedforContextMenuOption))
		{
			customButtons.get(customButtons.indexOf(itemSelectedforContextMenuOption)).setColor(newColor);
		}
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
