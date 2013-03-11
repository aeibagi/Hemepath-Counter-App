package edu.sjsu.hemepathcounter;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;
import edu.sjsu.hemepathcounter.adapter.CountingAdapter;
import edu.sjsu.hemepathcounter.model.CountingData;

public class CountingActivity extends Activity {

	private CountingData mData;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_counting);
		
		//Loading data from database depends New Counting or Favorites
		mData = new CountingData();
		
		GridView gridview = (GridView) findViewById(R.id.gridview);
	    gridview.setAdapter(new CountingAdapter(this, mData));

	    
	    gridview.setOnItemClickListener(new OnItemClickListener() {
	        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
	            Toast.makeText(CountingActivity.this, "" + position, Toast.LENGTH_SHORT).show();
	        }
	    });
	    
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.counting, menu);
		return true;
	}

}
