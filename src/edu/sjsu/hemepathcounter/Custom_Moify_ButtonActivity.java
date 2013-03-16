package edu.sjsu.hemepathcounter;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Custom_Moify_ButtonActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_custom__moify__button);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.custom__moify__button, menu);
		return true;
	}

}
