package edu.sjsu.hemepathcounter.model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import edu.sjsu.hemepathcounter.R;

import android.os.Parcel;
import android.os.Parcelable;

public class Counter implements Parcelable, JSONable {
	private ArrayList<CellButton> buttons;
	private int total;
	private String name;
	private int numUses;

	public Counter(String inName, ArrayList<CellButton> inButtons) {
		this.name = inName;
		this.buttons = inButtons;
	}

	public void removeaButton(CellButton itemToRemove)
	{
		buttons.remove(itemToRemove);
	}
	public void reset() {
		total = 0;
		for (CellButton cell : buttons)
			cell.reset();
	}
	
	public ArrayList<CellButton> getCells() {
		return buttons;
	}

	public String getName() {
		return name;
	}

	public Integer getNumUses() {
		return numUses;
	}

	public int getTotal() {
		return total;
	}

	public void incrementTotal() {
		total++;
	}

	public void decrementTotal() {
		total--;
	}

	public void addTotal(int number) {
		total += number;
	}
	
	public Counter(Parcel in) {
		readFromParcel(in);
	}

	public Counter() {
		buttons = new ArrayList<CellButton>();
		name = null;
		total = 0;
		numUses = 0;
	}

	@SuppressWarnings("unchecked")
	private void readFromParcel(Parcel in) {
		buttons = in.createTypedArrayList(CellButton.CREATOR);
		total = in.readInt();
		name = in.readString();
		numUses = in.readInt();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeTypedList(buttons);
		dest.writeInt(total);
		dest.writeString(name);
		dest.writeInt(numUses);

	}

	@SuppressWarnings("rawtypes")
	public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
		public Counter createFromParcel(Parcel in) {
			return new Counter(in);
		}

		public Counter[] newArray(int size) {
			return new Counter[size];
		}
	};

	public void setName(String newName) {
		name = newName;
	}

	@Override
	public String toString() {
		return name;
	}

	public void incrementUses() {
		numUses++;
	}

	@Override
	public JSONObject toJSONObject() throws JSONException {
		JSONObject jo = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		for (CellButton c : buttons) {
			jsonArray.put(c.toJSONObject());
		}
		jo.put("buttons", jsonArray);
		jo.put("total", total);
		jo.put("name", name);
		jo.put("numUses", numUses);
		return jo;
	}

	@Override
	public void fromJSONObject(JSONObject src) throws JSONException {
		JSONArray jsonArray = src.getJSONArray("buttons");
		this.buttons = new ArrayList<CellButton>();
		for(int i = 0; i<jsonArray.length();i++){
			CellButton c = new CellButton();
			c.fromJSONObject(jsonArray.getJSONObject(i));
			buttons.add(c);
		}
		this.total = src.getInt("total");
		this.name = src.getString("name");
		this.numUses = src.getInt("numUses");
	}

}
