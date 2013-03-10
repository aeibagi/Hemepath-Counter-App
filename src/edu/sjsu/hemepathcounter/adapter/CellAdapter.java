package edu.sjsu.hemepathcounter.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import edu.sjsu.hemepathcounter.model.CounterData;

public class CellAdapter extends BaseAdapter {

	public CellAdapter(Context context, CounterData data) {
		mContext = context;
		mData = data;
	}
	
	@Override
	public int getCount() {
		
		return 0;
	}

	@Override
	public Object getItem(int position) {		
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private Context mContext;
	private CounterData mData;
	
	private class ViewHolder {
        private TextView name;
        private TextView number;
        private TextView percent;
    }
}
