package edu.sjsu.hemepathcounter;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;

public class PreferencesActivity extends Activity {
	private static final String TAG = "PreferencesActivity";
	public static final String MUTE = "pref_mute";
	public static final String NOTIFY = "pref_notify";
	public static final String MUTE_TOTAL = "pref_mute_total";
	public static final String TOTAL_NOTIFY = "pref_notify_total";
	public static final String EMAIL = "pref_email";
	public static final String EXPORT = "pref_export";
	public static final String TIMESTAMP = "pref_timestamp";
	public static final String SHOW_PERCENT = "pref_show_percent";
	public static final String SHOW_NUM = "pref_show_num";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "Starting Preferences Activity.");

		logCurrentSettings();
        setTitle("Settings");
		// Display the fragment as the main content.
		getFragmentManager().beginTransaction()
				.replace(android.R.id.content, new PreferencesFragment())
				.commit();
	}

	private void logCurrentSettings() {
		SharedPreferences preferences = PreferenceManager
				.getDefaultSharedPreferences(this);

		String settingsDebugMessage = "Mute: "
				+ preferences.getBoolean(PreferencesActivity.MUTE, false)
				+ "\n";
		
		settingsDebugMessage += "Notify at: "
				+ preferences.getString(NOTIFY, "20")
				+ "\n";
		
		settingsDebugMessage += "Mute Total at: "
				+ preferences.getBoolean(MUTE_TOTAL, false)
				+ "\n";
		
		settingsDebugMessage += "Total notify at: "
				+ preferences.getString(TOTAL_NOTIFY, "100")
				+ "\n";
		
		settingsDebugMessage += "Export: "
				+ preferences.getBoolean(PreferencesActivity.EXPORT, false)
				+ "\n";
		
		settingsDebugMessage += "Email: "
				+ preferences.getString(PreferencesActivity.EMAIL, "NOT SET")
				+ "\n";
		
		settingsDebugMessage += "Timestamp: "
				+ preferences.getBoolean(PreferencesActivity.TIMESTAMP, true)
				+ "\n";
		
		settingsDebugMessage += "Show Percent: "
				+ preferences
						.getBoolean(PreferencesActivity.SHOW_PERCENT, true)
				+ "\n";
		
		settingsDebugMessage += "Show Num: "
				+ preferences.getBoolean(PreferencesActivity.SHOW_NUM, true)
				+ "\n";
		Log.d(TAG, settingsDebugMessage);
	}

}
