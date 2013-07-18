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
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.NumberPicker;
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
	private boolean muted_notify;
	private boolean muted_notify_total;
	private MediaPlayer player;
	private int notify;
	private int total_notify;
	private int total_sound = R.raw.system;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "Starting new Counting Activity.");
		setContentView(R.layout.activity_counting);
		SharedPreferences preferences = PreferenceManager
				.getDefaultSharedPreferences(this);
		muted_notify = preferences.getBoolean(PreferencesActivity.MUTE, false);
		notify = Integer.parseInt(preferences.getString(PreferencesActivity.NOTIFY, "20"));
		muted_notify_total = preferences.getBoolean(PreferencesActivity.MUTE_TOTAL, false);
		total_notify = Integer.parseInt(preferences.getString(PreferencesActivity.TOTAL_NOTIFY, "100"));
		
		// Loading data from database depends New Counting or Favorites
		mData = getIntent().getParcelableExtra("counter");
		mAdapter = new CountingAdapter(this, mData);
		mSequence = new ArrayList<Integer>();

		GridView gridview = (GridView) findViewById(R.id.counting_activity_gridview);		
		gridview.setAdapter(mAdapter);
		gridview.setLongClickable(true);
		
		final AlertDialog.Builder builder = new AlertDialog.Builder(this);
		
		gridview.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View v, final int position, long id) {
//				Log.d(TAG, "Long click - position: "  + position);
				//Toast.makeText(CountingActivity.this, "Long click: " + position, Toast.LENGTH_SHORT).show();
				final CellButton button = mData.getCells().get(position);
				int value = button.getCount(); 
				
				//NumberPicker np = (NumberPicker) findViewById(R.id.numberpicker);
				final NumberPicker np = new NumberPicker(CountingActivity.this);
				np.setMinValue(0);
				np.setMaxValue(value + 20);
				String[] nums = new String[value + 21];
				for (int i = 0; i <= value + 20; i++)
					nums[i] = i + "";
				np.setWrapSelectorWheel(true);
				np.setDisplayedValues(nums);
				np.setValue(value);
				
				builder.setView(np);
	
				builder.setMessage("Change value of \n" + button.getName() + ": ")
		               .setPositiveButton("Set", new DialogInterface.OnClickListener() {
		                   public void onClick(DialogInterface dialog, int id) {
		                	   Log.d(TAG, "Change Value of Counting Activity.");
		                	   int d = np.getValue() - button.getCount(); 
		                	   if (d == 0) return;
		                	   
		                	   mData.addTotal(d);
		                	   button.setCount(np.getValue());
		                	   
		                	   if (d > 0) { //add buton to sequence
		                		   for (int i = 0; i < d; i++)
		                			   mSequence.add(position);
		                	   }
		                	   else {
		                		   for (int i = mSequence.size() - 1; i >= 0; i--) 
		                			   if (mSequence.get(i) == position) {
		                				   mSequence.remove(i);
		                				   d++;
		                				   if (d == 0) break;
		                			   }
		                	   }
		                	   
		                	   mAdapter.notifyDataSetChanged();
		                   }
		               })
		               .setNegativeButton(R.string.counting_activity_cancel, new DialogInterface.OnClickListener() {
		                   public void onClick(DialogInterface dialog, int id) {
		                       // User cancelled the dialog
		                	   Log.d(TAG, "User cancelled clearing the Counting Activity.");
		                   }
		               });
//				builder.show();
				
				AlertDialog alert = builder.create();
		        alert.show();
		        alert.getWindow().setLayout(280, 320);
				return true;
			}
		});

		gridview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				CellButton button = mData.getCells().get(position);
				button.incrementCount();
				mData.incrementTotal();
				mSequence.add(position);
				mAdapter.notifyDataSetChanged();
				
				// play sound ....
				if (!muted_notify_total && (mData.getTotal() % total_notify == 0)) {
					if (player != null) {
						player.release();
					}
					player = MediaPlayer.create(CountingActivity.this, total_sound);
					player.start();
				}
			
				if (!muted_notify && (mData.getTotal() % total_notify != 0) && (button.getCount() % notify == 0)) { 
					if (player != null) {
						player.release();
					}
					player = MediaPlayer.create(CountingActivity.this, mData
							.getCells().get(position).getSound());
					player.start();
				}
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
				if (mData.getTotal() == 0) break ;
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
