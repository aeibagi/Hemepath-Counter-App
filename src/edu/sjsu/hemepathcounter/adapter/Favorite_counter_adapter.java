package edu.sjsu.hemepathcounter.adapter;
import java.util.ArrayList;


import edu.sjsu.hemepathcounter.R;
import edu.sjsu.hemepathcounter.model.Counter;
import edu.sjsu.hemepathcounter.model.CounterHolder;
import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class Favorite_counter_adapter extends BaseAdapter{

	private LayoutInflater inflater;
	private ArrayList<Counter> mFavCounter;
	private Context mContext;

	
	public Favorite_counter_adapter(Context context, ArrayList<Counter> FavCounter)
	{
		mFavCounter = FavCounter;
		mContext = context;
		inflater = LayoutInflater.from(context);
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mFavCounter.size();
	}

	@Override
	public Object getItem(int index) {
		return mFavCounter.get(index);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void clearData()
	{
		mFavCounter.clear();
	}
	public void setData(ArrayList<Counter> data)
	{
		mFavCounter = data;
	}
	@Override
	public View getView(int i, View view, ViewGroup viewGroup) {
		
		if (view == null) {
            view = inflater.inflate(R.layout.list_item_favorite_counter, viewGroup,false);
        }
 
              
        TextView textView = (TextView) view.findViewById(R.id.list_item_favorite_counter);
        //"i" is the position of the parent/group in the list and
        //"i1" is the position of the child
        textView.setText(mFavCounter.get(i).getName());
        
		return view;
	}


}
