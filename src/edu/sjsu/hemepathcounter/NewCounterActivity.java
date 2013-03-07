package edu.sjsu.hemepathcounter;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class NewCounterActivity extends Activity implements View.OnClickListener {

	private Button ModifyButton, SaveButton, ClearButton, CustomButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_counter);
		initialize();
	}

	private void initialize() {
		ModifyButton = (Button) findViewById(R.id.Button_Modify);
		SaveButton = (Button) findViewById(R.id.Button_Save);
		ClearButton = (Button) findViewById(R.id.Button_Clear);
		CustomButton = (Button) findViewById(R.id.Button_Custom);
		
		ModifyButton.setOnClickListener(this);
		SaveButton.setOnClickListener(this);
		ClearButton.setOnClickListener(this);
		CustomButton.setOnClickListener(this);
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
		case R.id.Button_Modify:
			break;
		case R.id.Button_Clear:
			break;
		case R.id.Button_Custom:
			break;
		case R.id.Button_Save:
			break;
		}

		
	}

}
