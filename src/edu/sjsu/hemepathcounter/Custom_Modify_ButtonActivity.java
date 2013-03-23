package edu.sjsu.hemepathcounter;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;
import edu.sjsu.hemepathcounter.model.ButtonHolder;
import edu.sjsu.hemepathcounter.model.CellButton;

public class Custom_Modify_ButtonActivity extends Activity implements
		OnItemSelectedListener, View.OnClickListener {

	ImageButton iB_Color_Green, iB_Color_orage, iB_blue_green_family_26878e,
	            iB_Color_yellow, iB_blue_purple_6666cc, iB_orangefamily_ffae61,
	            iB_greenfamily_00cc99, iB_purpule_663366, iB_dark_oragne_d1692c_072,
	            iB_darker_purple_5861be, iB_gray_familyone_adafb1_072, 
	            iB_light_bluefamily_5ddcd3_072, iB_light_blueish_81b3ff_072, 
	            iB_light_brown_c78540_072, iB_light_orange_ffae61_072, 
	            iB_light_grayblue_8387aa_072, iB_light_pink_fb97c9_072,
	            iB_light_gray_9198aa_072, iB_light_purple_d28dfa_072, 
	            iB_light_whitegray_e7d2ef_072, iB_lighter_pinkpurple_cc6699_072,
	            iB_veylight_green_ffff99_072;
	ImageButton speaker_button;
	Spinner sound_dropDown_menu;
	int SoundIndex, selectedSound, SelectedButtonBackground;
	
	String mode;
	
	Button btn_creat, btn_modify, btn_exit;
	EditText enter_name_for_customModify_buttons;
	ImageButton selectedButton;
	ArrayList<Integer> soundHolder = new ArrayList<Integer>();
	private CellButton modifiedButton;
	MediaPlayer myMediaPlayer;
	Drawable SaveBackGroundresource;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_custom__modify__button);
		modifiedButton = getIntent().getParcelableExtra("button");
		mode = getIntent().getStringExtra("ModifyorCustom");
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

		//set up the cImagebuttons for the colors:
		iB_Color_Green = (ImageButton) findViewById(R.id.iB_Color_Green);	
		iB_Color_orage = (ImageButton) findViewById(R.id.iB_Color_orage);
		iB_blue_green_family_26878e = (ImageButton) findViewById(R.id.iB_blue_green_family_26878e);
		iB_Color_yellow = (ImageButton) findViewById(R.id.iB_Color_yellow); 
		iB_blue_purple_6666cc = (ImageButton) findViewById(R.id.iB_blue_purple_6666cc); 
		iB_orangefamily_ffae61 = (ImageButton) findViewById(R.id.iB_orangefamily_ffae61);
        iB_greenfamily_00cc99 =(ImageButton) findViewById(R.id.iB_greenfamily_00cc99); 
        iB_purpule_663366 = (ImageButton) findViewById(R.id.iB_purpule_663366); 
        iB_dark_oragne_d1692c_072 = (ImageButton) findViewById(R.id.iB_dark_oragne_d1692c_072);
        iB_darker_purple_5861be =(ImageButton) findViewById(R.id.iB_darker_purple_5861be); 
        iB_gray_familyone_adafb1_072= (ImageButton) findViewById(R.id.iB_gray_familyone_adafb1_072); 
        iB_light_bluefamily_5ddcd3_072 = (ImageButton) findViewById(R.id.iB_light_bluefamily_5ddcd3_072);
        iB_light_blueish_81b3ff_072 =(ImageButton) findViewById(R.id.iB_light_blueish_81b3ff_072); 
        iB_light_brown_c78540_072 = (ImageButton) findViewById(R.id.iB_light_brown_c78540_072); 
        iB_light_orange_ffae61_072 = (ImageButton) findViewById(R.id.iB_light_orange_ffae61_072); 
        iB_light_grayblue_8387aa_072 = (ImageButton) findViewById(R.id.iB_light_grayblue_8387aa_072);
        iB_light_pink_fb97c9_072 =(ImageButton) findViewById(R.id.iB_light_pink_fb97c9_072);
        iB_light_gray_9198aa_072 = (ImageButton) findViewById(R.id.iB_light_gray_9198aa_072); 
        iB_light_purple_d28dfa_072 = (ImageButton) findViewById(R.id.iB_light_purple_d28dfa_072);
        iB_light_whitegray_e7d2ef_072 = (ImageButton) findViewById(R.id.iB_light_whitegray_e7d2ef_072); 
        iB_lighter_pinkpurple_cc6699_072 = (ImageButton) findViewById(R.id.iB_lighter_pinkpurple_cc6699_072);
        iB_veylight_green_ffff99_072 = (ImageButton) findViewById(R.id.iB_veylight_green_ffff99_072);
		
        iB_Color_Green.setOnClickListener(this);
        iB_Color_orage.setOnClickListener(this);
        iB_blue_green_family_26878e.setOnClickListener(this);
        iB_Color_yellow.setOnClickListener(this);
        iB_blue_purple_6666cc.setOnClickListener(this);
        iB_orangefamily_ffae61.setOnClickListener(this);
        iB_greenfamily_00cc99.setOnClickListener(this); 
        iB_purpule_663366.setOnClickListener(this);
        iB_dark_oragne_d1692c_072.setOnClickListener(this);
        iB_darker_purple_5861be.setOnClickListener(this);
        iB_gray_familyone_adafb1_072.setOnClickListener(this);
        iB_light_bluefamily_5ddcd3_072.setOnClickListener(this);
        iB_light_blueish_81b3ff_072.setOnClickListener(this); 
        iB_light_brown_c78540_072.setOnClickListener(this); 
        iB_light_orange_ffae61_072.setOnClickListener(this); 
        iB_light_grayblue_8387aa_072.setOnClickListener(this);
        iB_light_pink_fb97c9_072.setOnClickListener(this);
        iB_light_gray_9198aa_072.setOnClickListener(this); 
        iB_light_purple_d28dfa_072.setOnClickListener(this);
        iB_light_whitegray_e7d2ef_072.setOnClickListener(this); 
        iB_lighter_pinkpurple_cc6699_072.setOnClickListener(this);
        iB_veylight_green_ffff99_072.setOnClickListener(this);
        
        SaveBackGroundresource = iB_Color_Green.getBackground();
        
        selectedButton = iB_Color_Green;
        selectedButton.setBackgroundResource(R.drawable.ib_border);
        SelectedButtonBackground = R.id.iB_Color_Green;
        
        selectedSound = soundHolder.get(0);
        
        btn_creat = (Button) findViewById(R.id.btn_create_custom_Button);
        btn_creat.setOnClickListener(this);
        btn_modify = (Button) findViewById(R.id.btn_modify_custom_button);
        btn_modify.setOnClickListener(this);
        btn_exit = (Button)findViewById(R.id.btn_exit_custom_button);
        btn_exit.setOnClickListener(this);
		
        if(mode.equals("Custom"))
        {
        	btn_modify.setBackgroundResource(R.drawable.button_style_gray);
        	btn_modify.setClickable(false);
        }
        else if(mode.equals("Modify"))
        {
        	btn_creat.setBackgroundResource(R.drawable.button_style_gray);
        	btn_creat.setClickable(false);
        }
        
        enter_name_for_customModify_buttons = (EditText) findViewById(R.id.edt_Enter_name_Custom_modify);
        
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

		if (modifiedButton != null) {
			((EditText) findViewById(R.id.edt_Enter_name_Custom_modify))
					.setText(modifiedButton.getName());
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
		selectedSound = soundHolder.get(SoundIndex);

	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

	@SuppressLint("NewApi")
	@Override
	public void onClick(View v) {

		
		switch (v.getId()) {
		case R.id.ibSpeakerIcon:
			if(myMediaPlayer != null)
			{
				myMediaPlayer.release();
			}
			myMediaPlayer = MediaPlayer.create(
					Custom_Modify_ButtonActivity.this,
					soundHolder.get(SoundIndex));
			
			myMediaPlayer.start();
			break;
		case R.id.iB_Color_Green:
			selectedButton.setBackground(SaveBackGroundresource);
			selectedButton = iB_Color_Green;
			iB_Color_Green.setBackgroundResource(R.drawable.ib_border);
			SelectedButtonBackground = R.id.iB_Color_Green;
			break;
			
		case R.id.iB_Color_orage:
			selectedButton.setBackground(SaveBackGroundresource);
			selectedButton = iB_Color_orage;
			iB_Color_orage.setBackgroundResource(R.drawable.ib_border);
			SelectedButtonBackground = R.id.iB_Color_orage;
			break;
		case R.id.iB_blue_green_family_26878e:
			selectedButton.setBackground(SaveBackGroundresource);
			selectedButton = iB_blue_green_family_26878e;
			iB_blue_green_family_26878e.setBackgroundResource(R.drawable.ib_border);
			SelectedButtonBackground = R.id.iB_blue_green_family_26878e;
			break;
		case R.id.iB_Color_yellow:
			selectedButton.setBackground(SaveBackGroundresource);
			selectedButton = iB_Color_yellow;
			iB_Color_yellow.setBackgroundResource(R.drawable.ib_border);
			SelectedButtonBackground = R.id.iB_Color_yellow;
			break;
		case R.id.iB_blue_purple_6666cc:
			selectedButton.setBackground(SaveBackGroundresource);
			selectedButton = iB_blue_purple_6666cc;
			iB_blue_purple_6666cc.setBackgroundResource(R.drawable.ib_border);
			SelectedButtonBackground = R.id.iB_blue_purple_6666cc;
			break;
		case R.id.iB_orangefamily_ffae61:
			selectedButton.setBackground(SaveBackGroundresource);
			selectedButton = iB_orangefamily_ffae61;
			iB_orangefamily_ffae61.setBackgroundResource(R.drawable.ib_border);
			SelectedButtonBackground = R.id.iB_orangefamily_ffae61;
			break;
		case R.id.iB_greenfamily_00cc99:
			selectedButton.setBackground(SaveBackGroundresource);
			selectedButton = iB_greenfamily_00cc99;
			iB_greenfamily_00cc99.setBackgroundResource(R.drawable.ib_border);
			SelectedButtonBackground = R.id.iB_greenfamily_00cc99;
			break;
		case R.id.iB_purpule_663366:
			selectedButton.setBackground(SaveBackGroundresource);
			selectedButton = iB_purpule_663366;
			iB_purpule_663366.setBackgroundResource(R.drawable.ib_border);
			SelectedButtonBackground = R.id.iB_purpule_663366;
			break;
		case R.id.iB_dark_oragne_d1692c_072:
			selectedButton.setBackground(SaveBackGroundresource);
			selectedButton = iB_dark_oragne_d1692c_072;
			iB_dark_oragne_d1692c_072.setBackgroundResource(R.drawable.ib_border);
			SelectedButtonBackground = R.id.iB_dark_oragne_d1692c_072;
			break;
		case R.id.iB_darker_purple_5861be:
			selectedButton.setBackground(SaveBackGroundresource);
			selectedButton = iB_darker_purple_5861be;
			iB_darker_purple_5861be.setBackgroundResource(R.drawable.ib_border);
			SelectedButtonBackground = R.id.iB_darker_purple_5861be;
			break;
		case R.id.iB_gray_familyone_adafb1_072:
			selectedButton.setBackground(SaveBackGroundresource);
			selectedButton = iB_gray_familyone_adafb1_072;
			iB_gray_familyone_adafb1_072.setBackgroundResource(R.drawable.ib_border);
			SelectedButtonBackground = R.id.iB_gray_familyone_adafb1_072;
			break;
		case R.id.iB_light_bluefamily_5ddcd3_072:
			selectedButton.setBackground(SaveBackGroundresource);
			selectedButton = iB_light_bluefamily_5ddcd3_072;
			iB_light_bluefamily_5ddcd3_072.setBackgroundResource(R.drawable.ib_border);
			SelectedButtonBackground = R.id.iB_light_bluefamily_5ddcd3_072;
			break;
		case R.id.iB_light_blueish_81b3ff_072:
			selectedButton.setBackground(SaveBackGroundresource);
			selectedButton = iB_light_blueish_81b3ff_072;
			iB_light_blueish_81b3ff_072.setBackgroundResource(R.drawable.ib_border);
			SelectedButtonBackground = R.id.iB_light_blueish_81b3ff_072;
			break;
		case R.id.iB_light_brown_c78540_072:
			selectedButton.setBackground(SaveBackGroundresource);
			selectedButton = iB_light_brown_c78540_072;
			iB_light_brown_c78540_072.setBackgroundResource(R.drawable.ib_border);
			SelectedButtonBackground = R.id.iB_light_brown_c78540_072;
			break;
		case  R.id.iB_light_orange_ffae61_072:
			selectedButton.setBackground(SaveBackGroundresource);
			selectedButton = iB_light_orange_ffae61_072;
			iB_light_orange_ffae61_072.setBackgroundResource(R.drawable.ib_border);
			SelectedButtonBackground = R.id.iB_light_orange_ffae61_072;
			break;
		case  R.id.iB_light_grayblue_8387aa_072:
			selectedButton.setBackground(SaveBackGroundresource);
			selectedButton = iB_light_grayblue_8387aa_072;
			iB_light_grayblue_8387aa_072.setBackgroundResource(R.drawable.ib_border);
			SelectedButtonBackground = R.id.iB_light_grayblue_8387aa_072;
			break;
		case  R.id.iB_light_pink_fb97c9_072:
			selectedButton.setBackground(SaveBackGroundresource);
			selectedButton = iB_light_pink_fb97c9_072;
			iB_light_pink_fb97c9_072.setBackgroundResource(R.drawable.ib_border);
			SelectedButtonBackground = R.id.iB_light_pink_fb97c9_072;
			break;
		case  R.id.iB_light_gray_9198aa_072:
			selectedButton.setBackground(SaveBackGroundresource);
			selectedButton = iB_light_gray_9198aa_072;
			iB_light_gray_9198aa_072.setBackgroundResource(R.drawable.ib_border);
			SelectedButtonBackground = R.id.iB_light_gray_9198aa_072;
			break;
		case  R.id.iB_light_purple_d28dfa_072:
			selectedButton.setBackground(SaveBackGroundresource);
			selectedButton = iB_light_purple_d28dfa_072;
			iB_light_purple_d28dfa_072.setBackgroundResource(R.drawable.ib_border);
			SelectedButtonBackground = R.id.iB_light_purple_d28dfa_072;
			break;
		case  R.id.iB_light_whitegray_e7d2ef_072:
			selectedButton.setBackground(SaveBackGroundresource);
			selectedButton = iB_light_whitegray_e7d2ef_072;
			iB_light_whitegray_e7d2ef_072.setBackgroundResource(R.drawable.ib_border);
			SelectedButtonBackground = R.id.iB_light_whitegray_e7d2ef_072;
			break;
		case  R.id.iB_lighter_pinkpurple_cc6699_072:
			selectedButton.setBackground(SaveBackGroundresource);
			selectedButton = iB_lighter_pinkpurple_cc6699_072;
			iB_lighter_pinkpurple_cc6699_072.setBackgroundResource(R.drawable.ib_border);
			SelectedButtonBackground =  R.id.iB_lighter_pinkpurple_cc6699_072;
			break;
		case  R.id.iB_veylight_green_ffff99_072:
			selectedButton.setBackground(SaveBackGroundresource);
			selectedButton = iB_veylight_green_ffff99_072;
			iB_veylight_green_ffff99_072.setBackgroundResource(R.drawable.ib_border);
			SelectedButtonBackground = R.id.iB_veylight_green_ffff99_072;
			break;
			
		case R.id.btn_create_custom_Button:
			String name = enter_name_for_customModify_buttons.getText().toString().trim();
			if (name.length() > 0) 
			{
				CellButton customCell = new CellButton(name, "",selectedSound, SelectedButtonBackground);
				// I need a button holder?
				ButtonHolder btn_holder = new ButtonHolder();
				btn_holder.addCustomButton(customCell);
			}
			else 
			{
				Toast.makeText(this, "You did Enter a Name for the new Button",
						Toast.LENGTH_SHORT).show();
			}
			
			break;
		case R.id.btn_modify_custom_button:
			break;
		case R.id.btn_exit_custom_button:
			finish();
			break;
			
		}

	}

}
