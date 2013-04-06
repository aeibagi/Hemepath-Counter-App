package edu.sjsu.hemepathcounter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import edu.sjsu.hemepathcounter.model.Counter;
import edu.sjsu.hemepathcounter.model.CounterHolder;

/**
 * 
 * @author Amir Eibagi
 * @author Jake Karnes
 * 
 */
public class CountersActivity extends Activity implements
		AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener, View.OnClickListener {
	private static final String TAG = "CountersActivity";
	private ListView CountersList;
	private ArrayAdapter<Counter> CounterListAdaptor;
	private EditText CountersSearchBox;
	private Counter ItemSelectedforContextMenuOption = null;
	private CounterHolder holder;
	private FileManager manager;
	private Button btn_MainMenu, btn_NewCounter, btn_Displayresult;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "Starting new Counters Activity.");
		setContentView(R.layout.activity_counters);
		initialize();
		SetupSearchFilter();

	}

	private void initialize() {
		manager = FileManager.getInstance(getApplicationContext());
		holder = manager.getCounterHolder();
		// initialize the Search box
		CountersSearchBox = (EditText) findViewById(R.id.EditBox_Counters_Search);
		// setting up the list for the counters
		CountersList = (ListView) findViewById(R.id.Counters_list);
		CounterListAdaptor = new ArrayAdapter<Counter>(this,
				R.layout.counters_list_item, R.id.TextView_Counters_List_items);

		updateCounterList();
		CountersList.requestFocus();

		CountersList.setOnItemLongClickListener(this);
		CountersList.setOnItemClickListener(this);
		
		btn_MainMenu =  (Button) findViewById(R.id.btn_counters_mainMenu);
		btn_MainMenu.setOnClickListener(this);
		btn_NewCounter = (Button) findViewById(R.id.btn_counters_New_Counter);
		btn_NewCounter.setOnClickListener(this);
		btn_Displayresult = (Button) findViewById(R.id.btn_counters_DisplayData);
		btn_Displayresult.setOnClickListener(this);
	}

	private void updateCounterList() {
		CounterListAdaptor.addAll(holder.getCounters());
		CountersList.setAdapter(CounterListAdaptor);

	}

	private void SetupSearchFilter() {
		CountersSearchBox.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				CountersActivity.this.CounterListAdaptor.getFilter().filter(s);
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
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.counters, menu);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View v, int position, long id) {
		Log.d(TAG, "Counter Selected.");
		ItemSelectedforContextMenuOption = (Counter) CountersList
				.getItemAtPosition(position);
		Intent i = new Intent(CountersActivity.this, CountingActivity.class);
		i.putExtra("counter", ItemSelectedforContextMenuOption);
		startActivity(i);
		holder.incrementCounterUse(ItemSelectedforContextMenuOption);
		manager.updateCounterHolder(holder);
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View v, int position,
			long id) {
		Log.d(TAG, "Counter selected with long-click.");
		ItemSelectedforContextMenuOption = (Counter) CountersList
				.getItemAtPosition(position);
		registerForContextMenu(arg0);
		return false;
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.setHeaderTitle("Options");
		//menu.add(0, v.getId(), 0, "Rename");
		menu.add(0, v.getId(), 0, "Delete");
		menu.add(0, v.getId(), 0, "Edit");
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		/*if (item.getTitle() == "Rename") {
			AlertDialog.Builder renameDialogBox = createRenameDialogBox();
			renameDialogBox.show();
		} else*/ if (item.getTitle() == "Delete") {
			AlertDialog deleteDialogBox = createDeleteDialogBox();
			deleteDialogBox.show();
		}
		else if (item.getTitle() == "Edit") {
			Intent i = new Intent(CountersActivity.this, ModifyCounterActivity.class);
			i.putExtra("counter", ItemSelectedforContextMenuOption);
			startActivityForResult(i, 1);
		}
		return true;
	}

	/*private AlertDialog.Builder createRenameDialogBox() {
		Log.d(TAG, "Creating rename dialog.");
		AlertDialog.Builder renameDialog = new AlertDialog.Builder(this);
		renameDialog.setTitle("Rename");
		renameDialog.setMessage("Rename  " + ItemSelectedforContextMenuOption
				+ " to:");
		renameDialog.setIcon(R.drawable.rename_icon);
		final EditText newName = new EditText(this);
		newName.setHint("Enter new counter name");
		renameDialog.setView(newName);
		renameDialog.setPositiveButton("OK",
				new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int arg1) {
						Log.d(TAG, "Saving Counters new name.");
						holder.renameCounter(ItemSelectedforContextMenuOption,
								newName.getText().toString());
						manager.updateCounterHolder(holder);
						CounterListAdaptor.clear();
						updateCounterList();
						dialog.dismiss();
					}
				});

		renameDialog.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int arg1) {
						Log.d(TAG, "NOT saving counters new name.");
						dialog.dismiss();
					}
				});

		return renameDialog;
	}*/

	private AlertDialog createDeleteDialogBox() {
		Log.d(TAG, "Creating delete dialog box.");
		AlertDialog myQuittingDialogBox = new AlertDialog.Builder(this)
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
								Log.d(TAG, "Deleting Counter.");
								updateAdaptorListForRemove(ItemSelectedforContextMenuOption);
								dialog.dismiss();
							}

						})

				.setNegativeButton("No", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						Log.d(TAG, "NOT deleting Counter.");
						dialog.dismiss();
					}
				}).create();
		return myQuittingDialogBox;
	}

	private void updateAdaptorListForRemove(Counter itemToRemove) {
		Log.d(TAG, "Updating Adaptor.");
		holder.remove(itemToRemove);
		manager.updateCounterHolder(holder);
		CounterListAdaptor.remove(itemToRemove);
		CountersList.setAdapter(CounterListAdaptor);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		CounterListAdaptor.notifyDataSetChanged();
	}

	@Override
	public void onClick(View v) {
		Intent intent;
		switch(v.getId())
		{
		     case R.id.btn_counters_mainMenu:
		    	 finish();
		    	 break;
		     case R.id.btn_counters_New_Counter:
		    	 intent = new Intent(CountersActivity.this, NewCounterActivity.class);
		    	 intent.putExtra("mode", "CoutersActivity");
				 startActivity(intent);
		    	 break;
		     case R.id.btn_counters_DisplayData:
		    	 intent = new Intent(CountersActivity.this, DataActivity.class);
				 startActivity(intent);
		    	 break;
		}
		
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}
	
}
