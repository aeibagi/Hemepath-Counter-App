package edu.sjsu.hemepathcounter.adapter;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.DataSetObserver;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;
import edu.sjsu.hemepathcounter.NewCounterActivity;
import edu.sjsu.hemepathcounter.R;
import edu.sjsu.hemepathcounter.model.CellButton;
import edu.sjsu.hemepathcounter.model.Parent;

public class MyCustomAdapterForExpandableList extends BaseExpandableListAdapter {
 
 
    private LayoutInflater inflater;
    private ArrayList<Parent> mParent;
    private Button preview_button;
    private MediaPlayer myMediaPlayer;
    private Context mContext;
    
    public MyCustomAdapterForExpandableList(Context context, ArrayList<Parent> parent){
        mParent = parent;
        inflater = LayoutInflater.from(context);
        mContext = context;
    }
 

    @Override
    //counts the number of group/parent items so the list knows how many times calls getGroupView() method
    public int getGroupCount() {
        return mParent.size();
    }
 
    @Override
    //counts the number of children items so the list knows how many times calls getChildView() method
    public int getChildrenCount(int i) {
        return mParent.get(i).getArrayChildren().size();
    }
 
    @Override
    //gets the title of each parent/group
    public Object getGroup(int i) {
        return mParent.get(i).getTitle();
    }
 
    @Override
    //gets the name of each item
    public Object getChild(int i, int i1) {
        return mParent.get(i).getArrayChildren().get(i1);
    }
 
    @Override
    public long getGroupId(int i) {
        return i;
    }
 
    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }
 
    @Override
    public boolean hasStableIds() {
        return true;
    }
 
    @Override
    //in this method you must set the text to see the parent/group on the list
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
 
        if (view == null) {
            view = inflater.inflate(R.layout.list_item_parent_newcounterexpandable, viewGroup,false);
        }
 
        TextView textView = (TextView) view.findViewById(R.id.list_item_text_view);
        //"i" is the position of the parent/group in the list
        textView.setText(getGroup(i).toString());
 
        //return the entire view
        return view;
    }
 
    @SuppressLint("NewApi")
	@Override
    //in this method you must set the text to see the children on the list
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = inflater.inflate(R.layout.list_item_child_newcounterexpandable, viewGroup,false);
        }
 
        preview_button = (Button) view.findViewById(R.id.button_preview);
        
        
        CellButton keyName = mParent.get(i).getArrayChildren().get(i1);
        if(!NewCounterActivity.ChildStatus.isEmpty())
        {
        	if(NewCounterActivity.ChildStatus.containsKey(keyName))
        	{
		        if(NewCounterActivity.ChildStatus.get(keyName) == true)
		        {
		        	view.setBackgroundResource(R.drawable.button_style_green);
		        }
		        else
		        {
					view.setBackground(NewCounterActivity.listViewdefaultbackground);
					view.setBackground(null);
		        }
        	}
        	else
        	{
        		view.setBackground(NewCounterActivity.listViewdefaultbackground);
				view.setBackground(null);
        	}
        }
        TextView textView = (TextView) view.findViewById(R.id.list_item_text_child);
        //"i" is the position of the parent/group in the list and
        //"i1" is the position of the child
        textView.setText(mParent.get(i).getArrayChildren().get(i1).getName());
 
        preview_button.setText(mParent.get(i).getArrayChildren().get(i1).getAbbr());
        preview_button.setBackgroundResource(mParent.get(i).getArrayChildren().get(i1).getColor());
        final int pos1 = i;
        final int pos2 = i1;
        preview_button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(myMediaPlayer != null)
				{
					myMediaPlayer.release();
				}
				myMediaPlayer = MediaPlayer.create(
						mContext,
						mParent.get(pos1).getArrayChildren().get(pos2).getSound());
				
				myMediaPlayer.start();
				
			}
		});
        
        //return the entire view
        return view;
    }
 
    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
 
    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
        /* used to make the notifyDataSetChanged() method work */
        super.registerDataSetObserver(observer);
    }


	@Override
	public void onGroupExpanded(int groupPosition) {

		super.onGroupExpanded(groupPosition);
	}
}