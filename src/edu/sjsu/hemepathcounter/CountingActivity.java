package edu.sjsu.hemepathcounter;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import edu.sjsu.hemepathcounter.adapter.CountingAdapter;
import edu.sjsu.hemepathcounter.model.CellButton;
import edu.sjsu.hemepathcounter.model.Counter;
import edu.sjsu.hemepathcounter.model.Data;
import edu.sjsu.hemepathcounter.model.DataHolder;

public class CountingActivity extends Activity implements View.OnClickListener {
	private static final String TAG = "CountingActivity";
	private Counter mData;
	private CountingAdapter mAdapter;
	private ArrayList<Integer> mSequence;
	private boolean muted;
	private MediaPlayer player;
	private int notify;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "Starting new Counting Activity.");
		setContentView(R.layout.activity_counting);
		SharedPreferences preferences = PreferenceManager
				.getDefaultSharedPreferences(this);
		muted = preferences.getBoolean(PreferencesActivity.MUTE, false);
		notify = Integer.parseInt(preferences.getString(PreferencesActivity.NOTIFY, "20"));

		// Loading data from database depends New Counting or Favorites
		mData = getIntent().getParcelableExtra("counter");
		mAdapter = new CountingAdapter(this, mData);
		mSequence = new ArrayList<Integer>();

		GridView gridview = (GridView) findViewById(R.id.counting_activity_gridview);		
		gridview.setAdapter(mAdapter);

		gridview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				CellButton button = mData.getCells().get(position);
				button.incrementCount();
				// play sound ....
				if (!muted && (mData.getTotal() % notify == 0)) { //(button.getCount() % notify == 0) 
					if (player != null) {
						player.release();
					}
					player = MediaPlayer.create(CountingActivity.this, mData
							.getCells().get(position).getSound());
					player.start();
				}
				mData.incrementTotal();
				mSequence.add(position);
				mAdapter.notifyDataSetChanged();
			}
		});
		
		// Undo button
		Button btnUndo = (Button) findViewById(R.id.counting_activity_undo);
		btnUndo.setOnClickListener(this);

		// Save button
		Button btnSave = (Button) findViewById(R.id.counting_activity_save);
		btnSave.setOnClickListener(this);
		
		//Clear button
		Button btnClear = (Button) findViewById(R.id.counting_activity_clear);
		btnClear.setOnClickListener(this);
		
		//Main button
		Button btnMain = (Button) findViewById(R.id.counting_activity_main);
		btnMain.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.counting, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		Intent intent;
		AlertDialog.Builder builder;
		
		switch (v.getId()) {
			case R.id.counting_activity_undo:
				if (mSequence.size() == 0) break;
				int position = mSequence.get(mSequence.size() - 1);
				mSequence.remove(mSequence.size() - 1);
				mData.getCells().get(position).decrementCount();
				mData.decrementTotal();
				mAdapter.notifyDataSetChanged();				
				break;
			case R.id.counting_activity_clear:
				Log.d(TAG, "Clear button clicked.");
				if (mData.getTotal() == 0) break;
				builder = new AlertDialog.Builder(this);
		        builder.setMessage(R.string.counting_activity_clear_title)
		               .setPositiveButton(R.string.counting_activity_clear, new DialogInterface.OnClickListener() {
		                   public void onClick(DialogInterface dialog, int id) {
		                	   Log.d(TAG, "Clearing Counting Activity.");
		                	   mSequence.clear();
		                	   mData.reset();
		                	   mAdapter.notifyDataSetChanged();
		                   }
		               })
		               .setNegativeButton(R.string.counting_activity_cancel, new DialogInterface.OnClickListener() {
		                   public void onClick(DialogInterface dialog, int id) {
		                       // User cancelled the dialog
		                	   Log.d(TAG, "User cancelled clearing the Counting Activity.");
		                   }
		               });
		        builder.show();
				break;
			case R.id.counting_activity_save:
				//get name for the counter after finishing counting
				Log.d(TAG, "Creating Data Object and saving.");
				//Save data to database
				FileManager manager = FileManager.getInstance(getApplicationContext()); 
				DataHolder mDataHolder = manager.getDataHolder();
				Data data = new Data(mData);
				mDataHolder.addData(data);
				manager.updateDataHolder(mDataHolder);
				
				finish();
				intent = new Intent(CountingActivity.this, DisplayDataActivity.class);
				intent.putExtra("data", data);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);				
				break;
			case R.id.counting_activity_main:
				Log.d(TAG, "Main Menu Button clicked.");
				finish();
				intent = new Intent(CountingActivity.this, MainActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);				
				break;
			default:
				break;
		}
		
	}

}
