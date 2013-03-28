package edu.sjsu.hemepathcounter;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;
import edu.sjsu.hemepathcounter.adapter.DisplayDataAdapter;
import edu.sjsu.hemepathcounter.model.Data;

public class DisplayDataActivity extends Activity {
	private static final String TAG = "DisplayDataActivity";
	private Data mData;
	private DisplayDataAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_data);

		// Loading data from CountingActivity or DataActivity
		mData = getIntent().getParcelableExtra("data");
		Log.d(TAG, "Displaying data with timestamp: " + mData.getTimestamp());
		
		mAdapter = new DisplayDataAdapter(this, mData);

		ListView listview = (ListView) findViewById(R.id.display_data_activity_listview);
		listview.setAdapter(mAdapter);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_data, menu);
		return true;
	}

}
