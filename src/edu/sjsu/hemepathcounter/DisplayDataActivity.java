package edu.sjsu.hemepathcounter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import edu.sjsu.hemepathcounter.adapter.DisplayDataAdapter;
import edu.sjsu.hemepathcounter.model.Data;

public class DisplayDataActivity extends Activity implements
		View.OnClickListener {
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

		// Set adapter for the listview
		mAdapter = new DisplayDataAdapter(this, mData);
		ListView listview = (ListView) findViewById(R.id.display_data_activity_listview);
		listview.setAdapter(mAdapter);

		// if the M:E ratio is valid for this Data
		if (mData.isMEratioValid()) {
			// Set MERatio
			TextView MEratio = (TextView) findViewById(R.id.MEratioView);
			MEratio.setText("ME Ratio = "
					+ new DecimalFormat("#.##").format(mData.getMEratio())
					+ ":1");
		}
		// Set up button
		Button btnMain = (Button) findViewById(R.id.display_data_activity_main);
		Button btnExport = (Button) findViewById(R.id.display_data_activity_export);
		btnMain.setOnClickListener(this);
		btnExport.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.display_data_activity_main:
			Log.d(TAG, "Main Menu Button clicked.");
			finish();
			Intent intent = new Intent(DisplayDataActivity.this,
					MainActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			break;
		case R.id.display_data_activity_export:
			Log.d(TAG, "Export button clicked");

			// check if exporting is enabled.
			if (PreferenceManager.getDefaultSharedPreferences(this).getBoolean(
					PreferencesActivity.EXPORT, false)) {
				Log.d(TAG, "Export enabled. Exporting now.");
				exportData();
			} else {
				Log.d(TAG, "Export disabled.");
				AlertDialog exportDisabledDialogBox = createExportDisabledDialogBox();
				exportDisabledDialogBox.show();
			}
			break;
		default:
			break;
		}

	}

	private AlertDialog createExportDisabledDialogBox() {
		Log.d(TAG, "Creating export disabled dialog box.");
		AlertDialog myExportDisabledDialogBox = new AlertDialog.Builder(this)
				.setTitle("Export Disabled")
				.setMessage(
						"Exporting data is currently disabled. You can change this in your preferences. Would you like to go there now?")
				.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int whichButton) {
								Log.d(TAG, "Going to preferences.");
								Intent i = new Intent(DisplayDataActivity.this,
										PreferencesActivity.class);
								startActivity(i);
								dialog.dismiss();
							}

						})

				.setNegativeButton("No", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						Log.d(TAG, "NOT going to preferences.");
						dialog.dismiss();
					}
				}).create();
		return myExportDisabledDialogBox;
	}

	private void exportData() {
		try {
			Data exportData = mData;
			FileOutputStream openFileOutput = openFileOutput(
					exportData.getTimestamp() + ".csv",
					Context.MODE_WORLD_READABLE);
			openFileOutput.write(exportData.getCSVasString().getBytes());
			openFileOutput.flush();
			openFileOutput.close();

			File path = null;
			if (PreferenceManager.getDefaultSharedPreferences(this).getBoolean(
					PreferencesActivity.TIMESTAMP, true)) {
				path = getFileStreamPath(exportData.getTimestamp() + ".csv");
			} else {
				path = getFileStreamPath(exportData.hashCode() + ".csv");
			}

			Intent intent = new Intent(Intent.ACTION_SEND);
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
	}

}
