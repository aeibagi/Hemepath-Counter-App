package edu.sjsu.hemepathcounter.model;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Parcel;
import android.os.Parcelable;

public class CellButton implements Parcelable, JSONable {
	private String name;
	private String abbr;
	private Integer sound_id;
	private Integer color_id;
	private int count;

	public CellButton(String _name, String _abbr, int _sound, int drawable) {
		name = _name;
		abbr = _abbr;
		sound_id = _sound;
		color_id = drawable;
		count = 0;
	}

	public void reset() {
		count = 0;
	}
	
	public CellButton(Parcel in) {
		readFromParcel(in);
		count = 0;
	}

	public CellButton() {
		name = null;
		abbr = null;
		sound_id = null;
		color_id = null;
		count = 0;
	}

	private void readFromParcel(Parcel in) {
		name = in.readString();
		abbr = in.readString();
		sound_id = in.readInt();
		color_id = in.readInt();
	}

	@SuppressWarnings("rawtypes")
	public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
		public CellButton createFromParcel(Parcel in) {
			return new CellButton(in);
		}

		public CellButton[] newArray(int size) {
			return new CellButton[size];
		}
	};

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(name);
		dest.writeString(abbr);
		dest.writeInt(sound_id);
		dest.writeInt(color_id);
	}

	public void incrementCount() {
		count++;
	}

	public void decrementCount() {
		count--;
	}

	public String getName() {
		return name;
	}
	
	public void setCount(int _count) {
		count = _count;
	}

	public String getAbbr() {
		return abbr;
	}

	public Integer getSound() {
		return sound_id;
	}

	public Integer getColor() {
		return color_id;
	}

	public int getCount() {
		return count;
	}

	public void setName(String newName) {
		name = newName;
	}
	
	public void setSound(Integer newSound)
	{
		sound_id = newSound;
	}
	
	public void setColor(Integer newColor)
	{
		color_id = newColor;
	}

	@Override
	public String toString() {
		return name;
	}
	@Override
	public JSONObject toJSONObject() throws JSONException {
		JSONObject jo = new JSONObject();
		jo.put("name", name);
		jo.put("abbr", abbr);
		jo.put("sound_id", sound_id);
		jo.put("color_id", color_id);
		return jo;
	}

	@Override
	public void fromJSONObject(JSONObject src) throws JSONException {
		this.name = src.getString("name");
		this.abbr = src.getString("abbr");
		this.sound_id = src.getInt("sound_id");
		this.color_id = src.getInt("color_id");
		this.count = 0;
	}
}
