package edu.sjsu.hemepathcounter;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import edu.sjsu.hemepathcounter.model.ButtonHolder;
import edu.sjsu.hemepathcounter.model.CellButton;
import edu.sjsu.hemepathcounter.model.CellButton.CellType;

public class Custom_Modify_ButtonActivity extends Activity implements
		OnItemSelectedListener, View.OnClickListener {
	private static final String TAG = "Custom_Modify_ButtonActivity";
	private FileManager manager;
	private ButtonHolder holder;
	private ImageButton iB_Color_Green, iB_Color_orage,
			iB_Color_yellow,
			iB_blue_purple_6666cc, iB_orangefamily_ffae61,
			iB_greenfamily_00cc99, iB_dark_oragne_d1692c_072,
			iB_darker_purple_5861be, iB_gray_familyone_adafb1_072,
			iB_light_bluefamily_5ddcd3_072, iB_light_blueish_81b3ff_072,
			iB_light_brown_c78540_072, iB_light_orange_ffae61_072,
			iB_light_grayblue_8387aa_072, iB_light_pink_fb97c9_072,
			iB_light_gray_9198aa_072, iB_light_purple_d28dfa_072,
			iB_light_whitegray_e7d2ef_072, iB_lighter_pinkpurple_cc6699_072,
			iB_veylight_green_ffff99_072;
	private ImageButton speaker_button;
	private Spinner sound_dropDown_menu;
	// private int SoundIndex, selectedSound, SelectedButtonBackground;
	private int SoundIndex;

	private Integer selectedSound, SelectedButtonBackground;

	private String mode;

	private Button btn_creat, btn_modify, btn_exit;
	private RadioGroup cellTypeGroup;
	private EditText enter_name_for_customModify_buttons;
	private EditText enter_abbr_for_customModify_buttons;
	private ImageButton selectedButton;
	private ArrayList<Integer> soundHolder = new ArrayList<Integer>();
	private CellButton modifiedButton;
	private MediaPlayer myMediaPlayer;
	private Drawable SaveBackGroundresource;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "Starting new Custom Modify ButtonActivity");
		setContentView(R.layout.activity_custom__modify__button);
		modifiedButton = getIntent().getParcelableExtra("button");
		mode = getIntent().getStringExtra("mode");
		if (mode.equals("Custom")) {
			setTitle("Create a Custom Button");
		}
		else if (mode.equals("Modify"))
		{
			setTitle("Modify Existing Button");
		}
		initialize();
	}

	private void initialize() {

		manager = FileManager.getInstance(getApplicationContext());
		soundHolder.add(R.raw.arpeggio);
		soundHolder.add(R.raw.attention);
		soundHolder.add(R.raw.beep1);
		soundHolder.add(R.raw.beep2);
		soundHolder.add(R.raw.believe);
		soundHolder.add(R.raw.brake);
		soundHolder.add(R.raw.close);
		soundHolder.add(R.raw.dark);
		soundHolder.add(R.raw.dramatic);
		soundHolder.add(R.raw.here);
		soundHolder.add(R.raw.high);
		soundHolder.add(R.raw.know);
		soundHolder.add(R.raw.look);
		soundHolder.add(R.raw.question);
		soundHolder.add(R.raw.shoot);
		soundHolder.add(R.raw.sting);
		//use stopper for total counts
		//soundHolder.add(R.raw.stopper); 
		soundHolder.add(R.raw.way);

		// set up the cImagebuttons for the colors:
		iB_Color_Green = (ImageButton) findViewById(R.id.iB_Color_Green);
		iB_Color_orage = (ImageButton) findViewById(R.id.iB_Color_orage);
		iB_Color_yellow = (ImageButton) findViewById(R.id.iB_Color_yellow);
		iB_blue_purple_6666cc = (ImageButton) findViewById(R.id.iB_blue_purple_6666cc);
		iB_orangefamily_ffae61 = (ImageButton) findViewById(R.id.iB_orangefamily_ffae61);
		iB_greenfamily_00cc99 = (ImageButton) findViewById(R.id.iB_greenfamily_00cc99);
		iB_dark_oragne_d1692c_072 = (ImageButton) findViewById(R.id.iB_dark_oragne_d1692c_048);
		iB_darker_purple_5861be = (ImageButton) findViewById(R.id.iB_darker_purple_5861be);
		iB_gray_familyone_adafb1_072 = (ImageButton) findViewById(R.id.iB_gray_familyone_adafb1_072);
		iB_light_bluefamily_5ddcd3_072 = (ImageButton) findViewById(R.id.iB_light_bluefamily_5ddcd3_072);
		iB_light_blueish_81b3ff_072 = (ImageButton) findViewById(R.id.iB_light_blueish_81b3ff_072);
		iB_light_brown_c78540_072 = (ImageButton) findViewById(R.id.iB_light_brown_c78540_072);
		iB_light_orange_ffae61_072 = (ImageButton) findViewById(R.id.iB_light_orange_ffae61_072);
		iB_light_grayblue_8387aa_072 = (ImageButton) findViewById(R.id.iB_light_grayblue_8387aa_072);
		iB_light_pink_fb97c9_072 = (ImageButton) findViewById(R.id.iB_light_pink_fb97c9_072);
		iB_light_gray_9198aa_072 = (ImageButton) findViewById(R.id.iB_light_gray_9198aa_072);
		iB_light_purple_d28dfa_072 = (ImageButton) findViewById(R.id.iB_light_purple_d28dfa_072);
		iB_light_whitegray_e7d2ef_072 = (ImageButton) findViewById(R.id.iB_light_whitegray_e7d2ef_072);
		iB_lighter_pinkpurple_cc6699_072 = (ImageButton) findViewById(R.id.iB_lighter_pinkpurple_cc6699_072);
		iB_veylight_green_ffff99_072 = (ImageButton) findViewById(R.id.iB_veylight_green_ffff99_072);

		iB_Color_Green.setOnClickListener(this);
		iB_Color_orage.setOnClickListener(this);
		iB_Color_yellow.setOnClickListener(this);
		iB_blue_purple_6666cc.setOnClickListener(this);
		iB_orangefamily_ffae61.setOnClickListener(this);
		iB_greenfamily_00cc99.setOnClickListener(this);
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
		SelectedButtonBackground = R.drawable.green;

		selectedSound = soundHolder.get(0);

		btn_creat = (Button) findViewById(R.id.btn_create_custom_Button);
		btn_creat.setOnClickListener(this);
		btn_modify = (Button) findViewById(R.id.btn_modify_custom_button);
		btn_modify.setOnClickListener(this);
		btn_exit = (Button) findViewById(R.id.btn_exit_custom_button);
		btn_exit.setOnClickListener(this);

		if (mode.equals("Custom")) {
			btn_modify.setBackgroundResource(R.drawable.button_style_gray);
			btn_modify.setClickable(false);
		} else if (mode.equals("Modify")) {
			btn_creat.setBackgroundResource(R.drawable.button_style_gray);
			btn_creat.setClickable(false);
		}

		enter_name_for_customModify_buttons = (EditText) findViewById(R.id.edt_Enter_name_Custom_modify);
		enter_abbr_for_customModify_buttons = (EditText) findViewById(R.id.edt_Enter_abbr_Custom_modify);

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
			((EditText)findViewById(R.id.edt_Enter_abbr_Custom_modify))
			.setText(modifiedButton.getAbbr());
		}
		
		cellTypeGroup = (RadioGroup) findViewById(R.id.radioCellType);
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
			if (myMediaPlayer != null) {
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
			SelectedButtonBackground = R.drawable.green;
			break;

		case R.id.iB_Color_orage:
			selectedButton.setBackground(SaveBackGroundresource);
			selectedButton = iB_Color_orage;
			iB_Color_orage.setBackgroundResource(R.drawable.ib_border);
			SelectedButtonBackground = R.drawable.orange;
			break;
		case R.id.iB_Color_yellow:
			selectedButton.setBackground(SaveBackGroundresource);
			selectedButton = iB_Color_yellow;
			iB_Color_yellow.setBackgroundResource(R.drawable.ib_border);
			SelectedButtonBackground = R.drawable.yellow;
			break;
		case R.id.iB_blue_purple_6666cc:
			selectedButton.setBackground(SaveBackGroundresource);
			selectedButton = iB_blue_purple_6666cc;
			iB_blue_purple_6666cc.setBackgroundResource(R.drawable.ib_border);
			SelectedButtonBackground = R.drawable.blue_purple_6666cc;
			break;
		case R.id.iB_orangefamily_ffae61:
			selectedButton.setBackground(SaveBackGroundresource);
			selectedButton = iB_orangefamily_ffae61;
			iB_orangefamily_ffae61.setBackgroundResource(R.drawable.ib_border);
			SelectedButtonBackground = R.drawable.orangefamily_ffae61;
			break;
		case R.id.iB_greenfamily_00cc99:
			selectedButton.setBackground(SaveBackGroundresource);
			selectedButton = iB_greenfamily_00cc99;
			iB_greenfamily_00cc99.setBackgroundResource(R.drawable.ib_border);
			SelectedButtonBackground = R.drawable.greenfamily_00cc99;
			break;
		case R.id.iB_dark_oragne_d1692c_048:
			selectedButton.setBackground(SaveBackGroundresource);
			selectedButton = iB_dark_oragne_d1692c_072;
			iB_dark_oragne_d1692c_072
					.setBackgroundResource(R.drawable.ib_border);
			SelectedButtonBackground = R.drawable.dark_oragne_d1692c_048;
			break;
		case R.id.iB_darker_purple_5861be:
			selectedButton.setBackground(SaveBackGroundresource);
			selectedButton = iB_darker_purple_5861be;
			iB_darker_purple_5861be.setBackgroundResource(R.drawable.ib_border);
			SelectedButtonBackground = R.drawable.darker_purple_5861be;
			break;
		case R.id.iB_gray_familyone_adafb1_072:
			selectedButton.setBackground(SaveBackGroundresource);
			selectedButton = iB_gray_familyone_adafb1_072;
			iB_gray_familyone_adafb1_072
					.setBackgroundResource(R.drawable.ib_border);
			SelectedButtonBackground = R.drawable.gray_familyone_adafb1_048;
			break;
		case R.id.iB_light_bluefamily_5ddcd3_072:
			selectedButton.setBackground(SaveBackGroundresource);
			selectedButton = iB_light_bluefamily_5ddcd3_072;
			iB_light_bluefamily_5ddcd3_072
					.setBackgroundResource(R.drawable.ib_border);
			SelectedButtonBackground = R.drawable.light_bluefamily_5ddcd3_048;
			break;
		case R.id.iB_light_blueish_81b3ff_072:
			selectedButton.setBackground(SaveBackGroundresource);
			selectedButton = iB_light_blueish_81b3ff_072;
			iB_light_blueish_81b3ff_072
					.setBackgroundResource(R.drawable.ib_border);
			SelectedButtonBackground = R.drawable.light_blueish_81b3ff_048;
			break;
		case R.id.iB_light_brown_c78540_072:
			selectedButton.setBackground(SaveBackGroundresource);
			selectedButton = iB_light_brown_c78540_072;
			iB_light_brown_c78540_072
					.setBackgroundResource(R.drawable.ib_border);
			SelectedButtonBackground = R.drawable.light_brown_c78540_048;
			break;
		case R.id.iB_light_orange_ffae61_072:
			selectedButton.setBackground(SaveBackGroundresource);
			selectedButton = iB_light_orange_ffae61_072;
			iB_light_orange_ffae61_072
					.setBackgroundResource(R.drawable.ib_border);
			SelectedButtonBackground = R.drawable.light_orange_ffae61_048;
			break;
		case R.id.iB_light_grayblue_8387aa_072:
			selectedButton.setBackground(SaveBackGroundresource);
			selectedButton = iB_light_grayblue_8387aa_072;
			iB_light_grayblue_8387aa_072
					.setBackgroundResource(R.drawable.ib_border);
			SelectedButtonBackground = R.drawable.light_grayblue_8387aa_048;
			break;
		case R.id.iB_light_pink_fb97c9_072:
			selectedButton.setBackground(SaveBackGroundresource);
			selectedButton = iB_light_pink_fb97c9_072;
			iB_light_pink_fb97c9_072
					.setBackgroundResource(R.drawable.ib_border);
			SelectedButtonBackground = R.drawable.light_pink_fb97c9_048;
			break;
		case R.id.iB_light_gray_9198aa_072:
			selectedButton.setBackground(SaveBackGroundresource);
			selectedButton = iB_light_gray_9198aa_072;
			iB_light_gray_9198aa_072
					.setBackgroundResource(R.drawable.ib_border);
			SelectedButtonBackground = R.drawable.light_gray_9198aa_048;
			break;
		case R.id.iB_light_purple_d28dfa_072:
			selectedButton.setBackground(SaveBackGroundresource);
			selectedButton = iB_light_purple_d28dfa_072;
			iB_light_purple_d28dfa_072
					.setBackgroundResource(R.drawable.ib_border);
			SelectedButtonBackground = R.drawable.light_purple_d28dfa_048;
			break;
		case R.id.iB_light_whitegray_e7d2ef_072:
			selectedButton.setBackground(SaveBackGroundresource);
			selectedButton = iB_light_whitegray_e7d2ef_072;
			iB_light_whitegray_e7d2ef_072
					.setBackgroundResource(R.drawable.ib_border);
			SelectedButtonBackground = R.drawable.light_whitegray_e7d2ef_048;
			break;
		case R.id.iB_lighter_pinkpurple_cc6699_072:
			selectedButton.setBackground(SaveBackGroundresource);
			selectedButton = iB_lighter_pinkpurple_cc6699_072;
			iB_lighter_pinkpurple_cc6699_072
					.setBackgroundResource(R.drawable.ib_border);
			SelectedButtonBackground = R.drawable.lighter_pinkpurple_cc6699_048;
			break;
		case R.id.iB_veylight_green_ffff99_072:
			selectedButton.setBackground(SaveBackGroundresource);
			selectedButton = iB_veylight_green_ffff99_072;
			iB_veylight_green_ffff99_072
					.setBackgroundResource(R.drawable.ib_border);
			SelectedButtonBackground = R.drawable.veylight_green_ffff99_048;
			break;

		case R.id.btn_create_custom_Button:
			String name = enter_name_for_customModify_buttons.getText()
					.toString().trim();
			String abbr = enter_abbr_for_customModify_buttons.getText()
					.toString().trim();
			CellType type = null;
			switch (cellTypeGroup.getCheckedRadioButtonId()) {
			case R.id.myeloidButton:
				type = CellType.MYELOID;
				break;
			case R.id.erythroidButton:
				type = CellType.ERYTHROID;
				break;
			case R.id.otherButton:
				type = CellType.OTHER;
				break;
			default:
				type = CellType.OTHER;
				break;
			}
			if (name.length() > 0 && abbr.length() > 0) {
				Log.d(TAG, "Saving newly created button.");
				boolean sameName = false;
				CellButton customCell;
				for (CellButton cell : manager.getButtonHolder().getCustomButtons())
				{
					if(name.equalsIgnoreCase(cell.getName()))
					{
						sameName = true;
					}
				}
				if(sameName)
				{
					Toast.makeText(this,
							"Button " + name + " already exists.",
							Toast.LENGTH_SHORT).show();
				}
				else
				{	
					if (abbr.isEmpty()) {
						customCell = new CellButton(name, name, selectedSound,
								SelectedButtonBackground, type);
					} else {
						customCell = new CellButton(name, abbr, selectedSound,
								SelectedButtonBackground, type);
					}
					holder = manager.getButtonHolder();
					holder.addCustomButton(customCell);
					manager.updateButtonHolder(holder);
					Intent intent = new Intent(Custom_Modify_ButtonActivity.this,
							NewCounterActivity.class);
					intent.putExtra("Created_custom_button", true);
					intent.putExtra("Custom_Button", customCell);
					setResult(Activity.RESULT_OK, intent);
					finish();
				}
			} else {
				Toast.makeText(this,
						"You did Not Enter a Name or abbreviation for the new Button",
						Toast.LENGTH_SHORT).show();
			}

			break;
		case R.id.btn_modify_custom_button:
			String ModifiedName = enter_name_for_customModify_buttons.getText()
					.toString().trim();
			String ModifiedAbrr = enter_abbr_for_customModify_buttons.getText().toString().trim();
			holder = manager.getButtonHolder();
			if (ModifiedName.length() > 0 && ModifiedAbrr.length() > 0) {
				Log.d(TAG, "Passing back modified button.");
				Intent intent = new Intent(Custom_Modify_ButtonActivity.this,
						NewCounterActivity.class);
				intent.putExtra("Modified_button", true);
				intent.putExtra("newName", ModifiedName);
				intent.putExtra("newAbrr", ModifiedAbrr);
				intent.putExtra("newSound", selectedSound);
				intent.putExtra("newColor", SelectedButtonBackground);
				setResult(Activity.RESULT_OK, intent);
				finish();
			} else {
				Toast.makeText(this, "You did Not Enter a Name or Abbreviation for the Button",
						Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.btn_exit_custom_button:
			finish();
			break;

		}

	}

}
