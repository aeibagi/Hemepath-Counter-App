package edu.sjsu.hemepathcounter.adapter;

import java.text.DecimalFormat;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import edu.sjsu.hemepathcounter.PreferencesActivity;
import edu.sjsu.hemepathcounter.R;
import edu.sjsu.hemepathcounter.model.CellButton;
import edu.sjsu.hemepathcounter.model.Counter;

public class CountingAdapter extends BaseAdapter {

	private SharedPreferences preferences;
	private boolean showNumbers;
	private boolean showPercents;
	private boolean muted;

	public CountingAdapter(Context context, Counter data) {
		mContext = context;
		mData = data;
		preferences = PreferenceManager.getDefaultSharedPreferences(context); 
		showNumbers = preferences
				.getBoolean(PreferencesActivity.SHOW_NUM, true);
		showPercents = preferences.getBoolean(PreferencesActivity.SHOW_PERCENT,
				true);

	}

	@Override
	public int getCount() {
		return mData.getCells().size();
	}

	@Override
	public CellButton getItem(int position) {
		return mData.getCells().get(position);
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.cell_gridview_item, null);

			holder = new ViewHolder();
			holder.name = (TextView) convertView.findViewById(R.id.name);
			holder.number = (TextView) convertView.findViewById(R.id.number);
			holder.percent = (TextView) convertView.findViewById(R.id.percent);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		CellButton cell = getItem(position);
		holder.name.setText(cell.getName());
		if (showNumbers) {
			holder.number.setText(cell.getCount() + "");
		}else{
			holder.number.setText("");
		}
		DecimalFormat df = new DecimalFormat("#.##");
		if (showPercents) {
			if (mData.getTotal() == 0)
				holder.percent.setText("0%");
			else
				holder.percent.setText(String.format(df.format(100.0
						* cell.getCount() / mData.getTotal()))
						+ "%");
		}else{
			holder.percent.setText("");
		}
		convertView.setBackgroundResource(cell.getColor());

		return convertView;
	}

	private Context mContext;
	private Counter mData;

	private class ViewHolder {
		private TextView name;
		private TextView number;
		private TextView percent;
	}
}
