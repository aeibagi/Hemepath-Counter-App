package edu.sjsu.hemepathcounter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;

public class NewCounterActivity extends Activity implements
		View.OnClickListener, ExpandableListView.OnChildClickListener {

	private Button ModifyButton, SaveButton, ClearButton, CustomButton,
			mainMenu;
	private ExpandableListView mExpandableList;
	private EditText editBox_enter_name;
	private String File_Name = "untitled";
	private MyCustomAdapterForExpandableList myCustomAdaptor;

	private ArrayList<String> userSelection;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_counter);
		initialize();
		initializePredefinedCells();
	}

	private void initialize() {

		mExpandableList = (ExpandableListView) findViewById(R.id.expandable_list_New_Counters);
		mExpandableList.requestFocus();
		mExpandableList.setOnChildClickListener(this);

		userSelection = new ArrayList<String>();
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

	}

	private void initializePredefinedCells() {

		ArrayList<Parent> arrayParents = new ArrayList<Parent>();
		ArrayList<String> arrayChildren1 = new ArrayList<String>();
		ArrayList<String> arrayChildren2 = new ArrayList<String>();
		ArrayList<String> arrayChildren3 = new ArrayList<String>();
		ArrayList<String> arrayChildren4 = new ArrayList<String>();
		ArrayList<String> CustomarrayChildren = new ArrayList<String>();

		Parent parent1 = new Parent();
		Parent parent2 = new Parent();
		Parent parent3 = new Parent();
		Parent parent4 = new Parent();
		Parent CustomParent = new Parent();

		parent1.setTitle(getResources().getString(R.string.Default_Basic_Panel));
		parent2.setTitle(getResources().getString(
				R.string.High_yield_additional));
		parent3.setTitle(getResources()
				.getString(R.string.Mid_yield_additional));
		parent4.setTitle(getResources()
				.getString(R.string.Low_yield_additional));
		CustomParent.setTitle("Custom Cells");

		// add children to basic/default panel
		arrayChildren1.add(getResources().getString(R.string.Lymphocytes));
		arrayChildren1.add(getResources().getString(R.string.Monocytes));
		arrayChildren1.add(getResources().getString(R.string.Eosinophils));
		arrayChildren1.add(getResources().getString(R.string.Basophils));
		arrayChildren1.add(getResources().getString(R.string.Neutrophils));
		arrayChildren1.add(getResources().getString(R.string.Bands));
		arrayChildren1.add(getResources().getString(R.string.Blasts));
		arrayChildren1.add(getResources().getString(R.string.Reticulocytes));

		// add children to High_yield_additional panel
		arrayChildren2.add(getResources().getString(
				R.string.atypicalLymphocytes));
		arrayChildren2.add(getResources().getString(R.string.Meta_myelocytes));
		arrayChildren2.add(getResources().getString(R.string.Myelocytes));
		arrayChildren2.add(getResources().getString(R.string.Promyelocytes));
		arrayChildren2.add(getResources().getString(R.string.Promonocytes));
		arrayChildren2.add(getResources().getString(R.string.Red_blood_cells));
		arrayChildren2
				.add(getResources().getString(R.string.white_Blodd_Cells));
		arrayChildren2.add(getResources().getString(R.string.Plasma_cells));
		arrayChildren2.add(getResources().getString(
				R.string.Atypical_plasma_cells));
		arrayChildren2.add(getResources().getString(R.string.Mast_cells));
		arrayChildren2.add(getResources().getString(R.string.Megakaryocytes));

		// add children to Mid_yield_additional panel
		arrayChildren3.add(getResources().getString(R.string.Erythroblasts));
		arrayChildren3.add(getResources().getString(R.string.proerythroblasts));
		arrayChildren3.add(getResources().getString(R.string.T_cells));
		arrayChildren3.add(getResources().getString(R.string.B_Cells));
		arrayChildren3.add(getResources().getString(R.string.CD4Plus_T_cells));
		arrayChildren3.add(getResources().getString(R.string.CD8Plus_T_cells));
		arrayChildren3.add(getResources().getString(
				R.string.Reed_Sternberg_cells));

		// add children to Low_yield_additional panel
		arrayChildren4.add(getResources().getString(
				R.string.Atypical_eosinophils));
		arrayChildren4.add(getResources()
				.getString(R.string.Atypical_basophils));
		arrayChildren4.add(getResources().getString(
				R.string.Atypical_neutrophils));
		arrayChildren4.add(getResources().getString(
				R.string.Atypical_megakaryocytes));
		arrayChildren4.add(getResources().getString(R.string.Megakaryoblasts));
		arrayChildren4.add(getResources().getString(
				R.string.Basophilic_normoblasts));
		arrayChildren4.add(getResources().getString(
				R.string.Orthochromatophilic_normoblasts));
		arrayChildren4.add(getResources().getString(R.string.Immunoblasts));
		arrayChildren4.add(getResources().getString(R.string.Centroblasts));
		arrayChildren4.add(getResources().getString(R.string.Centrocytes));
		arrayChildren4.add(getResources().getString(R.string.Dendritic_cells));
		arrayChildren4.add(getResources().getString(R.string.NK_cells));

		// set parents adapter
		parent1.setArrayChildren(arrayChildren1);
		parent2.setArrayChildren(arrayChildren2);
		parent3.setArrayChildren(arrayChildren3);
		parent4.setArrayChildren(arrayChildren4);
		CustomParent.setArrayChildren(CustomarrayChildren);

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
			break;
		case R.id.Button_Clear:
			ClearEverything();
			break;
		case R.id.Button_Custom:
			break;
		case R.id.Button_Save:
			String CounterName = editBox_enter_name.getText().toString();
			if (editBox_enter_name.getText().toString().trim().length() > 0) {

				File_Name = editBox_enter_name.getText().toString();
				try {
					FileOutputStream fos = openFileOutput(File_Name,
							Context.MODE_PRIVATE);
					@SuppressWarnings("rawtypes")
					Iterator itr = userSelection.iterator();
					while (itr.hasNext()) {
						fos.write(itr.next().toString().getBytes());
					}
					fos.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				finish();
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

		String ChildName = parent.getExpandableListAdapter()
				.getChild(groupPosition, childPosition).toString();

		// highlight and un-highlight
		if (v.getBackground() == null) {
			userSelection.add(ChildName);
			v.setBackgroundResource(R.drawable.button_style_green);
		} else {
			if (userSelection.contains(ChildName)) {
				userSelection.remove(ChildName);
			}

			v.setBackground(getResources().getDrawable(
					android.R.drawable.list_selector_background));
			v.setBackground(null);

		}

		return true;
	}

	private void ClearEverything() {
		userSelection.clear();
		initializePredefinedCells();
	}

	@Override
	protected void onPause() {
		super.onPause();
		finish();
	}

}
