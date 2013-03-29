package edu.sjsu.hemepathcounter.adapter;

import java.text.DecimalFormat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import edu.sjsu.hemepathcounter.R;
import edu.sjsu.hemepathcounter.model.CellButton;
import edu.sjsu.hemepathcounter.model.Data;

public class DisplayDataAdapter extends BaseAdapter {
	
	public DisplayDataAdapter(Context context, Data data) {
		mContext = context;
		mData = data;
		mKeys = mData.getMap().keySet().toArray(new String[0]);
	}

	@Override
	public int getCount() {
		return mKeys.length;
	}

	@Override
	public CellButton getItem(int position) {
		String key = mKeys[position]; //reserve the order
		CellButton cell = new CellButton(key, "", -1, -1);
		cell.setCount(mData.getMap().get(key));
		return cell;
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.cell_listview_item, null);

			holder = new ViewHolder();
			holder.name = (TextView) convertView.findViewById(R.id.display_data_activity_listview_name);
			holder.number = (TextView) convertView.findViewById(R.id.display_data_activity_listview_number);
			holder.percent = (TextView) convertView.findViewById(R.id.display_data_activity_listview_percent);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		CellButton cell = getItem(position);
		holder.name.setText(cell.getName());
		holder.number.setText(cell.getCount() + "");
		
		DecimalFormat df = new DecimalFormat("#.##");
		holder.percent.setText(String.format(df.format(100.0 * cell.getCount() / mData.getTotal())) + "%");
		
		//convertView.setBackgroundResource(cell.getColor());

		return convertView;
	}

	private Context mContext;
	private Data mData;
	private String[] mKeys;
		
	private class ViewHolder {
		private TextView name;
		private TextView number;
		private TextView percent;
	}
}
