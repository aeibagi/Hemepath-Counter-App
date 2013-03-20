package edu.sjsu.hemepathcounter.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CounterHolder implements JSONable{
	private static final int NUM_FAVORTIES = 4;
	ArrayList<Counter> counters;

	public CounterHolder(ArrayList<Counter> count) {
		counters = count;
	}

	public CounterHolder() {
		counters = new ArrayList<Counter>();
	}

	/**
	 * 
	 * @return All of the saved counters in alphabetical order by name
	 */
	public ArrayList<Counter> getCounters() {
		Collections.sort(counters, new Comparator<Counter>() {

			@Override
			public int compare(Counter lhs, Counter rhs) {
				return lhs.getName().compareTo(rhs.getName());
			}
		});
		return counters;
	}

	public void addCounter(Counter newCounter) {
		counters.add(newCounter);
	}

	/**
	 * 
	 * @return the top NUM_FAVORITES counters.
	 */
	public ArrayList<Counter> getFavoriteCounters() {
		Collections.sort(counters, new Comparator<Counter>() {

			@Override
			public int compare(Counter lhs, Counter rhs) {
				return lhs.getNumUses().compareTo(rhs.getNumUses());
			}
		});
		ArrayList<Counter> favorites = new ArrayList<Counter>();
		for (int i = 0; i < NUM_FAVORTIES && i<counters.size(); i++) {
			favorites.add(i, counters.get(i));
		}
		return favorites;
	}

	public void remove(Counter counterToRemove) {
		counters.remove(counters.indexOf(counterToRemove));
	}

	public void renameCounter(Counter itemSelectedforContextMenuOption,
			String newName) {
		counters.get(counters.indexOf(itemSelectedforContextMenuOption))
				.setName(newName);

	}

	public void incrementCounterUse(Counter selected) {
		counters.get(counters.indexOf(selected)).incrementUses();
	}

	@Override
	public JSONObject toJSONObject() throws JSONException {
		JSONObject jo = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		for (Counter c : counters) {
			jsonArray.put(c.toJSONObject());
		}
		jo.put("counters", jsonArray);
		return jo;
	}

	@Override
	public void fromJSONObject(JSONObject src) throws JSONException {
		JSONArray jsonArray = src.getJSONArray("counters");
		this.counters = new ArrayList<Counter>();
		for(int i = 0; i<jsonArray.length();i++){
			Counter c =  new Counter();
			c.fromJSONObject(jsonArray.getJSONObject(i));
			counters.add(c);
		}
	}

}
