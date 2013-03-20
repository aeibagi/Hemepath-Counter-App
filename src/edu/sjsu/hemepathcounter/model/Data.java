package edu.sjsu.hemepathcounter.model;

import java.util.HashMap;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Paint.Join;

public class Data implements JSONable {
	private HashMap<String, Integer> map;
	private int total;

	public Data(HashMap<String, Integer> inMap) {
		map = inMap;
		total = 0;
	}

	public Data() {
		map = new HashMap<String, Integer>();
		total = 0;
	}
	
	public HashMap<String, Integer> getMap() {
		return map;
	}
	public int getTotal() {
		return total;
	}

	@Override
	public JSONObject toJSONObject() throws JSONException {
		JSONObject jo = new JSONObject();
		for (String key : map.keySet()) {
			jo.put(key, map.get(key).intValue());
		}
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
		total = 0;
		for (String key : map.keySet()) {
			total += map.get(key).intValue();
		}
	}
}
