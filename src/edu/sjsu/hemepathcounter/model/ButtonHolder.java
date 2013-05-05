package edu.sjsu.hemepathcounter.model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ButtonHolder implements JSONable {
	/*ArrayList<CellButton> defaultButtons;
	ArrayList<CellButton> highYieldButtons;
	ArrayList<CellButton> midYieldButtons;
	ArrayList<CellButton> lowYieldButtons;
	ArrayList<CellButton> customButtons;*/
	
	ArrayList<CellButton> Granulocytic_Lineage;
	ArrayList<CellButton> Lymphocytic_Lineage;
	ArrayList<CellButton> Erythrocytic_Lineage;
	ArrayList<CellButton> Monocytic_and_Megakaryocytic_Lineages;
	ArrayList<CellButton> Miscellaneous;
	ArrayList<CellButton> customButtons;

	public ButtonHolder(ArrayList<CellButton> Gra_Lin, ArrayList<CellButton> Lym_lin,
			ArrayList<CellButton> Ery_lin, ArrayList<CellButton> Mon_Mega_lin, ArrayList<CellButton> Misc) {
		/*defaultButtons = def;
		highYieldButtons = high;
		midYieldButtons = mid;
		lowYieldButtons = low;
		customButtons = new ArrayList<CellButton>();*/
		Granulocytic_Lineage = Gra_Lin;
		Lymphocytic_Lineage = Lym_lin;
		Erythrocytic_Lineage = Ery_lin;
		Monocytic_and_Megakaryocytic_Lineages = Mon_Mega_lin;
		Miscellaneous = Misc;
		customButtons = new ArrayList<CellButton>();
	}

	public ButtonHolder() {
		/*defaultButtons = new ArrayList<CellButton>();
		highYieldButtons = new ArrayList<CellButton>();
		midYieldButtons = new ArrayList<CellButton>();
		lowYieldButtons = new ArrayList<CellButton>();
		customButtons = new ArrayList<CellButton>();*/
		
		Granulocytic_Lineage = new ArrayList<CellButton>();
		Lymphocytic_Lineage = new ArrayList<CellButton>();
		Erythrocytic_Lineage = new ArrayList<CellButton>();
		Monocytic_and_Megakaryocytic_Lineages = new ArrayList<CellButton>();
		Miscellaneous = new ArrayList<CellButton>();
		customButtons = new ArrayList<CellButton>();	
	}

	/*
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
*/
	
	public ArrayList<CellButton> getGranulocytic_LineageButtons() {
		return Granulocytic_Lineage;
	}

	public ArrayList<CellButton> getLymphocytic_LineageButtons() {
		return Lymphocytic_Lineage;
	}

	public ArrayList<CellButton> getErythrocytic_LineageButtons() {
		return Erythrocytic_Lineage;
	}

	public ArrayList<CellButton> getMonocytic_and_Megakaryocytic_LineagesButtons() {
		return Monocytic_and_Megakaryocytic_Lineages;
	}
	
	public ArrayList<CellButton> getMiscellaneousButtons() {
		return Miscellaneous;
	}
	
	public ArrayList<CellButton> getCustomButtons() {
		return customButtons;
	}

	public void addCustomButton(CellButton newButton) {
		customButtons.add(newButton);
	}
	
	public void remove(CellButton ButtonToRemove)
	{
		/*if(defaultButtons.contains(ButtonToRemove))
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
		}*/
		
		if(Granulocytic_Lineage.contains(ButtonToRemove))
		{
			Granulocytic_Lineage.remove(Granulocytic_Lineage.indexOf(ButtonToRemove));
		}
		else if(Lymphocytic_Lineage.contains(ButtonToRemove))
		{
			Lymphocytic_Lineage.remove(Lymphocytic_Lineage.indexOf(ButtonToRemove));
		}
		else if(Erythrocytic_Lineage.contains(ButtonToRemove))
		{
			Erythrocytic_Lineage.remove(Erythrocytic_Lineage.indexOf(ButtonToRemove));
		}
		else if(Monocytic_and_Megakaryocytic_Lineages.contains(ButtonToRemove))
		{
			Monocytic_and_Megakaryocytic_Lineages.remove(Monocytic_and_Megakaryocytic_Lineages.indexOf(ButtonToRemove));
		}
		else if(Miscellaneous.contains(ButtonToRemove))
		{
			Miscellaneous.remove(Miscellaneous.indexOf(ButtonToRemove));
		}
		else if(customButtons.contains(ButtonToRemove))
		{
			customButtons.remove(customButtons.indexOf(ButtonToRemove));
		}
		
	}
	
	public void renameButton(CellButton itemSelectedforContextMenuOption,
			String newName) {
		
		/*if(defaultButtons.contains(itemSelectedforContextMenuOption))
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
		}*/
		
		if(Granulocytic_Lineage.contains(itemSelectedforContextMenuOption))
		{
			Granulocytic_Lineage.get(Granulocytic_Lineage.indexOf(itemSelectedforContextMenuOption)).setName(newName);
		}
		else if(Lymphocytic_Lineage.contains(itemSelectedforContextMenuOption))
		{
			Lymphocytic_Lineage.get(Lymphocytic_Lineage.indexOf(itemSelectedforContextMenuOption)).setName(newName);
		}
		else if(Erythrocytic_Lineage.contains(itemSelectedforContextMenuOption))
		{
			Erythrocytic_Lineage.get(Erythrocytic_Lineage.indexOf(itemSelectedforContextMenuOption)).setName(newName);
		}
		else if(Monocytic_and_Megakaryocytic_Lineages.contains(itemSelectedforContextMenuOption))
		{
			Monocytic_and_Megakaryocytic_Lineages.get(Monocytic_and_Megakaryocytic_Lineages.indexOf(itemSelectedforContextMenuOption)).setName(newName);
		}
		else if(Miscellaneous.contains(itemSelectedforContextMenuOption))
		{
			Miscellaneous.get(Miscellaneous.indexOf(itemSelectedforContextMenuOption)).setName(newName);
		}
		else if(customButtons.contains(itemSelectedforContextMenuOption))
		{
			customButtons.get(customButtons.indexOf(itemSelectedforContextMenuOption)).setName(newName);
		}
		
	}
	
	public void changeAbbrofButton(CellButton itemSelectedforContextMenuOption,
			String newAbbr) {
		/*
		if(defaultButtons.contains(itemSelectedforContextMenuOption))
		{
			defaultButtons.get(defaultButtons.indexOf(itemSelectedforContextMenuOption)).setAbbr(newAbbr);
		}
		else if(highYieldButtons.contains(itemSelectedforContextMenuOption))
		{
			highYieldButtons.get(highYieldButtons.indexOf(itemSelectedforContextMenuOption)).setAbbr(newAbbr);
		}
		else if(midYieldButtons.contains(itemSelectedforContextMenuOption))
		{
			midYieldButtons.get(midYieldButtons.indexOf(itemSelectedforContextMenuOption)).setAbbr(newAbbr);
		}
		else if(lowYieldButtons.contains(itemSelectedforContextMenuOption))
		{
			lowYieldButtons.get(lowYieldButtons.indexOf(itemSelectedforContextMenuOption)).setAbbr(newAbbr);
		}
		else if(customButtons.contains(itemSelectedforContextMenuOption))
		{
			customButtons.get(customButtons.indexOf(itemSelectedforContextMenuOption)).setAbbr(newAbbr);
		}*/
		
		if(Granulocytic_Lineage.contains(itemSelectedforContextMenuOption))
		{
			Granulocytic_Lineage.get(Granulocytic_Lineage.indexOf(itemSelectedforContextMenuOption)).setAbbr(newAbbr);
		}
		else if(Lymphocytic_Lineage.contains(itemSelectedforContextMenuOption))
		{
			Lymphocytic_Lineage.get(Lymphocytic_Lineage.indexOf(itemSelectedforContextMenuOption)).setAbbr(newAbbr);
		}
		else if(Erythrocytic_Lineage.contains(itemSelectedforContextMenuOption))
		{
			Erythrocytic_Lineage.get(Erythrocytic_Lineage.indexOf(itemSelectedforContextMenuOption)).setAbbr(newAbbr);
		}
		else if(Monocytic_and_Megakaryocytic_Lineages.contains(itemSelectedforContextMenuOption))
		{
			Monocytic_and_Megakaryocytic_Lineages.get(Monocytic_and_Megakaryocytic_Lineages.indexOf(itemSelectedforContextMenuOption)).setAbbr(newAbbr);
		}
		else if(Miscellaneous.contains(itemSelectedforContextMenuOption))
		{
			Miscellaneous.get(Miscellaneous.indexOf(itemSelectedforContextMenuOption)).setAbbr(newAbbr);
		}
		else if(customButtons.contains(itemSelectedforContextMenuOption))
		{
			customButtons.get(customButtons.indexOf(itemSelectedforContextMenuOption)).setAbbr(newAbbr);
		}
		
	}
	
	
	public void changeSoundofButton(CellButton itemSelectedforContextMenuOption,
			Integer newSound) {
		
		/*if(defaultButtons.contains(itemSelectedforContextMenuOption))
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
		}*/
		
		if(Granulocytic_Lineage.contains(itemSelectedforContextMenuOption))
		{
			Granulocytic_Lineage.get(Granulocytic_Lineage.indexOf(itemSelectedforContextMenuOption)).setSound(newSound);
		}
		else if(Lymphocytic_Lineage.contains(itemSelectedforContextMenuOption))
		{
			Lymphocytic_Lineage.get(Lymphocytic_Lineage.indexOf(itemSelectedforContextMenuOption)).setSound(newSound);
		}
		else if(Erythrocytic_Lineage.contains(itemSelectedforContextMenuOption))
		{
			Erythrocytic_Lineage.get(Erythrocytic_Lineage.indexOf(itemSelectedforContextMenuOption)).setSound(newSound);
		}
		else if(Monocytic_and_Megakaryocytic_Lineages.contains(itemSelectedforContextMenuOption))
		{
			Monocytic_and_Megakaryocytic_Lineages.get(Monocytic_and_Megakaryocytic_Lineages.indexOf(itemSelectedforContextMenuOption)).setSound(newSound);
		}
		else if(Miscellaneous.contains(itemSelectedforContextMenuOption))
		{
			Miscellaneous.get(Miscellaneous.indexOf(itemSelectedforContextMenuOption)).setSound(newSound);
		}
		else if(customButtons.contains(itemSelectedforContextMenuOption))
		{
			customButtons.get(customButtons.indexOf(itemSelectedforContextMenuOption)).setSound(newSound);
		}
	}

	public void changeColorofButton(CellButton itemSelectedforContextMenuOption,
			Integer newColor) {
		
		/*if(defaultButtons.contains(itemSelectedforContextMenuOption))
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
		}*/
		
		if(Granulocytic_Lineage.contains(itemSelectedforContextMenuOption))
		{
			Granulocytic_Lineage.get(Granulocytic_Lineage.indexOf(itemSelectedforContextMenuOption)).setColor(newColor);
		}
		else if(Lymphocytic_Lineage.contains(itemSelectedforContextMenuOption))
		{
			Lymphocytic_Lineage.get(Lymphocytic_Lineage.indexOf(itemSelectedforContextMenuOption)).setColor(newColor);
		}
		else if(Erythrocytic_Lineage.contains(itemSelectedforContextMenuOption))
		{
			Erythrocytic_Lineage.get(Erythrocytic_Lineage.indexOf(itemSelectedforContextMenuOption)).setColor(newColor);
		}
		else if(Monocytic_and_Megakaryocytic_Lineages.contains(itemSelectedforContextMenuOption))
		{
			Monocytic_and_Megakaryocytic_Lineages.get(Monocytic_and_Megakaryocytic_Lineages.indexOf(itemSelectedforContextMenuOption)).setColor(newColor);
		}
		else if(Miscellaneous.contains(itemSelectedforContextMenuOption))
		{
			Miscellaneous.get(Miscellaneous.indexOf(itemSelectedforContextMenuOption)).setColor(newColor);
		}
		else if(customButtons.contains(itemSelectedforContextMenuOption))
		{
			customButtons.get(customButtons.indexOf(itemSelectedforContextMenuOption)).setColor(newColor);
		}
		
		
	}
	@Override
	public JSONObject toJSONObject() throws JSONException {
		JSONObject jo = new JSONObject();
		/*JSONArray defaultArray = new JSONArray();
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
		return jo;*/
		
		JSONArray Granulocytic_Lineage_Array = new JSONArray();
		for (CellButton c : Granulocytic_Lineage) {
			Granulocytic_Lineage_Array.put(c.toJSONObject());
		}
		jo.put("Granulocytic_Lineage_Buttons", Granulocytic_Lineage_Array);
		
		JSONArray Lymphocytic_Lineage_Array = new JSONArray();
		for (CellButton c : Lymphocytic_Lineage) {
			Lymphocytic_Lineage_Array.put(c.toJSONObject());
		}
		jo.put("Lymphocytic_Lineage_Buttons", Lymphocytic_Lineage_Array);
		
		JSONArray Erythrocytic_Lineage_Array = new JSONArray();
		for (CellButton c : Erythrocytic_Lineage) {
			Erythrocytic_Lineage_Array.put(c.toJSONObject());
		}
		jo.put("Erythrocytic_Lineage_Buttons", Erythrocytic_Lineage_Array);
		
		JSONArray Monocytic_and_Megakaryocytic_Lineages_Array = new JSONArray();
		for (CellButton c : Monocytic_and_Megakaryocytic_Lineages) {
			Monocytic_and_Megakaryocytic_Lineages_Array.put(c.toJSONObject());
		}
		jo.put("Monocytic_and_Megakaryocytic_Lineages_Buttons", Monocytic_and_Megakaryocytic_Lineages_Array);
		
		
		JSONArray Miscellaneous_Array = new JSONArray();
		for (CellButton c : Miscellaneous) {
			Miscellaneous_Array.put(c.toJSONObject());
		}
		jo.put("Miscellaneous_Buttons", Miscellaneous_Array);
		
		
		JSONArray customArray = new JSONArray();
		for (CellButton c : customButtons) {
			customArray.put(c.toJSONObject());
		}
		jo.put("customButtons", customArray);
		return jo;
	}

	@Override
	public void fromJSONObject(JSONObject src) throws JSONException {
		/*JSONArray defaultArray = src.getJSONArray("defaultButtons");
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
		}*/
		
		JSONArray Granulocytic_Lineage_Array = src.getJSONArray("Granulocytic_Lineage_Buttons");
		this.Granulocytic_Lineage = new ArrayList<CellButton>();
		for(int i = 0; i<Granulocytic_Lineage_Array.length();i++){
			CellButton c = new CellButton();
			c.fromJSONObject(Granulocytic_Lineage_Array.getJSONObject(i));
			Granulocytic_Lineage.add(c);
		}
		
		JSONArray Lymphocytic_Lineage_Array = src.getJSONArray("Lymphocytic_Lineage_Buttons");
		this.Lymphocytic_Lineage = new ArrayList<CellButton>();
		for(int i = 0; i<Lymphocytic_Lineage_Array.length();i++){
			CellButton c = new CellButton();
			c.fromJSONObject(Lymphocytic_Lineage_Array.getJSONObject(i));
			Lymphocytic_Lineage.add(c);
		}
		
		JSONArray Erythrocytic_Lineage_Array = src.getJSONArray("Erythrocytic_Lineage_Buttons");
		this.Erythrocytic_Lineage = new ArrayList<CellButton>();
		for(int i = 0; i<Erythrocytic_Lineage_Array.length();i++){
			CellButton c = new CellButton();
			c.fromJSONObject(Erythrocytic_Lineage_Array.getJSONObject(i));
			Erythrocytic_Lineage.add(c);
		}
		
		JSONArray Monocytic_and_Megakaryocytic_Lineages_Array = src.getJSONArray("Monocytic_and_Megakaryocytic_Lineages_Buttons");
		this.Monocytic_and_Megakaryocytic_Lineages = new ArrayList<CellButton>();
		for(int i = 0; i<Monocytic_and_Megakaryocytic_Lineages_Array.length();i++){
			CellButton c = new CellButton();
			c.fromJSONObject(Monocytic_and_Megakaryocytic_Lineages_Array.getJSONObject(i));
			Monocytic_and_Megakaryocytic_Lineages.add(c);
		}
		
		
		JSONArray Miscellaneous_Array = src.getJSONArray("Miscellaneous_Buttons");
		this.Miscellaneous = new ArrayList<CellButton>();
		for(int i = 0; i<Miscellaneous_Array.length();i++){
			CellButton c = new CellButton();
			c.fromJSONObject(Miscellaneous_Array.getJSONObject(i));
			Miscellaneous.add(c);
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
