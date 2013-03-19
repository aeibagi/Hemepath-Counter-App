package edu.sjsu.hemepathcounter.model;


import android.os.Parcel;
import android.os.Parcelable;

public class CellButton implements Parcelable {
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

	public CellButton(Parcel in) {
		readFromParcel(in);
		count = 0;
	}

	@SuppressWarnings("deprecation")
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
}
