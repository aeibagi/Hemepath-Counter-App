package edu.sjsu.hemepathcounter;

import java.util.ArrayList;
import java.util.HashMap;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Toast;
import edu.sjsu.hemepathcounter.adapter.MyCustomAdapterForExpandableList;
import edu.sjsu.hemepathcounter.model.ButtonHolder;
import edu.sjsu.hemepathcounter.model.CellButton;
import edu.sjsu.hemepathcounter.model.Counter;
import edu.sjsu.hemepathcounter.model.CounterHolder;

/**
 * 
 * @author Amir Eibagi
 * @author Jake Karnes
 * 
 */
public class NewCounterActivity extends Activity implements
		View.OnClickListener, ExpandableListView.OnChildClickListener {

	private Button ModifyButton, SaveButton, ClearButton, CustomButton,
			mainMenu;
	private ExpandableListView mExpandableList;
	private EditText editBox_enter_name;
	private MyCustomAdapterForExpandableList myCustomAdaptor;

	private ArrayList<CellButton> userSelection;

	public static HashMap<CellButton, Boolean> ChildStatus;

	public FileManager manager;
	public ButtonHolder holder;

	public static Drawable listViewdefaultbackground;

	private ArrayList<Parent> arrayParents = new ArrayList<Parent>();
	private ArrayList<CellButton> defaultButtons = new ArrayList<CellButton>();
	private ArrayList<CellButton> highYieldButtons = new ArrayList<CellButton>();
	private ArrayList<CellButton> midYieldButtons = new ArrayList<CellButton>();
	private ArrayList<CellButton> lowYieldButtons = new ArrayList<CellButton>();
	private ArrayList<CellButton> customButtons = new ArrayList<CellButton>();

	private Parent parent1 = new Parent();
	private Parent parent2 = new Parent();
	private Parent parent3 = new Parent();
	private Parent parent4 = new Parent();
	private Parent CustomParent = new Parent();
	
   private CellButton created_custom_button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_counter);
		initialize();
		initializeCells();
	}

	private void initialize() {

		listViewdefaultbackground = getResources().getDrawable(
				android.R.drawable.list_selector_background);
		mExpandableList = (ExpandableListView) findViewById(R.id.expandable_list_New_Counters);
		mExpandableList.requestFocus();
		mExpandableList.setOnChildClickListener(this);

		ChildStatus = new HashMap<CellButton, Boolean>();

		userSelection = new ArrayList<CellButton>();
		// applicationBaseDatabase = new ApplicationDatabaseActivity();
		editBox_enter_name = (EditText) findViewById(R.id.edt_Enter_name);

		mainMenu = (Button) findViewById(R.id.Button_New_counters_backTo_MainMenu);
		ModifyButton = (Button) findViewById(R.id.Button_Modify);
		SaveButton = (Button) findViewById(R.id.Button_Save);
		ClearButton = (Button) findViewById(R.id.Button_Clear);
		CustomButton = (Button) findViewById(R.id.Button_Custom);

		mainMenu.setOnClickListener(this);
		ModifyButton.setOnClickListener(this);
		SaveButton.setOnClickListener(this);
		ClearButton.setOnClickListener(this);
		CustomButton.setOnClickListener(this);

		// gray out the modify button
		ModifyButton.setBackgroundResource(R.drawable.button_style_gray);
		ModifyButton.setClickable(false);

	}

	private void initializeCells() {

		manager = new FileManager(getApplicationContext());
		holder = manager.getButtonHolder();

		parent1.setTitle(getResources().getString(R.string.Default_Basic_Panel));
		parent2.setTitle(getResources().getString(
				R.string.High_yield_additional));
		parent3.setTitle(getResources()
				.getString(R.string.Mid_yield_additional));
		parent4.setTitle(getResources()
				.getString(R.string.Low_yield_additional));
		CustomParent.setTitle("Custom Cells");

		defaultButtons = holder.getDefaultButtons();
		highYieldButtons = holder.getHighYieldButtons();
		midYieldButtons = holder.getMidYieldButtons();
		lowYieldButtons = holder.getLowYieldButtons();
		customButtons = holder.getCustomButtons();

		// add children to basic/default panel
		parent1.setArrayChildren(defaultButtons);
		parent2.setArrayChildren(highYieldButtons);
		parent3.setArrayChildren(midYieldButtons);
		parent4.setArrayChildren(lowYieldButtons);
		CustomParent.setArrayChildren(customButtons);

		arrayParents.add(parent1);
		arrayParents.add(parent2);
		arrayParents.add(parent3);
		arrayParents.add(parent4);
		arrayParents.add(CustomParent);

		// set the parent & child adaptor to the expandable list
		myCustomAdaptor = new MyCustomAdapterForExpandableList(
				NewCounterActivity.this, arrayParents);
		mExpandableList.setAdapter(myCustomAdaptor);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_counter, menu);
		return true;
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.Button_New_counters_backTo_MainMenu:
			finish();
			break;
		case R.id.Button_Modify:
			Intent intent = new Intent(NewCounterActivity.this,
					Custom_Modify_ButtonActivity.class);
			intent.putExtra("button", userSelection.get(0));
			intent.putExtra("ModifyorCustom", "Modify");
			startActivityForResult(intent, 2);
			break;
		case R.id.Button_Clear:
			ClearEverything();
			break;
		case R.id.Button_Custom:
			Intent intent2 = new Intent(NewCounterActivity.this,
					Custom_Modify_ButtonActivity.class);
			intent2.putExtra("ModifyorCustom", "Custom");
			startActivityForResult(intent2, 1);
			break;
		case R.id.Button_Save:
			String name = editBox_enter_name.getText().toString().trim();
			if (name.length() > 0) {
				if (!userSelection.isEmpty()) {
					CounterHolder counterHolder = manager.getCounterHolder();
					counterHolder.addCounter(new Counter(name, userSelection));
					manager.updateCounterHolder(counterHolder);
					finish();
				} else {
					Toast.makeText(this, "You did not add any buttons.",
							Toast.LENGTH_SHORT).show();
				}

			} else {
				Toast.makeText(this,
						"You did not enter a name for your counter",
						Toast.LENGTH_SHORT).show();
			}

			break;

		}

	}

	@SuppressLint("NewApi")
	@Override
	public boolean onChildClick(ExpandableListView parent, View v,
			int groupPosition, int childPosition, long id) {

		CellButton ChildName = (CellButton) parent.getExpandableListAdapter()
				.getChild(groupPosition, childPosition);

		// highlight and un-highlight
		if (v.getBackground() == null) {
			userSelection.add(ChildName);
			v.setBackgroundResource(R.drawable.button_style_green);
			ChildStatus.put(ChildName, true);
		} else {
			if (userSelection.contains(ChildName)) {
				userSelection.remove(ChildName);
				ChildStatus.put(ChildName, false);
			}
			v.setBackground(getResources().getDrawable(
					android.R.drawable.list_selector_background));
			v.setBackground(null);
		}

		if (userSelection.isEmpty() || userSelection.size() > 1) {
			ModifyButton.setBackgroundResource(R.drawable.button_style_gray);
			ModifyButton.setClickable(false);
		} else if (userSelection.size() == 1) {
			ModifyButton.setBackgroundResource(R.drawable.button_style_yellow);
			ModifyButton.setClickable(true);
		}
		return true;
	}

	@SuppressLint("NewApi")
	private void ClearEverything() {
		userSelection.clear();
		ChildStatus.clear();
		arrayParents.clear();
		defaultButtons.clear();
		highYieldButtons.clear();
		midYieldButtons.clear();
		lowYieldButtons.clear();
		initializeCells();

	}

	@Override
	protected void onPause() {
		super.onPause();
		// finish();

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		switch(requestCode)
		{ 
		    case (1):
		    	if (resultCode == Activity.RESULT_OK) 
			    { 
		    		created_custom_button = data.getParcelableExtra("Custom_Button");
		    		customButtons = holder.getCustomButtons();
		    		customButtons.add(created_custom_button);
		    		//CustomParent.setArrayChildren(customButtons);
			    }
		    	break;
		    case (2):
		    	if (resultCode == Activity.RESULT_OK) 
			    { 
		    		
			    }
		    	break;
		    	
		}
	}

}
