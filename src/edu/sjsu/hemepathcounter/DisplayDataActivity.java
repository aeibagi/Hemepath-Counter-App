package edu.sjsu.hemepathcounter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import edu.sjsu.hemepathcounter.adapter.DisplayDataAdapter;
import edu.sjsu.hemepathcounter.model.Data;

public class DisplayDataActivity extends Activity implements View.OnClickListener {
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
		
		//Set adapter for the listview
		mAdapter = new DisplayDataAdapter(this, mData);		
		ListView listview = (ListView) findViewById(R.id.display_data_activity_listview);
		listview.setAdapter(mAdapter);
		
		//Set up button
		Button btnMain = (Button) findViewById(R.id.display_data_activity_main);
		Button btnExport = (Button) findViewById(R.id.display_data_activity_export);
		btnMain.setOnClickListener(this);
		btnExport.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_data, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		Intent intent;
		switch (v.getId()) {
			case R.id.display_data_activity_main: 
				Log.d(TAG, "Main Menu Button clicked.");
				finish();
				intent = new Intent(DisplayDataActivity.this, MainActivity.class);
				startActivity(intent);		
				break;
			case R.id.display_data_activity_export: 
				try {
					Data exportData = mData;
					FileOutputStream openFileOutput = openFileOutput(
							exportData.hashCode() + ".csv",
							Context.MODE_WORLD_READABLE);
					openFileOutput.write(exportData.getCSVasString().getBytes());
					openFileOutput.flush();
					openFileOutput.close();

					File path = getFileStreamPath(exportData.hashCode() + ".csv");

					intent = new Intent(Intent.ACTION_SEND);
					intent.setType("plain/text");
					intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(path));
					intent.putExtra(
							Intent.EXTRA_EMAIL,
							new String[] { PreferenceManager
									.getDefaultSharedPreferences(this).getString(
											PreferencesActivity.EMAIL, "NOT SET") });
					intent.putExtra(Intent.EXTRA_SUBJECT, "Hemepath Counter App Data");
					intent.putExtra(
							Intent.EXTRA_TEXT,
							"Here is the data that you exported. There should be one file attached to this email. It will be of the type \".csv\"");
					startActivity(Intent.createChooser(intent, "E-mail"));

				} catch (IOException e) {
					Log.e(TAG, "Exporting to email failed", e);
				}
				break;
			default:
				break;
		}
		
	}

}
