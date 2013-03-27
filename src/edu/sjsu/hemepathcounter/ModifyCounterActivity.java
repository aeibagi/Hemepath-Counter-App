package edu.sjsu.hemepathcounter;

import java.util.ArrayList;
import java.util.Iterator;
import edu.sjsu.hemepathcounter.adapter.modify_counter_adaptor;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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
import android.widget.Toast;
import edu.sjsu.hemepathcounter.model.ButtonHolder;
import edu.sjsu.hemepathcounter.model.CellButton;
import edu.sjsu.hemepathcounter.model.Counter;
import edu.sjsu.hemepathcounter.model.CounterHolder;

public class ModifyCounterActivity extends Activity implements
		View.OnClickListener, AdapterView.OnItemClickListener {
	
	private static final String TAG = "ModifyCounterActivity";
	private EditText modify_counter_name;
	private ListView edit_buttoninCounters_list;
	private ArrayAdapter<CellButton> modify_counter_adaptor;
	private Counter mData;
	private CellButton ItemSelectedforContextMenuOption;
	private FileManager manager;
	private CounterHolder myCounterHolder;
	private Button finish_button;
	private String originalName;
	
	private modify_counter_adaptor counter_adaptor;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d(TAG, "Starting new Modify Counter Activity");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_modify_counter);
		initiailize();
	}

	private void initiailize() {
		manager = FileManager.getInstance(getApplicationContext());
		myCounterHolder = manager.getCounterHolder();
		
		finish_button = (Button) findViewById(R.id.btn_edit_counter_finish);
		finish_button.setOnClickListener(this);

		modify_counter_name = (EditText) findViewById(R.id.modify_counter_editText);
		mData = getIntent().getParcelableExtra("counter");

		edit_buttoninCounters_list = (ListView) findViewById(R.id.list_view_counters);
		edit_buttoninCounters_list.setOnItemClickListener(this);
		
		counter_adaptor = new modify_counter_adaptor(ModifyCounterActivity.this, mData.getCells());

		// please keep this
		/*modify_counter_adaptor = new ArrayAdapter<CellButton>(this,
				android.R.layout.simple_list_item_1, mData.getCells());*/

		edit_buttoninCounters_list.setAdapter(counter_adaptor);
		
		if(mData != null)
		{
			modify_counter_name.setText(mData.getName());
			originalName = mData.getName();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.modify_counter, menu);
		return true;
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.btn_edit_counter_finish:
			Log.d(TAG, "Finishing Modify Counter Activity");
			
			String name = modify_counter_name.getText().toString().trim();
			Counter c;
			if (name.length() > 0) 
			{
				ArrayList<Counter> listofCounters = myCounterHolder.getCounters();
				
				for (int i = 0; i < listofCounters.size(); i++) {
					c = listofCounters.get(i);
					if(c.getName().equals(mData.getName()))
					{
						myCounterHolder.renameCounter(c, name);
					}
				}
				manager.updateCounterHolder(myCounterHolder);
				if(!name.equals(originalName))
				{
					Toast.makeText(this, "Counter has renmed to " + name,
						Toast.LENGTH_SHORT).show();
				}
				finish();
			}
			else 
			{
				Toast.makeText(this, "You did Not Enter a Name for the Counter",
						Toast.LENGTH_SHORT).show();
			}
			break;
			
		}

	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View v, int position, long id) {
		ItemSelectedforContextMenuOption = (CellButton) edit_buttoninCounters_list
				.getItemAtPosition(position);

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
			Log.d(TAG, "Edit Selected.");
			EditButton();
		} else if (item.getTitle() == "Delete") {
			Log.d(TAG, "Delete selected.");
			AlertDialog quitDialogBox = createQuitDialogBox();
			quitDialogBox.show();
		}
		return true;
	}

	private void EditButton() {
		
		 Intent intent = new Intent(ModifyCounterActivity.this,
		 Custom_Modify_ButtonActivity.class); 
		 intent.putExtra("button",ItemSelectedforContextMenuOption); 
		 intent.putExtra("ModifyorCustom","Modify");
		 startActivityForResult(intent, 1);
		 

	}

	private AlertDialog createQuitDialogBox() {
		AlertDialog myQuittingDialogBox = new AlertDialog.Builder(this)
				// set message, title, and icon
				.setTitle("Delete")
				.setMessage(
						"Do you want to Delete "
								+ ItemSelectedforContextMenuOption.getName()
								+ "?")
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

	private void updateExpandableListAdaptorListForRemove(
			CellButton itemToRemove) {
		Counter c;
		ArrayList<CellButton> listofButtons;
		mData.removeaButton(itemToRemove);
		// please keep this
		//modify_counter_adaptor.notifyDataSetChanged();
		counter_adaptor.notifyDataSetChanged();
		ArrayList<Counter> listofCounters = myCounterHolder.getCounters();
		
		for (int i = 0; i < listofCounters.size(); i++) {
			c = listofCounters.get(i);
			if(c.getName().equals(mData.getName()))
			{
				listofButtons = c.getCells();
				for (int j =0; j < listofButtons.size(); j++)
				{
					if(listofButtons.get(j).getName().equals(itemToRemove.getName()))
					{
						listofButtons.remove(j);
					}
				}
			}
		}
		manager.updateCounterHolder(myCounterHolder);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Log.d(TAG, "Recieving result.");
		super.onActivityResult(requestCode, resultCode, data);

		switch (requestCode) {
		case (1):
			if (resultCode == Activity.RESULT_OK) {
				Log.d(TAG, "Changing already present button.");
				
				Counter c;
				ArrayList<CellButton> listofButtons = null;
				ArrayList<Counter> listofCounters = myCounterHolder.getCounters();
				
				for (int i = 0; i < listofCounters.size(); i++) {
					c = listofCounters.get(i);
					if(c.getName().equals(mData.getName()))
					{
						listofButtons = c.getCells();
						for (int j =0; j < listofButtons.size(); j++)
						{
							if(listofButtons.get(j).getName().equals(ItemSelectedforContextMenuOption.getName()))
							{
								listofButtons.get(j).setName(data.getStringExtra("newName"));
								listofButtons.get(j).setColor(data.getIntExtra("newColor", 0));
								listofButtons.get(j).setSound(data.getIntExtra("newSound", 0));
							}
						}
					}
				}
				// please keep this
				/*modify_counter_adaptor.notifyDataSetChanged();
				modify_counter_adaptor.clear();
				modify_counter_adaptor.addAll(listofButtons);*/
				
				counter_adaptor.notifyDataSetChanged();
				counter_adaptor.clearData();
				counter_adaptor.setData(listofButtons);
				manager.updateCounterHolder(myCounterHolder);
			}
			break;

		}
	}

}
