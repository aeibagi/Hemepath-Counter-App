package edu.sjsu.hemepathcounter;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

/**
 * 
 * @author Amir Eibagi
 * 
 */
public class CountersActivity extends Activity implements View.OnClickListener {
	private Button mainMenuButton, backButton;
	private ListView CountersList;
	private ArrayAdapter<String> CounterListAdaptor;
	private EditText CountersSearchBox;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_counters);
		initialize();
		SetupSearchFilter();

	}

	private void initialize() {
		// initialize the button and setup actionlisteners
		mainMenuButton = (Button) findViewById(R.id.CountersActivity_mainMenu_button);
		backButton = (Button) findViewById(R.id.CountersActivity_back_button);
		mainMenuButton.setOnClickListener(this);
		backButton.setOnClickListener(this);
		// initialize the Search box
		CountersSearchBox = (EditText) findViewById(R.id.EditBox_Counters_Search);
		// setting up the list for the counters
		CountersList = (ListView) findViewById(R.id.Counters_list);
		CounterListAdaptor = new ArrayAdapter<String>(this,
				R.layout.counters_list_item, R.id.TextView_Counters_List_items);
		CounterListAdaptor.add("Counter 1");
		CounterListAdaptor.add("Counter 2");
		CounterListAdaptor.add("Counter 3");
		CounterListAdaptor.add("Counter 4");
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
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.CountersActivity_mainMenu_button:
			break;
		case R.id.CountersActivity_back_button:
			break;
		}

	}

}
