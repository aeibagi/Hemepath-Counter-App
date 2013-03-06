package edu.sjsu.hemepathcounter;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

/**
 * This is the application's default landing page or main menu. The user will
 * begin here and can use the buttons to navigate to different activities. Other
 * activities will also route the user to this screen. From this Activity, the
 * user can view favorite button presets, launch a Counting Activity, Data
 * Activity, or Settings Activity.
 * 
 * @author Jake Karnes
 * 
 */
public class MainActivity extends Activity implements OnItemClickListener{
	
	private ListView favoritesListView;
	private ArrayAdapter<String> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		favoritesListView = (ListView) findViewById(R.id.favorites_list);
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
		adapter.add("Favorite 1");
		adapter.add("Favorite 2");
		adapter.add("Favorite 3");
		adapter.add("Favorite 4");
		favoritesListView.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
	}

}
