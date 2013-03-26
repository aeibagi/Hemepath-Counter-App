package edu.sjsu.hemepathcounter.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

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
		for (String key : map.keySet()) {
			jo.put(key, map.get(key).intValue());
		}
		jo.put("timestamp", timestamp);
		return jo;
	}

	@Override
	public void fromJSONObject(JSONObject src) throws JSONException {
		map = new HashMap<String, Integer>();
		@SuppressWarnings("unchecked")
		Iterator<String> keys = src.keys();
		while (keys.hasNext()) {
			String key = keys.next();
			map.put(key, src.getInt(key));
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
}
