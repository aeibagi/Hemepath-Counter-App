package edu.sjsu.hemepathcounter.adapter;

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
	}

	@Override
	public int getCount() {
		return mData.getMap().size();
	}

	@Override
	public CellButton getItem(int position) {
		CellButton cell = new CellButton();
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

		/*
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
		*/
		//convertView.setBackgroundResource(cell.getColor());


		return convertView;
	}

	private Context mContext;
	private Data mData;

	private class ViewHolder {
		private TextView name;
		private TextView number;
		private TextView percent;
	}
}
