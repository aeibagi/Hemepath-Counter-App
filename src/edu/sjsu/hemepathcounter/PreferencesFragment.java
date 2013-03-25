package edu.sjsu.hemepathcounter;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceFragment;
import android.util.Log;
import android.widget.Toast;

public class PreferencesFragment extends PreferenceFragment {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Load the preferences from an XML resource
		addPreferencesFromResource(R.xml.preferences);
		
		
		//Add email validation
		OnPreferenceChangeListener listener = new OnPreferenceChangeListener() {

			@Override
			public boolean onPreferenceChange(Preference preference,
					Object newValue) {
				if (newValue == null) {
					return false;
				} else {
					String newEmail = (String) newValue;
					if(isValidEmail(newEmail)){
						Log.d(getTag(),"Valid email address saved: " + newEmail);
						return true;
					}else{
						Log.d(getTag(),"Invalid email address NOT saved: " + newEmail);
						Toast.makeText(getActivity(),
								"Invalid Email Address. Not Saved.",
								Toast.LENGTH_SHORT).show();
						return false;
					}
				}
			}

			private boolean isValidEmail(String newValue) {
				return android.util.Patterns.EMAIL_ADDRESS.matcher(newValue).matches();
			}
		};
		findPreference("pref_email").setOnPreferenceChangeListener(listener);
	}
}
