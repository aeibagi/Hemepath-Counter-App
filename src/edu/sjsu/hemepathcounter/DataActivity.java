package edu.sjsu.hemepathcounter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import edu.sjsu.hemepathcounter.model.Data;
import edu.sjsu.hemepathcounter.model.DataHolder;

@SuppressLint("WorldReadableFiles")
public class DataActivity extends Activity implements OnClickListener,
		OnItemClickListener, OnItemLongClickListener {
	private static final String TAG = "DataActivity";
	private EditText DataSearchBox;
	private ArrayAdapter<Data> DataListAdaptor;
	private ListView DataList;
	private DataHolder holder;
	private FileManager manager;
	private Button exportButton, viewButton;
	private Data ItemSelectedforContextMenuOption;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "Starting new Data Activity.");
		setContentView(R.layout.activity_data);
		setTitle("View & Export Results");
		initialize();
		SetupSearchFilter();
	}

	private void initialize() {

		manager = FileManager.getInstance(getApplicationContext());
		holder = manager.getDataHolder();
		// initialize the Search box
		DataSearchBox = (EditText) findViewById(R.id.EditBox_Data_Search);
		// setting up the list for the counters
		DataList = (ListView) findViewById(R.id.Data_list);
		DataList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		DataListAdaptor = new ArrayAdapter<Data>(this,
				android.R.layout.simple_list_item_multiple_choice,
				holder.getData());
		DataList.setAdapter(DataListAdaptor);
		DataList.requestFocus();

		// Set list listeners
		DataList.setOnItemLongClickListener(this);
		DataList.setOnItemClickListener(this);

		// Set button listeners
		exportButton = (Button) findViewById(R.id.export_multiple_data_button);
		exportButton.setBackgroundResource(R.drawable.button_style_gray);
		exportButton.setOnClickListener(this);
		viewButton = (Button) findViewById(R.id.view_data_button);
		viewButton.setBackgroundResource(R.drawable.button_style_gray);
		viewButton.setOnClickListener(this);

	}

	private void SetupSearchFilter() {
		DataSearchBox.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				DataActivity.this.DataListAdaptor.getFilter().filter(s);
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View v, int position,
			long id) {
		Log.d(TAG, "Data selected with long-click.");
		ItemSelectedforContextMenuOption = (Data) DataList
				.getItemAtPosition(position);
		registerForContextMenu(arg0);
		return false;
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.setHeaderTitle("Options");
		menu.add(0, v.getId(), 0, "View");
		menu.add(0, v.getId(), 0, "Delete");
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		if (item.getTitle() == "View") {
			AlertDialog viewDialogBox = createViewDialogBox();
			viewDialogBox.show();
		} else if (item.getTitle() == "Delete") {
			AlertDialog deleteDialogBox = createDeleteDialogBox();
			deleteDialogBox.show();
		}
		return true;
	}

	private AlertDialog createViewDialogBox() {
		Log.d(TAG, "Creating view dialog.");
		AlertDialog myViewingDialogBox = new AlertDialog.Builder(this)
				// set message, title, and icon
				.setTitle("View")
				.setMessage(
						"Do you want to view the data created at "
								+ ItemSelectedforContextMenuOption + "?")
				.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int whichButton) {
								Log.d(TAG, "Viewing data.");
								dialog.dismiss();
								Intent intent = new Intent(DataActivity.this,
										DisplayDataActivity.class);
								intent.putExtra("data",
										ItemSelectedforContextMenuOption);
								startActivity(intent);
							}

						})

				.setNegativeButton("No", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						Log.d(TAG, "NOT Viewing data.");
						dialog.dismiss();
					}
				}).create();
		return myViewingDialogBox;
	}

	private AlertDialog createDeleteDialogBox() {
		Log.d(TAG, "Creating delete dialog box.");
		AlertDialog myDeletingDialogBox = new AlertDialog.Builder(this)
				// set message, title, and icon
				.setTitle("Delete")
				.setMessage(
						"Do you want to Delete "
								+ ItemSelectedforContextMenuOption + "?")
				.setIcon(R.drawable.delete_icon)

				.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int whichButton) {
								Log.d(TAG, "Deleting Data.");
								updateAdaptorListForRemove(ItemSelectedforContextMenuOption);
								dialog.dismiss();
							}

						})

				.setNegativeButton("No", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						Log.d(TAG, "NOT deleting Data.");
						dialog.dismiss();
					}
				}).create();
		return myDeletingDialogBox;
	}

	protected void updateAdaptorListForRemove(Data itemToRemove) {
		Log.d(TAG, "Updating Adaptor.");
		holder.remove(itemToRemove);
		manager.updateDataHolder(holder);
		DataListAdaptor.remove(itemToRemove);
		DataListAdaptor.notifyDataSetChanged();

	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View v, int position, long id) {
		// Gray out view button depending on how many items are selected.

		int count = DataList.getCheckedItemCount();

		Log.v(TAG, "Number of checked items might be : " + count);
		if (count == 0) {
			Log.d(TAG, "No item selected.");
			viewButton.setBackgroundResource(R.drawable.button_style_gray);
			viewButton.setClickable(false);

			exportButton.setBackgroundResource(R.drawable.button_style_gray);
			exportButton.setClickable(false);
		} else if (count == 1) {
			Log.d(TAG, "Only one item selected.");
			viewButton.setBackgroundResource(R.drawable.button_style_green);
			viewButton.setClickable(true);

			exportButton.setBackgroundResource(R.drawable.button_style_green);
			exportButton.setClickable(true);
		} else {
			Log.d(TAG, "More than one item selected.");
			viewButton.setBackgroundResource(R.drawable.button_style_gray);
			viewButton.setClickable(false);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.view_data_button:
			Log.d(TAG, "View button clicked");
			// Find selected data
			int len = DataList.getCount();
			if (len == 0) {
				Toast.makeText(this, "You don't have any data saved",
						Toast.LENGTH_SHORT).show();
			} else {
				SparseBooleanArray checked = DataList.getCheckedItemPositions();
				if (checked.size() != 0) {
					Data viewData = null;
					for (int i = 0; i < len; i++)
						if (checked.get(i)) {
							viewData = (Data) DataList.getItemAtPosition(i);
						}

					// Start other activity.
					Intent intent = new Intent(DataActivity.this,
							DisplayDataActivity.class);
					intent.putExtra("data", viewData);
					intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(intent);
					finish();
				} else {
					Toast.makeText(this,
							"Select data from the list above to view.",
							Toast.LENGTH_SHORT).show();
				}
			}
			break;
		case R.id.export_multiple_data_button:
			Log.d(TAG, "Export button clicked");
			// check if any data is selected data is selected.
			SparseBooleanArray checked = DataList.getCheckedItemPositions();
			if (checked.size() != 0) {
				// check if exporting is enabled.
				if (PreferenceManager.getDefaultSharedPreferences(this)
						.getBoolean(PreferencesActivity.EXPORT, false)) {
					Log.d(TAG, "Export enabled. Exporting now.");
					exportSelected();
				} else {
					Log.d(TAG, "Export disabled.");
					AlertDialog exportDisabledDialogBox = createExportDisabledDialogBox();
					exportDisabledDialogBox.show();
				}

			}
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
								Intent i = new Intent(DataActivity.this,
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

	@SuppressWarnings("deprecation")
	private void exportSelected() {
		// Check how many attachments we are trying to do.
		if (DataList.getCheckedItemCount() == 1) {
			Log.d(TAG, "Exporting single Data Object.");

			// Find selected data
			int len = DataList.getCount();
			// if (len == 0) return ;
			SparseBooleanArray checked = DataList.getCheckedItemPositions();
			Data exportData = null;
			for (int i = 0; i < len; i++)
				if (checked.get(i)) {
					exportData = (Data) DataList.getItemAtPosition(i);
				}
			try {
				FileOutputStream openFileOutput = openFileOutput(
						exportData.getTimestamp() + ".csv",
						Context.MODE_WORLD_READABLE);
				openFileOutput.write(exportData.getCSVasString().getBytes());
				openFileOutput.flush();
				openFileOutput.close();

				File path = null;
				if (PreferenceManager.getDefaultSharedPreferences(this)
						.getBoolean(PreferencesActivity.TIMESTAMP, true)) {
					path = getFileStreamPath(exportData.getTimestamp() + ".csv");
				} else {
					path = getFileStreamPath(exportData.hashCode() + ".csv");
				}

				Intent i = new Intent(Intent.ACTION_SEND);
				i.setType("plain/text");
				i.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(path));
				i.putExtra(
						Intent.EXTRA_EMAIL,
						new String[] { PreferenceManager
								.getDefaultSharedPreferences(this).getString(
										PreferencesActivity.EMAIL, "NOT SET") });
				i.putExtra(Intent.EXTRA_SUBJECT, "Hemepath Counter App Data");
				i.putExtra(
						Intent.EXTRA_TEXT,
						"Here is the data that you exported. There should be one file attached to this email. It will be of the type \".csv\"");
				startActivity(Intent.createChooser(i, "E-mail"));

			} catch (IOException e) {
				Log.e(TAG, "Exporting to email failed", e);
			}

		} else {
			Log.d(TAG, "Exporting multiple Data Objects.");
			// get selected Data
			ArrayList<Data> selectedData = new ArrayList<Data>();
			SparseBooleanArray checked = DataList.getCheckedItemPositions();
			for (int i = 0; i < checked.size(); i++) {
				if (checked.get(i)) {
					selectedData.add((Data) DataList.getItemAtPosition(i));
				}
			}
			try {
				// Get all data into files and put files in List of Uri
				ArrayList<Uri> uris = new ArrayList<Uri>();
				for (Data d : selectedData) {
					FileOutputStream openFileOutput = openFileOutput(
							d.getTimestamp() + ".csv",
							Context.MODE_WORLD_READABLE);
					openFileOutput.write(d.getCSVasString().getBytes());
					openFileOutput.flush();
					openFileOutput.close();
					File path = null;
					if (PreferenceManager.getDefaultSharedPreferences(this)
							.getBoolean(PreferencesActivity.TIMESTAMP, true)) {
						path = getFileStreamPath(d.getTimestamp() + ".csv");
					} else {
						path = getFileStreamPath(d.hashCode() + ".csv");
					}
					uris.add(Uri.fromFile(path));
				}
				Intent i = new Intent(Intent.ACTION_SEND_MULTIPLE);
				i.setType("plain/text");
				i.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uris);
				i.putExtra(
						Intent.EXTRA_EMAIL,
						new String[] { PreferenceManager
								.getDefaultSharedPreferences(this).getString(
										PreferencesActivity.EMAIL, "NOT SET") });
				i.putExtra(Intent.EXTRA_SUBJECT, "Hemepath Counter App Data");
				i.putExtra(
						Intent.EXTRA_TEXT,
						"Here is the data that you exported. There should be "
								+ selectedData.size()
								+ " files attached to this email. They will be of the type \".csv\"");
				startActivity(Intent.createChooser(i, "E-mail"));
			} catch (IOException e) {
				Log.e(TAG, "Exporting to email failed", e);
			}
		}
	}

	@Override
	protected void onDestroy() {
		// Clear out the data files used to export. This helps reduce space used
		// by the app.
		super.onDestroy();
		Log.d(TAG, "Clearing out the data files used to export.");
		ArrayList<String> possibleFileNames = new ArrayList<String>();
		for (Data d : holder.getData()) {
			possibleFileNames.add(d.getTimestamp() + ".csv");
			possibleFileNames.add(d.hashCode() + ".csv");
		}
		for (File actualFile : getFilesDir().listFiles()) {
			if (possibleFileNames.contains(actualFile.getName())) {
				Log.d(TAG, "Deleting file: " + actualFile.getName());
				actualFile.delete();
			}
		}
	}

}
