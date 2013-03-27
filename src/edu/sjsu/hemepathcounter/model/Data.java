package edu.sjsu.hemepathcounter.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.format.DateFormat;

public class Data implements Parcelable, JSONable {
	private HashMap<String, Integer> map;
	private int total;
	private String timestamp;

	public Data(Counter counter, Context context) {
		// Fill the map with the names of the buttons, and their counts.
		map = new HashMap<String, Integer>();
		for (CellButton c : counter.getCells()) {
			map.put(c.getName(), c.getCount());
		}

		// Format the timestamp and set the variable
		java.text.DateFormat dateFormat = DateFormat.getDateFormat(context);
		java.text.DateFormat timeFormat = DateFormat.getTimeFormat(context);
		Date d = new Date();
		timestamp = dateFormat.format(d) + " " + timeFormat.format(d);

		// Set the total
		total = 0;
		for (String key : map.keySet()) {
			total += map.get(key).intValue();
		}
	}

	public Data() {
		map = new HashMap<String, Integer>();
		total = 0;
		timestamp = "";
	}

	public Data(Parcel in) {
		readFromParcel(in);
	}

	public HashMap<String, Integer> getMap() {
		return map;
	}

	public int getTotal() {
		return total;
	}

	public String getTimestamp() {
		return timestamp;
	}

	@Override
	public JSONObject toJSONObject() throws JSONException {
		JSONObject jo = new JSONObject();
		JSONArray keyArray = new JSONArray(map.keySet());
		JSONArray valueArray = new JSONArray();
		for (String key : map.keySet()) {
			valueArray.put(map.get(key));
		}
		jo.put("keyArray", keyArray);
		jo.put("valueArray", valueArray);
		jo.put("timestamp", timestamp);
		return jo;
	}

	@Override
	public void fromJSONObject(JSONObject src) throws JSONException {
		map = new HashMap<String, Integer>();
		JSONArray jsonKeys = src.getJSONArray("keyArray");
		JSONArray jsonValues = src.getJSONArray("valueArray");
		for (int i = 0; i < jsonKeys.length(); i++) {
			map.put((String) jsonKeys.get(i), (Integer) jsonValues.get(i));
		}
		timestamp = src.getString("timestamp");
		total = 0;
		for (String key : map.keySet()) {
			total += map.get(key).intValue();
		}
	}

	private void readFromParcel(Parcel in) {
		map = new HashMap<String, Integer>();
		Bundle temp = in.readBundle();
		for (String key : temp.keySet()) {
			map.put(key, (Integer) temp.get(key));
		}
		total = in.readInt();
		timestamp = in.readString();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		Bundle b = new Bundle();
		for (Entry<String, Integer> e : map.entrySet()) {
			b.putInt(e.getKey(), e.getValue());
		}
		dest.writeBundle(b);
		dest.writeInt(total);
		dest.writeString(timestamp);
	}

	@SuppressWarnings("rawtypes")
	public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
		public Data createFromParcel(Parcel in) {
			return new Data(in);
		}

		public Data[] newArray(int size) {
			return new Data[size];
		}
	};

	public String toString() {
		return timestamp;
	}

	public String getCSVasString() {
		String csv = "";
		csv += "Name";
		csv += ',';
		csv += "Count";
		csv += '\n';

		for (String key : map.keySet()) {
			csv += key;
			csv += ',';
			csv += "" + map.get(key);
			csv += '\n';
		}
		return csv;

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((map == null) ? 0 : map.hashCode());
		result = prime * result
				+ ((timestamp == null) ? 0 : timestamp.hashCode());
		result = prime * result + total;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Data))
			return false;
		Data other = (Data) obj;
		if (map == null) {
			if (other.map != null)
				return false;
		} else if (!map.equals(other.map))
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		if (total != other.total)
			return false;
		return true;
	}
	
	
}
