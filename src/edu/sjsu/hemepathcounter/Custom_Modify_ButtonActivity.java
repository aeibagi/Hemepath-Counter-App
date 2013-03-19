package edu.sjsu.hemepathcounter;

import java.util.ArrayList;

import edu.sjsu.hemepathcounter.model.CellButton;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

public class Custom_Modify_ButtonActivity extends Activity implements
		OnItemSelectedListener, View.OnClickListener {

	ImageButton speaker_button;
	Spinner sound_dropDown_menu;
	int SoundIndex;
	ArrayList<Integer> soundHolder = new ArrayList<Integer>();
	private CellButton modifiedButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_custom__modify__button);
		modifiedButton = getIntent().getParcelableExtra("button");
		initialize();
	}

	private void initialize() {

		soundHolder.add(R.raw.sounds_1015_news_bringer);
		soundHolder.add(R.raw.sounds_1066_may_i_have_your_attention);
		soundHolder.add(R.raw.sounds_824_twirl);
		soundHolder.add(R.raw.sounds_874_gets_in_the_way);
		soundHolder.add(R.raw.sounds_882_solemn);
		soundHolder.add(R.raw.sounds_898_braking);
		soundHolder.add(R.raw.sounds_900_you_know);
		soundHolder.add(R.raw.sounds_902_oh_boy);
		soundHolder.add(R.raw.sounds_908_sting);
		soundHolder.add(R.raw.sounds_917_communication_channel);
		soundHolder.add(R.raw.sounds_930_conclusion);
		soundHolder.add(R.raw.sounds_931_whatever);
		soundHolder.add(R.raw.sounds_949_you_wouldnt_believe);
		soundHolder.add(R.raw.sounds_951_pedantic);
		soundHolder.add(R.raw.sounds_998_awareness);

		ArrayAdapter<CharSequence> SpinnerAdapter = ArrayAdapter
				.createFromResource(this, R.array.SoundArray,
						android.R.layout.simple_spinner_item);
		SpinnerAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		sound_dropDown_menu = (Spinner) findViewById(R.id.SP_Sound_menu);
		speaker_button = (ImageButton) findViewById(R.id.ibSpeakerIcon);
		speaker_button.setOnClickListener(this);
		sound_dropDown_menu.setAdapter(SpinnerAdapter);
		sound_dropDown_menu.setOnItemSelectedListener(this);

		if(modifiedButton!=null){
			((EditText) findViewById(R.id.edt_Enter_name_Custom_modify)).setText(modifiedButton.getName());
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.custom__modify__button, menu);
		return true;
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		SoundIndex = arg0.getSelectedItemPosition();

	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.ibSpeakerIcon:
			MediaPlayer myMediaPlayer = MediaPlayer.create(
					Custom_Modify_ButtonActivity.this,
					soundHolder.get(SoundIndex));
			myMediaPlayer.start();
			break;
		}

	}

}
