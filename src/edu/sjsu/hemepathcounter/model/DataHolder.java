package edu.sjsu.hemepathcounter.model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DataHolder implements JSONable {

	ArrayList<Data> data = new ArrayList<Data>();

	public ArrayList<Data> getData() {
		return data;
	}

	@Override
	public JSONObject toJSONObject() throws JSONException {
		JSONObject jo = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		for (Data d : data) {
			jsonArray.put(d.toJSONObject());
		}
		jo.put("data", jsonArray);
		return jo;
	}

	@Override
	public void fromJSONObject(JSONObject src) throws JSONException {
		JSONArray jsonArray = src.getJSONArray("data");
		this.data = new ArrayList<Data>();
		for (int i = 0; i < jsonArray.length(); i++) {
			Data d = new Data();
			d.fromJSONObject(jsonArray.getJSONObject(i));
			data.add(d);
		}
	}

	public void addData(Data newData) {
		data.add(newData);
	}

}
