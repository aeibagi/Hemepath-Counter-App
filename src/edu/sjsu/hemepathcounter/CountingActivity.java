package edu.sjsu.hemepathcounter;

import java.util.ArrayList;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import edu.sjsu.hemepathcounter.adapter.CountingAdapter;
import edu.sjsu.hemepathcounter.model.Counter;

public class CountingActivity extends Activity {

	private Counter mData;
	private CountingAdapter mAdapter;
	private ArrayList<Integer> mSequence;
	private boolean muted;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_counting);
		muted = PreferenceManager.getDefaultSharedPreferences(this).getBoolean(
				PreferencesActivity.MUTE, false);
		// Loading data from database depends New Counting or Favorites
		mData = getIntent().getParcelableExtra("counter");
		mAdapter = new CountingAdapter(this, mData);
		mSequence = new ArrayList<Integer>();

		GridView gridview = (GridView) findViewById(R.id.gridview);
		gridview.setAdapter(mAdapter);

		gridview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				// play sound ....
				if (!muted) {
					MediaPlayer player = MediaPlayer.create(
							CountingActivity.this,
							mData.getCells().get(position).getSound());
					player.start();
				}
				mData.getCells().get(position).incrementCount();
				mData.incrementTotal();
				mSequence.add(position);
				mAdapter.notifyDataSetChanged();
			}
		});

		// Undo button
		Button btnUndo = (Button) findViewById(R.id.counting_activity_undo);
		btnUndo.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (mSequence.size() > 0) {
					int position = mSequence.get(mSequence.size() - 1);
					mSequence.remove(mSequence.size() - 1);
					mData.getCells().get(position).decrementCount();
					mData.decrementTotal();
					mAdapter.notifyDataSetChanged();
				}
			}
		});

		// Save button
		Button btnSave = (Button) findViewById(R.id.counting_activity_save);
		btnSave.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// Save mData to file.
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
