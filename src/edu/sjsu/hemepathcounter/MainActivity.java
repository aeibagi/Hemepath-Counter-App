package edu.sjsu.hemepathcounter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import edu.sjsu.hemepathcounter.model.Counter;
import edu.sjsu.hemepathcounter.model.CounterHolder;

/**
 * This is the application's default landing page or main menu. The user will
 * begin here and can use the buttons to navigate to different activities. Other
 * activities will also route the user to this screen. From this Activity, the
 * user can view favorite button presets, launch a Counting Activity, Data
 * Activity, or Settings Activity.
 * 
 * @author Jake Karnes, Minh H Dang, Amir Eibagi
 * 
 */
public class MainActivity extends Activity implements View.OnClickListener,
		AdapterView.OnItemClickListener {
	private static final String TAG = "MainActivity";
	private ListView favoritesListView;
	private ArrayAdapter<Counter> adapter;
	private Button counterButton, newButton, preferenceButton, dataButton, resumeButton;
	private FileManager manager;
	private CounterHolder holder;
	private Counter itemSelected;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "Starting Main Activity");
		setContentView(R.layout.activity_main);
		initialize();

//		SharedPreferences.Editor editor = PreferenceManager
//				.getDefaultSharedPreferences(this).edit();
//		editor.putBoolean("resume", false);
//		editor.commit();
		FileManager.resume = 0;
		
		adapter = new ArrayAdapter<Counter>(this,
				R.layout.list_item_favorite_counter);
		setupFavorites();
		favoritesListView.setOnItemClickListener(this);
	}

	private void setupFavorites() {
		adapter.clear();
		manager = FileManager.getInstance(getApplicationContext());
		holder = manager.getCounterHolder();
		adapter.addAll(holder.getFavoriteCounters());
		favoritesListView.setAdapter(adapter);
	}

	private void initialize() {
		favoritesListView = (ListView) findViewById(R.id.favorites_list);
		// initialize our button on the main activity
		counterButton = (Button) findViewById(R.id.counters_button);
		newButton = (Button) findViewById(R.id.new_button);
		preferenceButton = (Button) findViewById(R.id.preferences_button);
		dataButton = (Button) findViewById(R.id.data_button);
		resumeButton = (Button) findViewById(R.id.resume_button);
		// setting up click action listeners for each buttons
		counterButton.setOnClickListener(this);
		newButton.setOnClickListener(this);
		preferenceButton.setOnClickListener(this);
		dataButton.setOnClickListener(this);
		resumeButton.setOnClickListener(this);
		
		if (FileManager.resume == -1) {
			resumeButton.setBackgroundResource(R.drawable.button_style_gray);
			resumeButton.setClickable(false);
		}
		
		// Set default values if not set already.
		PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
	}

	/**
	 * Create a menu to be displayed when user hits Menu key on device
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return true;
	}

	/** Menu Item Click Listener */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
		case R.id.menu_options_about:
			Log.d(TAG, "Showing App About Dialog.");
			AlertDialog.Builder builder = new AlertDialog.Builder(this, AlertDialog.THEME_HOLO_LIGHT);
		    // Get the layout inflater
		    LayoutInflater inflater = getLayoutInflater();

		    // Inflate and set the layout for the dialog
		    // Pass null as the parent view because its going in the dialog layout
		    builder.setTitle(R.string.about_title);
		    builder.setView(inflater.inflate(R.layout.about_dialog, null))
		    // Add action buttons
		           .setPositiveButton("OK", new DialogInterface.OnClickListener() {
		               @Override
		               public void onClick(DialogInterface dialog, int id) {
		                   // sign in the user ...
		               }
		           });      
		    builder.show();
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onClick(View v) { // handler for click listener
		Intent intent;
		switch (v.getId()) {
			case R.id.counters_button:
				intent = new Intent(MainActivity.this, CountersActivity.class);
				startActivity(intent);
				break;
			case R.id.new_button:
				intent = new Intent(MainActivity.this, NewCounterActivity.class);
				startActivity(intent);
				break;
			case R.id.preferences_button:
				Log.d(TAG, "preferences");
				intent = new Intent(MainActivity.this, PreferencesActivity.class);
				startActivity(intent);
				break;
			case R.id.data_button:
				Log.d(TAG, "data");
				intent = new Intent(MainActivity.this, DataActivity.class);
				startActivity(intent);
				break;
			case R.id.resume_button: 
				FileManager.resume = 1;
				intent = new Intent(MainActivity.this, CountingActivity.class);
				startActivity(intent);
				break;
			default:
				break;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View v, int position, long id) {
		Log.d(TAG, "Favorite Selected.");
		itemSelected = (Counter) favoritesListView.getItemAtPosition(position);
		Intent i = new Intent(MainActivity.this, CountingActivity.class);
		i.putExtra("counter", itemSelected);
		startActivity(i);
		holder.incrementCounterUse(itemSelected);
		manager.updateCounterHolder(holder);
	}

	@Override
	protected void onRestart() {
		Log.d(TAG, "Restarting Main Activity");
		super.onRestart();
		setupFavorites();
	}

}
