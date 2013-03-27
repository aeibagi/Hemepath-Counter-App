package edu.sjsu.hemepathcounter.adapter;

import java.util.ArrayList;

import edu.sjsu.hemepathcounter.NewCounterActivity;
import edu.sjsu.hemepathcounter.R;
import edu.sjsu.hemepathcounter.model.CellButton;
import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class modify_counter_adaptor extends BaseAdapter{

	private LayoutInflater inflater;
	private ArrayList<CellButton> mCellData;
	private Context mContext;
    private Button preview_button;
    private MediaPlayer myMediaPlayer;
	
	public modify_counter_adaptor(Context context, ArrayList<CellButton> cellButtons)
	{
		mCellData = cellButtons;
		mContext = context;
		inflater = LayoutInflater.from(context);
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mCellData.size();
	}

	@Override
	public Object getItem(int index) {
		return mCellData.get(index);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void clearData()
	{
		mCellData.clear();
	}
	public void setData(ArrayList<CellButton> data)
	{
		mCellData = data;
	}
	@Override
	public View getView(int i, View view, ViewGroup viewGroup) {
		
		if (view == null) {
            view = inflater.inflate(R.layout.list_item_child_newcounterexpandable, viewGroup,false);
        }
 
        preview_button = (Button) view.findViewById(R.id.button_preview);
        
        
        TextView textView = (TextView) view.findViewById(R.id.list_item_text_child);
        //"i" is the position of the parent/group in the list and
        //"i1" is the position of the child
        textView.setText(mCellData.get(i).getName());
 
        preview_button.setText(textView.getText().toString());
        preview_button.setBackgroundResource(mCellData.get(i).getColor());
        final int pos1 = i;

        preview_button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(myMediaPlayer != null)
				{
					myMediaPlayer.release();
				}
				myMediaPlayer = MediaPlayer.create(
						mContext,
						mCellData.get(pos1).getSound());
				
				myMediaPlayer.start();
				
			}
		});
        
		return view;
	}


}
