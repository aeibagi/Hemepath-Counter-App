package edu.sjsu.hemepathcounter;

import java.util.ArrayList;
import java.util.Iterator;

import edu.sjsu.hemepathcounter.model.CellButton;
import edu.sjsu.hemepathcounter.model.Counter;
import edu.sjsu.hemepathcounter.model.CounterHolder;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ModifyCounterActivity extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener{

	private EditText modify_counter_name;
	private ListView edit_buttoninCounters_list;
	private ArrayAdapter<CellButton> modify_counter_adaptor;
	private Counter mData;
	private CellButton ItemSelectedforContextMenuOption;
	private FileManager manager;
	private CounterHolder myCounterHolder;
	private Button finish_button;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_modify_counter);
		initiailize();
	}

	private void initiailize() {
		manager = new FileManager(getApplicationContext());
		myCounterHolder = manager.getCounterHolder();
		
        finish_button = (Button) findViewById(R.id.btn_edit_counter_finish);
        finish_button.setOnClickListener(this);
		
		modify_counter_name = (EditText) findViewById(R.id.modify_counter_editText);
		mData = getIntent().getParcelableExtra("counter");
			

		
		edit_buttoninCounters_list = (ListView) findViewById(R.id.list_view_counters);
		edit_buttoninCounters_list.setOnItemClickListener(this);
		
		modify_counter_adaptor = new ArrayAdapter<CellButton>(this,
				android.R.layout.simple_list_item_1, mData.getCells());
		
		edit_buttoninCounters_list.setAdapter(modify_counter_adaptor);

		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.modify_counter, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		
		switch (v.getId())
		{
		    case R.id.btn_edit_counter_finish:
		    	break;
		}
		
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View v, int position,long id) {
		ItemSelectedforContextMenuOption = (CellButton) edit_buttoninCounters_list.getItemAtPosition(position);
		
		registerForContextMenu(v);
		v.setLongClickable(false);
		this.openContextMenu(v);
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.setHeaderTitle("Options");
		menu.add(0, v.getId(), 0, "Edit");
		menu.add(0, v.getId(), 0, "Delete");
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		if (item.getTitle() == "Edit") {
			EditButton();
		} else if (item.getTitle() == "Delete") {
			AlertDialog quitDialogBox = createQuitDialogBox();
			quitDialogBox.show();
		}
		return true;
	}

	private void EditButton() 
	{
		/*Intent intent = new Intent(ModifyCounterActivity.this,
				Custom_Modify_ButtonActivity.class);
		intent.putExtra("button", ItemSelectedforContextMenuOption);
		intent.putExtra("ModifyorCustom", "Modify");
		startActivityForResult(intent, 1);*/
		
	}

	
	private AlertDialog createQuitDialogBox() {
		AlertDialog myQuittingDialogBox = new AlertDialog.Builder(this)
		// set message, title, and icon
		.setTitle("Delete")
		.setMessage(
				"Do you want to Delete "
						+ ItemSelectedforContextMenuOption.getName() + "?")
		.setIcon(R.drawable.delete_icon)

		.setPositiveButton("Yes",
				new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog,
							int whichButton) {
						updateExpandableListAdaptorListForRemove(ItemSelectedforContextMenuOption);
						dialog.dismiss();
					}

				})

		.setNegativeButton("No", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		}).create();
		return myQuittingDialogBox;
	}


	private void updateExpandableListAdaptorListForRemove(CellButton itemToRemove) {
		
		
		
	}


}
