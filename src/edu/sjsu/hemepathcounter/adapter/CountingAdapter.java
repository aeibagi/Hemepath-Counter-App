package edu.sjsu.hemepathcounter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import edu.sjsu.hemepathcounter.R;
import edu.sjsu.hemepathcounter.model.CellData;
import edu.sjsu.hemepathcounter.model.CountingData;

public class CountingAdapter extends BaseAdapter {

	public CountingAdapter(Context context, CountingData data) {
		mContext = context;
		mData = data;
	}
	
	@Override
	public int getCount() {
		return mData.cells.size();		
	}

	@Override
	public CellData getItem(int position) {		
		return mData.cells.get(position);
	}

	@Override
	public long getItemId(int arg0) {		
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.cell_gridview_item, null);

            holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.number = (TextView) convertView.findViewById(R.id.number);
            holder.percent = (TextView) convertView.findViewById(R.id.percent);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        
        CellData cell = getItem(position);
        holder.name.setText(cell.name);
        holder.number.setText(cell.number + "");        
        //holder.percent.setText(cell.name / mData.);
        
        convertView.setBackgroundResource(R.color.red);
        
		return convertView;
	}
	
	private Context mContext;
	private CountingData mData;
	
	private class ViewHolder {
        private TextView name;
        private TextView number;
        private TextView percent;
    }
}
