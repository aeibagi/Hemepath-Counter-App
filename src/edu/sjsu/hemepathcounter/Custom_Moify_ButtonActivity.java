package edu.sjsu.hemepathcounter;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.Menu;
import android.widget.ImageButton;

public class Custom_Moify_ButtonActivity extends Activity {

	ImageButton Ib_green;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_custom__moify__button);
		initialize();
	}

	private void initialize() {
		Ib_green = (ImageButton) findViewById(R.id.iB_CustomColor_Green);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.custom__moify__button, menu);
		return true;
	}

}
