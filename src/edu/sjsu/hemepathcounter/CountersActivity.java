package edu.sjsu.hemepathcounter;


import java.io.File;
import java.util.Arrays;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

/**
 * 
 * @author Amir Eibagi
 * 
 */
public class CountersActivity extends Activity implements View.OnClickListener, AdapterView.OnItemLongClickListener {
	private Button mainMenuButton, backButton;
	private ListView CountersList;
	private ArrayAdapter<String> CounterListAdaptor;
	private EditText CountersSearchBox;
	private String ItemSelectedforContextMenuOption = "";
    private int SelectedItemIndex;

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
		
		updateCounterList();
		CountersList.requestFocus();

		CountersList.setOnItemLongClickListener(this);
		
	}


	private void updateCounterList() {
		String[] fileNames = getApplicationContext().fileList();
		Arrays.sort(fileNames);
		for(int i = 0; i < fileNames.length; i++)
		{
			CounterListAdaptor.add(fileNames[i]);
		}
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

	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View v, int position,
			long id) {
		ItemSelectedforContextMenuOption = (String) CountersList.getItemAtPosition(position);
		SelectedItemIndex = position;
		registerForContextMenu(arg0);
		return false;
	}  
	
	@Override  
	public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {  
	super.onCreateContextMenu(menu, v, menuInfo);  
	    menu.setHeaderTitle("Options");  
	    menu.add(0, v.getId(), 0, "Rename");  
	    menu.add(0, v.getId(), 0, "Delete");  
	}
	
	 @Override  
	 public boolean onContextItemSelected(MenuItem item) 
	 {	 
	     if(item.getTitle()=="Rename")
	     {
	     	AlertDialog.Builder renameDialogBox = createRenameDialogBox();
	     	renameDialogBox.show();
	     }  	    
	     else if(item.getTitle()=="Delete")
	     {
	       	AlertDialog quitDialogBox = createQuitDialogBox();
	       	quitDialogBox.show();
	     }	 
	     return true;  
	 }

	
	private AlertDialog.Builder createRenameDialogBox() {
		AlertDialog.Builder renameDialog = new AlertDialog.Builder(this);
        renameDialog.setTitle("Rename");
        renameDialog.setMessage("Rename  " +  ItemSelectedforContextMenuOption + " to:");
        renameDialog.setIcon(R.drawable.rename_icon);
        final EditText NewFileName = new EditText(this);
        NewFileName.setHint("Enter new file name");
        renameDialog.setView(NewFileName);
        renameDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int arg1) {
            	File oldFile = getApplicationContext().getFileStreamPath(ItemSelectedforContextMenuOption);
            	oldFile.renameTo(new File(getApplicationContext().getFilesDir(), NewFileName.getText().toString()));
            	CounterListAdaptor.clear();
            	updateCounterList();
            	dialog.dismiss();
            }
        });

        renameDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int arg1) {
            	dialog.dismiss();
            }
        });

    
		return renameDialog;
	}

	private AlertDialog createQuitDialogBox() {
		 AlertDialog myQuittingDialogBox =new AlertDialog.Builder(this) 
         //set message, title, and icon
         .setTitle("Delete") 
         .setMessage("Do you want to Delete " +  ItemSelectedforContextMenuOption + "?") 
         .setIcon(R.drawable.delete_icon)

         .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

             public void onClick(DialogInterface dialog, int whichButton) { 
    	    	 deleteFile(ItemSelectedforContextMenuOption);
    	    	 updateAdaptorListForRemove(ItemSelectedforContextMenuOption);
                 dialog.dismiss();
             }
             

         })

         .setNegativeButton("No", new DialogInterface.OnClickListener() {
             public void onClick(DialogInterface dialog, int which) {
                 dialog.dismiss();
             }
         })
         .create();
		return myQuittingDialogBox;
	}


	private void updateAdaptorListForRemove(String s) {
		CounterListAdaptor.remove(s);
		CountersList.setAdapter(CounterListAdaptor);
	}  
}
