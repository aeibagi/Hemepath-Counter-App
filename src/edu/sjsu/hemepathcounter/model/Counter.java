package edu.sjsu.hemepathcounter.model;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

public class Counter implements Parcelable {
	private ArrayList<CellButton> buttons;
	private int total;
	private String name;
	private int numUses;

	public Counter(String inName, ArrayList<CellButton> inButtons) {
		this.name = inName;
		this.buttons = inButtons;
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

	public Counter(Parcel in) {
		readFromParcel(in);
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
}
