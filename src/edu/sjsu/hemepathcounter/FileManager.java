package edu.sjsu.hemepathcounter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;
import edu.sjsu.hemepathcounter.model.ButtonHolder;
import edu.sjsu.hemepathcounter.model.CellButton;
import edu.sjsu.hemepathcounter.model.Counter;
import edu.sjsu.hemepathcounter.model.CounterHolder;
import edu.sjsu.hemepathcounter.model.DataHolder;

public final class FileManager {
	private static final String TAG = "FileManager";
	private final String dataHolderFileName = "dataHolderFile.dat";
	private final String counterHolderFileName = "counterHolderFile.dat";
	private final String buttonHolderFileName = "buttonHolderFile.dat";

	private CounterHolder counterHolder = null;
	private DataHolder dataHolder = null;
	private ButtonHolder buttonHolder = null;
	private Context context;
	private static FileManager sInstance;

	private FileManager(Context c) {
		Log.d(TAG, "Creating new FileManager.");
		context = c;		
	}

	public static synchronized FileManager getInstance(Context context) {
		Log.d(TAG, "Getting FileManager.");
        if (sInstance == null) {
            sInstance = new FileManager(context);
        }
        
        return sInstance;
    }  
    
	public CounterHolder getCounterHolder() {
		// Load the counter holder if necessary
		if (counterHolder == null) {
			Log.d(TAG, "Loading counter from file.");
			counterHolder = new CounterHolder();
			try {
				// Fill counterHolder
				FileInputStream fis = context
						.openFileInput(counterHolderFileName);
				InputStreamReader isr = new InputStreamReader(fis);
				BufferedReader buffreader = new BufferedReader(isr);
				String JSONString = "";
				String readString = buffreader.readLine();
				while (readString != null) {
					JSONString = JSONString + readString;
					readString = buffreader.readLine();
				}
				if (JSONString.isEmpty()) {
					createCounterHolderFile();
				} else {
					counterHolder.fromJSONObject(new JSONObject(JSONString));
				}
				fis.close();

			} catch (FileNotFoundException e) {
				// The file doesn't exist. Create file with default counters
				createCounterHolderFile();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return counterHolder;
	}

	private void createCounterHolderFile() {
		Log.d(TAG, "Creating counterHolder file.");
		ArrayList<CellButton> buttonList1 = new ArrayList<CellButton>();
		buttonList1.add(new CellButton(context.getResources().getString(
				R.string.Lymphocytes), context.getResources().getString(
				R.string.Lymphocytes_abbr), R.raw.arpeggio,
				R.drawable.light_bluefamily_5ddcd3_048));
		buttonList1.add(new CellButton(context.getResources().getString(
				R.string.Monocytes), context.getResources().getString(
				R.string.Monocytes_abbr),
				R.raw.attention,
				R.drawable.blue_green_family_26878e));
		buttonList1.add(new CellButton(context.getResources().getString(
				R.string.Eosinophils), context.getResources().getString(
				R.string.Eosinophils_abbr), R.raw.beep1,
				R.drawable.blue_purple_6666cc));
		buttonList1.add(new CellButton(context.getResources().getString(
				R.string.Basophils), context.getResources().getString(
				R.string.Basophils_abbr), R.raw.way,
				R.drawable.green));
		buttonList1.add(new CellButton(context.getResources().getString(
				R.string.Neutrophils), context.getResources().getString(
				R.string.Neutrophils_abbr), R.raw.beep2,
				R.drawable.greenfamily_00cc99));
		buttonList1.add(new CellButton(context.getResources().getString(
				R.string.Bands), context.getResources().getString(
				R.string.Bands_abbr), R.raw.brake,
				R.drawable.orange));
		buttonList1.add(new CellButton(context.getResources().getString(
				R.string.Blasts), context.getResources().getString(
				R.string.Blasts_abbr), R.raw.know,
				R.drawable.orangefamily_ffae61));
		buttonList1.add(new CellButton(context.getResources().getString(
				R.string.Reticulocytes), context.getResources().getString(
				R.string.Reticulocytes_abbr), R.raw.dark,
				R.drawable.light_pink_fb97c9_048));

		Counter counter1 = new Counter("Default Counter", buttonList1);

		ArrayList<CellButton> buttonList2 = new ArrayList<CellButton>();

		buttonList2.add(new CellButton(context.getResources().getString(
				R.string.AtypicalLymphocytes), context.getResources()
				.getString(R.string.AtypicalLymphocytes_abbr),
				R.raw.sting, R.drawable.light_blueish_81b3ff_048));
		buttonList2.add(new CellButton(context.getResources().getString(
				R.string.Meta_myelocytes), context.getResources().getString(
				R.string.Meta_myelocytes_abbr),
				R.raw.here, R.drawable.yellow));
		buttonList2.add(new CellButton(context.getResources().getString(
				R.string.Myelocytes), context.getResources().getString(
				R.string.Myelocytes_abbr), R.raw.stopper,
				R.drawable.light_blueish_81b3ff_048));
		buttonList2.add(new CellButton(context.getResources().getString(
				R.string.Promyelocytes), context.getResources().getString(
				R.string.Promyelocytes_abbr), R.raw.high,
				R.drawable.blue_green_family_26878e));
		buttonList2.add(new CellButton(context.getResources().getString(
				R.string.Promonocytes), context.getResources().getString(
				R.string.Promonocytes_abbr),
				R.raw.believe,
				R.drawable.blue_purple_6666cc));
		buttonList2.add(new CellButton(context.getResources().getString(
				R.string.Red_blood_cells), context.getResources().getString(
				R.string.Red_blood_cells_abbr), R.raw.question,
				R.drawable.green));
		buttonList2.add(new CellButton(context.getResources().getString(
				R.string.White_Blood_Cells), context.getResources().getString(
				R.string.White_Blood_Cells_abbr), R.raw.look,
				R.drawable.greenfamily_00cc99));
		buttonList2.add(new CellButton(context.getResources().getString(
				R.string.Plasma_cells), context.getResources().getString(
				R.string.Plasma_cells_abbr), R.raw.arpeggio,
				R.drawable.orange));
		buttonList2.add(new CellButton(context.getResources().getString(
				R.string.Atypical_plasma_cells), context.getResources()
				.getString(R.string.Atypical_plasma_cells_abbr),
				R.raw.attention,
				R.drawable.orangefamily_ffae61));
		buttonList2.add(new CellButton(context.getResources().getString(
				R.string.Mast_cells), context.getResources().getString(
				R.string.Mast_cells_abbr), R.raw.dramatic,
				R.drawable.light_purple_d28dfa_048));
		buttonList2.add(new CellButton(context.getResources().getString(
				R.string.Megakaryocytes), context.getResources().getString(
				R.string.Megakaryocytes_abbr),
				R.raw.know, R.drawable.light_pink_fb97c9_048));

		Counter counter2 = new Counter("High Yield Counter", buttonList2);

		ArrayList<CellButton> buttonList3 = new ArrayList<CellButton>();

		buttonList3.add(new CellButton(context.getResources().getString(
				R.string.Erythroblasts), context.getResources().getString(
				R.string.Erythroblasts_abbr), R.raw.dramatic,
				R.drawable.yellow));
		buttonList3.add(new CellButton(context.getResources().getString(
				R.string.Proerythroblasts), context.getResources().getString(
				R.string.Proerythroblasts_abbr), R.raw.brake,
				R.drawable.blue_green_family_26878e));
		buttonList3.add(new CellButton(context.getResources().getString(
				R.string.T_cells), context.getResources().getString(
				R.string.T_cells_abbr), R.raw.close,
				R.drawable.blue_green_family_26878e));
		buttonList3.add(new CellButton(context.getResources().getString(
				R.string.B_Cells), context.getResources().getString(
				R.string.B_Cells_abbr), R.raw.dark,
				R.drawable.blue_purple_6666cc));
		buttonList3.add(new CellButton(context.getResources().getString(
				R.string.CD4Plus_T_cells), context.getResources().getString(
				R.string.CD4Plus_T_cells_abbr), R.raw.sting,
				R.drawable.green));
		buttonList3.add(new CellButton(context.getResources().getString(
				R.string.CD8Plus_T_cells), context.getResources().getString(
				R.string.CD8Plus_T_cells_abbr),
				R.raw.stopper,
				R.drawable.greenfamily_00cc99));
		buttonList3.add(new CellButton(context.getResources().getString(
				R.string.Reed_Sternberg_cells), context.getResources()
				.getString(R.string.Reed_Sternberg_cells_abbr),
				R.raw.shoot, R.drawable.orange));

		Counter counter3 = new Counter("Mid Yield Counter", buttonList3);
		ArrayList<Counter> list = new ArrayList<Counter>();
		list.add(counter1);
		list.add(counter2);
		list.add(counter3);
		updateCounterHolder(new CounterHolder(list));
	}

	public void updateCounterHolder(CounterHolder newCounterHolder) {
		Log.d(TAG, "Updating CounterHolder file.");
		this.counterHolder = newCounterHolder;
		try {
			FileOutputStream fileOutput = context.openFileOutput(
					counterHolderFileName, Context.MODE_PRIVATE);
			String json = newCounterHolder.toJSONObject().toString();
			OutputStreamWriter writer = new OutputStreamWriter(fileOutput);
			writer.write(json);
			Log.d(TAG, "Closing CounterHolder file.");
			writer.flush();
			writer.close();
			fileOutput.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public DataHolder getDataHolder() {
		// Load the data holder if necessary
		if (dataHolder == null) {
			Log.d(TAG, "Loading dataHolder from file.");
			dataHolder = new DataHolder();
			try {
				// Fill dataHolder
				FileInputStream fis = context.openFileInput(dataHolderFileName);
				InputStreamReader isr = new InputStreamReader(fis);
				BufferedReader buffreader = new BufferedReader(isr);
				String JSONString = "";
				String readString = buffreader.readLine();
				while (readString != null) {
					JSONString = JSONString + readString;
					readString = buffreader.readLine();
				}
				if (JSONString.isEmpty()) {
					createDataHolderFile();
				} else {
					dataHolder.fromJSONObject(new JSONObject(JSONString));
				}
				fis.close();

			} catch (FileNotFoundException e) {
				// The file doesn't exist. Create file with default counters
				createDataHolderFile();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return dataHolder;
	}

	private void createDataHolderFile() {
		Log.d(TAG, "creating dataHolder file.");
		updateDataHolder(new DataHolder());
	}

	public void updateDataHolder(DataHolder newDataHolder) {
		Log.d(TAG, "Updating DataHolder file.");
		this.dataHolder = newDataHolder;
		try {
			FileOutputStream fileOutput = context.openFileOutput(
					dataHolderFileName, Context.MODE_PRIVATE);
			String json = newDataHolder.toJSONObject().toString();
			OutputStreamWriter writer = new OutputStreamWriter(fileOutput);
			writer.write(json);
			Log.d(TAG, "Closing CounterHolder file.");
			writer.flush();
			writer.close();
			fileOutput.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public ButtonHolder getButtonHolder() {
		// Load the button holder if necessary
		if (buttonHolder == null) {
			Log.d(TAG, "Loading buttonHolder from file.");
			buttonHolder = new ButtonHolder();
			try {
				// Fill buttonHolder
				FileInputStream fis = context
						.openFileInput(buttonHolderFileName);
				InputStreamReader isr = new InputStreamReader(fis);
				BufferedReader buffreader = new BufferedReader(isr);
				String JSONString = "";
				String readString = buffreader.readLine();
				while (readString != null) {
					JSONString = JSONString + readString;
					readString = buffreader.readLine();
				}
				if (JSONString.isEmpty()) {
					createButtonHolderFile();
				} else {
					buttonHolder.fromJSONObject(new JSONObject(JSONString));
				}
				fis.close();

			} catch (FileNotFoundException e) {
				// The file doesn't exist. Create file with default counters
				createButtonHolderFile();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return buttonHolder;
	}

	private void createButtonHolderFile() {
		Log.d(TAG, "Creating ButtonHolder file.");
		CellButton butt1 = new CellButton(context.getResources().getString(
				R.string.Lymphocytes), context.getResources().getString(
				R.string.Lymphocytes_abbr), R.raw.believe,
				R.drawable.light_bluefamily_5ddcd3_048);
		CellButton butt2 = new CellButton(context.getResources().getString(
				R.string.Monocytes), context.getResources().getString(
				R.string.Monocytes_abbr),
				R.raw.attention,
				R.drawable.blue_green_family_26878e);
		CellButton butt3 = new CellButton(context.getResources().getString(
				R.string.Eosinophils), context.getResources().getString(
				R.string.Eosinophils_abbr), R.raw.way,
				R.drawable.blue_purple_6666cc);
		CellButton butt4 = new CellButton(context.getResources().getString(
				R.string.Basophils), context.getResources().getString(
				R.string.Basophils_abbr), R.raw.look,
				R.drawable.green);
		CellButton butt5 = new CellButton(context.getResources().getString(
				R.string.Neutrophils), context.getResources().getString(
				R.string.Neutrophils_abbr), R.raw.here,
				R.drawable.greenfamily_00cc99);
		CellButton butt6 = new CellButton(context.getResources().getString(
				R.string.Bands), context.getResources().getString(
				R.string.Bands_abbr), R.raw.high,
				R.drawable.orange);
		CellButton butt7 = new CellButton(context.getResources().getString(
				R.string.Blasts), context.getResources().getString(
				R.string.Blasts_abbr), R.raw.know,
				R.drawable.orangefamily_ffae61);
		CellButton butt8 = new CellButton(context.getResources().getString(
				R.string.Reticulocytes), context.getResources().getString(
				R.string.Reticulocytes_abbr), R.raw.dark,
				R.drawable.light_orange_ffae61_048);

		ArrayList<CellButton> defaultCells = new ArrayList<CellButton>();
		defaultCells.add(butt1);
		defaultCells.add(butt2);
		defaultCells.add(butt3);
		defaultCells.add(butt4);
		defaultCells.add(butt5);
		defaultCells.add(butt6);
		defaultCells.add(butt7);
		defaultCells.add(butt8);

		CellButton butt9 = new CellButton(context.getResources().getString(
				R.string.AtypicalLymphocytes), context.getResources()
				.getString(R.string.AtypicalLymphocytes_abbr),
				R.raw.sting, R.drawable.light_grayblue_8387aa_048);
		CellButton butt10 = new CellButton(context.getResources().getString(
				R.string.Meta_myelocytes), context.getResources().getString(
				R.string.Meta_myelocytes_abbr),
				R.raw.arpeggio, R.drawable.yellow);
		CellButton butt11 = new CellButton(context.getResources().getString(
				R.string.Myelocytes), context.getResources().getString(
				R.string.Myelocytes_abbr), R.raw.stopper,
				R.drawable.light_blueish_81b3ff_048);
		CellButton butt12 = new CellButton(context.getResources().getString(
				R.string.Promyelocytes), context.getResources().getString(
				R.string.Promyelocytes_abbr), R.raw.close,
				R.drawable.blue_green_family_26878e);
		CellButton butt13 = new CellButton(context.getResources().getString(
				R.string.Promonocytes), context.getResources().getString(
				R.string.Promonocytes_abbr),
				R.raw.believe,
				R.drawable.blue_purple_6666cc);
		CellButton butt14 = new CellButton(context.getResources().getString(
				R.string.Red_blood_cells), context.getResources().getString(
				R.string.Red_blood_cells_abbr), R.raw.beep1,
				R.drawable.green);
		CellButton butt15 = new CellButton(context.getResources().getString(
				R.string.White_Blood_Cells), context.getResources().getString(
				R.string.White_Blood_Cells_abbr), R.raw.dramatic,
				R.drawable.greenfamily_00cc99);
		CellButton butt16 = new CellButton(context.getResources().getString(
				R.string.Plasma_cells), context.getResources().getString(
				R.string.Plasma_cells_abbr), R.raw.brake,
				R.drawable.orange);
		CellButton butt17 = new CellButton(context.getResources().getString(
				R.string.Atypical_plasma_cells), context.getResources()
				.getString(R.string.Atypical_plasma_cells_abbr),
				R.raw.beep2,
				R.drawable.orangefamily_ffae61);
		CellButton butt18 = new CellButton(context.getResources().getString(
				R.string.Mast_cells), context.getResources().getString(
				R.string.Mast_cells_abbr), R.raw.attention,
				R.drawable.light_pink_fb97c9_048);
		CellButton butt19 = new CellButton(context.getResources().getString(
				R.string.Megakaryocytes), context.getResources().getString(
				R.string.Megakaryocytes_abbr),
				R.raw.question, R.drawable.light_whitegray_e7d2ef_048);

		ArrayList<CellButton> highYield = new ArrayList<CellButton>();
		highYield.add(butt9);
		highYield.add(butt10);
		highYield.add(butt11);
		highYield.add(butt12);
		highYield.add(butt13);
		highYield.add(butt14);
		highYield.add(butt15);
		highYield.add(butt16);
		highYield.add(butt17);
		highYield.add(butt18);
		highYield.add(butt19);

		CellButton butt20 = new CellButton(context.getResources().getString(
				R.string.Erythroblasts), context.getResources().getString(
				R.string.Erythroblasts_abbr), R.raw.arpeggio,
				R.drawable.yellow);
		CellButton butt21 = new CellButton(context.getResources().getString(
				R.string.Proerythroblasts), context.getResources().getString(
				R.string.Proerythroblasts_abbr), R.raw.brake,
				R.drawable.blue_purple_6666cc);
		CellButton butt22 = new CellButton(context.getResources().getString(
				R.string.T_cells), context.getResources().getString(
				R.string.T_cells_abbr), R.raw.know,
				R.drawable.blue_green_family_26878e);
		CellButton butt23 = new CellButton(context.getResources().getString(
				R.string.B_Cells), context.getResources().getString(
				R.string.B_Cells_abbr), R.raw.dark,
				R.drawable.blue_purple_6666cc);
		CellButton butt24 = new CellButton(context.getResources().getString(
				R.string.CD4Plus_T_cells), context.getResources().getString(
				R.string.CD4Plus_T_cells_abbr), R.raw.sting,
				R.drawable.green);
		CellButton butt25 = new CellButton(context.getResources().getString(
				R.string.CD8Plus_T_cells), context.getResources().getString(
				R.string.CD8Plus_T_cells_abbr),
				R.raw.way,
				R.drawable.greenfamily_00cc99);
		CellButton butt26 = new CellButton(context.getResources().getString(
				R.string.Reed_Sternberg_cells), context.getResources()
				.getString(R.string.Reed_Sternberg_cells_abbr),
				R.raw.here, R.drawable.orange);
		ArrayList<CellButton> midYield = new ArrayList<CellButton>();
		midYield.add(butt20);
		midYield.add(butt21);
		midYield.add(butt22);
		midYield.add(butt23);
		midYield.add(butt24);
		midYield.add(butt25);
		midYield.add(butt26);

		CellButton butt27 = new CellButton(context.getResources().getString(
				R.string.Atypical_eosinophils), context.getResources()
				.getString(R.string.Atypical_eosinophils_abbr),
				R.raw.high, R.drawable.orangefamily_ffae61);
		CellButton butt28 = new CellButton(context.getResources().getString(
				R.string.Atypical_basophils), context.getResources().getString(
				R.string.Atypical_basophils_abbr),
				R.raw.arpeggio, R.drawable.light_pink_fb97c9_048);
		CellButton butt29 = new CellButton(context.getResources().getString(
				R.string.Atypical_neutrophils), context.getResources()
				.getString(R.string.Atypical_neutrophils_abbr),
				R.raw.attention, R.drawable.light_brown_c78540_048);
		CellButton butt30 = new CellButton(context.getResources().getString(
				R.string.Atypical_neutrophils), context.getResources()
				.getString(R.string.Atypical_neutrophils_abbr),
				R.raw.dark, R.drawable.light_blueish_81b3ff_048);
		CellButton butt31 = new CellButton(context.getResources().getString(
				R.string.Atypical_megakaryocytes), context.getResources()
				.getString(R.string.Atypical_megakaryocytes_abbr),
				R.raw.know, R.drawable.yellow);
		CellButton butt32 = new CellButton(context.getResources().getString(
				R.string.Megakaryoblasts), context.getResources().getString(
				R.string.Megakaryoblasts_abbr), R.raw.beep1,
				R.drawable.light_brown_c78540_048);
		CellButton butt33 = new CellButton(context.getResources().getString(
				R.string.Basophilic_normoblasts), context.getResources()
				.getString(R.string.Basophilic_normoblasts_abbr),
				R.raw.shoot,
				R.drawable.blue_green_family_26878e);
		CellButton butt34 = new CellButton(context.getResources().getString(
				R.string.Orthochromatophilic_normoblasts), context
				.getResources().getString(
						R.string.Orthochromatophilic_normoblasts_abbr),
				R.raw.beep2, R.drawable.blue_purple_6666cc);
		CellButton butt35 = new CellButton(context.getResources().getString(
				R.string.Immunoblasts), context.getResources().getString(
				R.string.Immunoblasts_abbr), R.raw.stopper,
				R.drawable.green);
		CellButton butt36 = new CellButton(context.getResources().getString(
				R.string.Centroblasts), context.getResources().getString(
				R.string.Centroblasts_abbr), R.raw.believe,
				R.drawable.greenfamily_00cc99);
		CellButton butt37 = new CellButton(context.getResources().getString(
				R.string.Centrocytes), context.getResources().getString(
				R.string.Centrocytes_abbr), R.raw.brake,
				R.drawable.orange);
		CellButton butt38 = new CellButton(context.getResources().getString(
				R.string.Dendritic_cells), context.getResources().getString(
				R.string.Dendritic_cells_abbr), R.raw.way,
				R.drawable.orangefamily_ffae61);
		CellButton butt39 = new CellButton(context.getResources().getString(
				R.string.NK_cells), context.getResources().getString(
				R.string.NK_cells_abbr), R.raw.dark,
				R.drawable.light_whitegray_e7d2ef_048);

		ArrayList<CellButton> lowYield = new ArrayList<CellButton>();
		lowYield.add(butt27);
		lowYield.add(butt28);
		lowYield.add(butt29);
		lowYield.add(butt30);
		lowYield.add(butt31);
		lowYield.add(butt32);
		lowYield.add(butt33);
		lowYield.add(butt34);
		lowYield.add(butt35);
		lowYield.add(butt36);
		lowYield.add(butt37);
		lowYield.add(butt38);
		lowYield.add(butt39);

		updateButtonHolder(new ButtonHolder(defaultCells, highYield, midYield,
				lowYield));
	}

	public void updateButtonHolder(ButtonHolder newButtonHolder) {
		Log.d(TAG, "Updating ButtonHolder file.");
		this.buttonHolder = newButtonHolder;
		try {
			FileOutputStream fileOutput = context.openFileOutput(
					buttonHolderFileName, Context.MODE_PRIVATE);
			String json = newButtonHolder.toJSONObject().toString();
			OutputStreamWriter writer = new OutputStreamWriter(fileOutput);
			writer.write(json);
			Log.d(TAG, "Closing CounterHolder file.");
			writer.flush();
			writer.close();
			fileOutput.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
