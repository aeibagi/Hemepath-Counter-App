package edu.sjsu.hemepathcounter;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import edu.sjsu.hemepathcounter.model.ButtonHolder;
import edu.sjsu.hemepathcounter.model.CellButton;
import edu.sjsu.hemepathcounter.model.CellButton.CellType;
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

		ArrayList<CellButton> tenKeyCells = new ArrayList<CellButton>();
		CellButton nRBC = new CellButton(context.getResources().getString(
				R.string.Reticulocytes), context.getResources().getString(
				R.string.Reticulocytes_abbr), R.raw.dark,
				R.drawable.light_orange_ffae61_048, CellType.ERYTHROID);
		tenKeyCells.add(nRBC);
		CellButton blasts = new CellButton(context.getResources().getString(
				R.string.Blasts), context.getResources().getString(
				R.string.Blasts_abbr), R.raw.know,
				R.drawable.orangefamily_ffae61, CellType.MYELOID);
		tenKeyCells.add(blasts);
		CellButton basos = new CellButton(context.getResources().getString(
				R.string.Basophils), context.getResources().getString(
				R.string.Basophils_abbr), R.raw.look, R.drawable.green,
				CellType.MYELOID);
		tenKeyCells.add(basos);
		CellButton eos = new CellButton(context.getResources().getString(
				R.string.Eosinophils), context.getResources().getString(
				R.string.Eosinophils_abbr), R.raw.way,
				R.drawable.blue_family, CellType.MYELOID);
		tenKeyCells.add(eos);
		CellButton PMNs = new CellButton(context.getResources().getString(
				R.string.Neutrophils), context.getResources().getString(
				R.string.Neutrophils_abbr), R.raw.here,
				R.drawable.greenfamily_00cc99, CellType.MYELOID);
		tenKeyCells.add(PMNs);
		CellButton bands = new CellButton(context.getResources().getString(
				R.string.Bands), context.getResources().getString(
				R.string.Bands_abbr), R.raw.high, R.drawable.orange,
				CellType.MYELOID);
		tenKeyCells.add(bands);
		CellButton lymphs = new CellButton(context.getResources().getString(
				R.string.Lymphocytes), context.getResources().getString(
				R.string.Lymphocytes_abbr), R.raw.believe,
				R.drawable.light_bluefamily_5ddcd3_048, CellType.OTHER);
		tenKeyCells.add(lymphs);
		CellButton atyp_lymphs = new CellButton(context.getResources()
				.getString(R.string.AtypicalLymphocytes), context
				.getResources().getString(R.string.AtypicalLymphocytes_abbr),
				R.raw.sting, R.drawable.red_family,
				CellType.OTHER);
		tenKeyCells.add(atyp_lymphs);
		CellButton monos = new CellButton(context.getResources().getString(
				R.string.Monocytes), context.getResources().getString(
				R.string.Monocytes_abbr), R.raw.attention,
				R.drawable.gray_familyone_adafb1_048, CellType.MYELOID);
		tenKeyCells.add(monos);
		CellButton myelos = new CellButton(context.getResources().getString(
				R.string.Myelocytes), context.getResources().getString(
				R.string.Myelocytes_abbr), R.raw.stopper,
				R.drawable.light_blueish_81b3ff_048, CellType.MYELOID);
		tenKeyCells.add(myelos);
		Counter counter1 = new Counter("10 Key", tenKeyCells);

		ArrayList<CellButton> eightKeyCells = new ArrayList<CellButton>();
		eightKeyCells.add(nRBC);
		eightKeyCells.add(blasts);
		eightKeyCells.add(basos);
		eightKeyCells.add(eos);
		eightKeyCells.add(PMNs);
		eightKeyCells.add(monos);
		eightKeyCells.add(lymphs);
		eightKeyCells.add(atyp_lymphs);
		Counter counter2 = new Counter("8 Key", eightKeyCells);

		ArrayList<CellButton> MECells = new ArrayList<CellButton>();
		MECells.add(nRBC);
		CellButton myeloid = new CellButton(context.getResources().getString(
				R.string.Myeloid), context.getResources().getString(
				R.string.Myeloid_abbr), R.raw.question,
				R.drawable.light_orange_ffae61_048, CellType.MYELOID);
		MECells.add(myeloid);
		Counter counter3 = new Counter("M:E", MECells);

		ArrayList<CellButton> blastCells = new ArrayList<CellButton>();
		CellButton WBCs = new CellButton(context.getResources().getString(
				R.string.White_Blood_Cells), context.getResources().getString(
				R.string.White_Blood_Cells_abbr), R.raw.dramatic,
				R.drawable.greenfamily_00cc99, CellType.OTHER);
		blastCells.add(WBCs);
		blastCells.add(blasts);
		Counter counter4 = new Counter("Blast Count", blastCells);

		ArrayList<CellButton> erythroidCells = new ArrayList<CellButton>();
		CellButton eBlasts = new CellButton(context.getResources().getString(
				R.string.Erythroblasts), context.getResources().getString(
				R.string.Erythroblasts_abbr), R.raw.arpeggio,
				R.drawable.yellow, CellType.ERYTHROID);
		erythroidCells.add(eBlasts);
		CellButton proeryth = new CellButton(context.getResources().getString(
				R.string.Proerythroblasts), context.getResources().getString(
				R.string.Proerythroblasts_abbr), R.raw.brake,
				R.drawable.blue_family, CellType.ERYTHROID);
		erythroidCells.add(proeryth);
		CellButton bNormo = new CellButton(context.getResources().getString(
				R.string.Basophilic_normoblasts), context.getResources()
				.getString(R.string.Basophilic_normoblasts_abbr), R.raw.shoot,
				R.drawable.light_pink_fb97c9_048, CellType.ERYTHROID);
		erythroidCells.add(bNormo);
		CellButton oNormo = new CellButton(context.getResources().getString(
				R.string.Orthochromatophilic_normoblasts), context
				.getResources().getString(
						R.string.Orthochromatophilic_normoblasts_abbr),
				R.raw.beep2, R.drawable.red_family, CellType.OTHER);
		erythroidCells.add(oNormo);
		erythroidCells.add(nRBC);
		erythroidCells.add(myelos);
		erythroidCells.add(lymphs);
		CellButton megs = new CellButton(context.getResources().getString(
				R.string.Megakaryocytes), context.getResources().getString(
				R.string.Megakaryocytes_abbr), R.raw.question,
				R.drawable.light_whitegray_e7d2ef_048, CellType.OTHER);
		erythroidCells.add(megs);
		Counter counter5 = new Counter("Erythroid Series", erythroidCells);

		ArrayList<CellButton> marrowCells = new ArrayList<CellButton>();
		marrowCells.add(nRBC);
		marrowCells.add(blasts);
		marrowCells.add(basos);
		marrowCells.add(eos);
		CellButton mast = new CellButton(context.getResources().getString(
				R.string.Mast_cells), context.getResources().getString(
				R.string.Mast_cells_abbr), R.raw.attention,
				R.drawable.light_pink_fb97c9_048, CellType.OTHER);
		marrowCells.add(mast);
		marrowCells.add(PMNs);
		marrowCells.add(bands);
		marrowCells.add(lymphs);
		marrowCells.add(atyp_lymphs);
		marrowCells.add(monos);
		CellButton promonos = new CellButton(context.getResources().getString(
				R.string.Promonocytes), context.getResources().getString(
				R.string.Promonocytes_abbr), R.raw.believe,
				R.drawable.yellow_family, CellType.MYELOID);
		marrowCells.add(promonos);
		marrowCells.add(myelos);
		CellButton meta = new CellButton(context.getResources().getString(
				R.string.Meta_myelocytes), context.getResources().getString(
				R.string.Meta_myelocytes_abbr), R.raw.arpeggio,
				R.drawable.yellow, CellType.MYELOID);
		marrowCells.add(meta);
		CellButton promyelos = new CellButton(context.getResources().getString(
				R.string.Promyelocytes), context.getResources().getString(
				R.string.Promyelocytes_abbr), R.raw.close,
				R.drawable.light_blueish_81b3ff_048, CellType.MYELOID);
		marrowCells.add(promyelos);
		Counter counter6 = new Counter("Bone Marrow", marrowCells);

		ArrayList<CellButton> myelomaCells = new ArrayList<CellButton>();
		myelomaCells.add(nRBC);
		myelomaCells.add(blasts);
		myelomaCells.add(basos);
		myelomaCells.add(eos);
		myelomaCells.add(mast);
		myelomaCells.add(PMNs);
		myelomaCells.add(bands);
		myelomaCells.add(lymphs);
		CellButton PCs = new CellButton(context.getResources().getString(
				R.string.Plasma_cells), context.getResources().getString(
				R.string.Plasma_cells_abbr), R.raw.brake, R.drawable.orange,
				CellType.OTHER);
		myelomaCells.add(PCs);
		myelomaCells.add(monos);
		myelomaCells.add(promonos);
		myelomaCells.add(myelos);
		myelomaCells.add(meta);
		myelomaCells.add(promyelos);
		Counter counter7 = new Counter("Myeloma", myelomaCells);

		ArrayList<CellButton> myelomaQuickCells = new ArrayList<CellButton>();
		myelomaQuickCells.add(nRBC);
		myelomaQuickCells.add(myeloid);
		myelomaQuickCells.add(lymphs);
		myelomaQuickCells.add(PCs);
		Counter counter8 = new Counter("Myeloma Quick", myelomaQuickCells);

		ArrayList<CellButton> myelomonocyticCells = new ArrayList<CellButton>();
		myelomonocyticCells.add(nRBC);
		myelomonocyticCells.add(blasts);
		myelomonocyticCells.add(basos);
		myelomonocyticCells.add(eos);
		myelomonocyticCells.add(PMNs);
		myelomonocyticCells.add(lymphs);
		myelomonocyticCells.add(monos);
		myelomonocyticCells.add(promonos);
		CellButton monoBs = new CellButton(context.getResources().getString(
				R.string.Monoblasts), context.getResources().getString(
				R.string.Monoblasts_abbr), R.raw.here,
				R.drawable.light_gray_9198aa_048, CellType.MYELOID);
		myelomonocyticCells.add(monoBs);
		myelomonocyticCells.add(bands);
		myelomonocyticCells.add(myelos);
		myelomonocyticCells.add(meta);
		Counter counter9 = new Counter("Myelomonocytic", myelomonocyticCells);

		ArrayList<CellButton> mitosesCells = new ArrayList<CellButton>();
		CellButton mitoses = new CellButton(context.getResources().getString(
				R.string.Mitoses), context.getResources().getString(
				R.string.Mitoses), R.raw.here,
				R.drawable.light_pink_fb97c9_048, CellType.OTHER);
		mitosesCells.add(mitoses);
		CellButton HPFs = new CellButton(context.getResources().getString(
				R.string.High_Power_fields), context.getResources().getString(
				R.string.High_Power_fields_abbr), R.raw.here,
				R.drawable.light_pink_fb97c9_048, CellType.OTHER);
		mitosesCells.add(HPFs);
		Counter counter10 = new Counter("Mitoses", mitosesCells);

		ArrayList<CellButton> CLLCells = new ArrayList<CellButton>();
		CLLCells.add(nRBC);
		CLLCells.add(myelos);
		CLLCells.add(lymphs);
		CLLCells.add(atyp_lymphs);
		CellButton pro_lymphs = new CellButton(context.getResources().getString(
				R.string.Prolymphocytes), context.getResources().getString(
				R.string.Prolymphocytes_abbr), R.raw.here,
				R.drawable.greenish_family, CellType.OTHER);
		CLLCells.add(pro_lymphs);
		Counter counter11 = new Counter("CLL with Prolymphocytes", CLLCells);

		ArrayList<CellButton> FLCells = new ArrayList<CellButton>();
		FLCells.add(lymphs);
		FLCells.add(atyp_lymphs);
		FLCells.add(HPFs);
		Counter counter12 = new Counter("Follicular Lymphoma", FLCells);

		ArrayList<CellButton> nodeCells = new ArrayList<CellButton>();
		CellButton lymph_nodes = new CellButton(context.getResources().getString(
				R.string.Lymph_Nodes), context.getResources().getString(
				R.string.Lymph_Nodes_abbr), R.raw.here,
				R.drawable.veylight_green_ffff99_048, CellType.OTHER);
		nodeCells.add(lymph_nodes);
		CellButton pos_lymphs = new CellButton(context.getResources().getString(
				R.string.Positive_Lymph_Nodes), context.getResources().getString(
				R.string.Positive_Lymph_Nodes_abbr), R.raw.here,
				R.drawable.dark_oragne_d1692c_048, CellType.OTHER);
		nodeCells.add(pos_lymphs);
		Counter counter13 = new Counter("Node Count", nodeCells);

		ArrayList<Counter> list = new ArrayList<Counter>();
		list.add(counter1);
		list.add(counter2);
		list.add(counter3);
		list.add(counter4);
		list.add(counter5);
		list.add(counter6);
		list.add(counter7);
		list.add(counter8);
		list.add(counter9);
		list.add(counter10);
		list.add(counter11);
		list.add(counter12);
		list.add(counter13);
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
				R.string.Eosinophils), context.getResources().getString(
				R.string.Eosinophils_abbr), R.raw.believe,
				R.drawable.light_bluefamily_5ddcd3_048, CellType.MYELOID);
		CellButton butt2 = new CellButton(context.getResources().getString(
				R.string.Basophils), context.getResources().getString(
				R.string.Basophils_abbr), R.raw.attention,
				R.drawable.gray_familyone_adafb1_048, CellType.MYELOID);
		CellButton butt3 = new CellButton(context.getResources().getString(
				R.string.Neutrophils), context.getResources().getString(
				R.string.Neutrophils_abbr), R.raw.way,
				R.drawable.pink_family, CellType.MYELOID);
		CellButton butt4 = new CellButton(context.getResources().getString(
				R.string.Bands), context.getResources().getString(
				R.string.Bands_abbr), R.raw.look, R.drawable.green,
				CellType.MYELOID);
		CellButton butt5 = new CellButton(context.getResources().getString(
				R.string.Blasts), context.getResources().getString(
				R.string.Blasts_abbr), R.raw.here,
				R.drawable.greenfamily_00cc99, CellType.MYELOID);
		CellButton butt6 = new CellButton(context.getResources().getString(
				R.string.Meta_myelocytes), context.getResources().getString(
				R.string.Meta_myelocytes_abbr), R.raw.high, R.drawable.orange,
				CellType.MYELOID);
		CellButton butt7 = new CellButton(context.getResources().getString(
				R.string.Myelocytes), context.getResources().getString(
				R.string.Myelocytes_abbr), R.raw.know,
				R.drawable.orangefamily_ffae61, CellType.MYELOID);
		CellButton butt8 = new CellButton(context.getResources().getString(
				R.string.Promyelocytes), context.getResources().getString(
				R.string.Promyelocytes_abbr), R.raw.dark,
				R.drawable.light_orange_ffae61_048, CellType.MYELOID);
		CellButton butt9 = new CellButton(context.getResources().getString(
				R.string.Myeloid), context.getResources().getString(
				R.string.Myeloid_abbr), R.raw.here,
				R.drawable.greenfamily_00cc99, CellType.MYELOID);
		CellButton butt10 = new CellButton(context.getResources().getString(
				R.string.Atypical_eosinophils), context.getResources().getString(
				R.string.Atypical_eosinophils_abbr), R.raw.high, R.drawable.orange,
				CellType.MYELOID);
		CellButton butt11 = new CellButton(context.getResources().getString(
				R.string.Atypical_basophils), context.getResources().getString(
				R.string.Atypical_basophils_abbr), R.raw.know,
				R.drawable.orangefamily_ffae61, CellType.MYELOID);
		CellButton butt12 = new CellButton(context.getResources().getString(
				R.string.Atypical_neutrophils), context.getResources().getString(
				R.string.Atypical_neutrophils_abbr), R.raw.dark,
				R.drawable.light_orange_ffae61_048, CellType.ERYTHROID);
		CellButton butt13 = new CellButton(context.getResources().getString(
				R.string.Mast_cells), context.getResources().getString(
				R.string.Mast_cells_abbr), R.raw.dark,
				R.drawable.light_orange_ffae61_048, CellType.OTHER);
		
		ArrayList<CellButton> Granulocytic_Lineage = new ArrayList<CellButton>();
		Granulocytic_Lineage.add(butt1);
		Granulocytic_Lineage.add(butt2);
		Granulocytic_Lineage.add(butt3);
		Granulocytic_Lineage.add(butt4);
		Granulocytic_Lineage.add(butt5);
		Granulocytic_Lineage.add(butt6);
		Granulocytic_Lineage.add(butt7);
		Granulocytic_Lineage.add(butt8);
		Granulocytic_Lineage.add(butt9);
		Granulocytic_Lineage.add(butt10);
		Granulocytic_Lineage.add(butt11);
		Granulocytic_Lineage.add(butt12);
		Granulocytic_Lineage.add(butt13);
		/*
		CellButton butt1 = new CellButton(context.getResources().getString(
				R.string.Lymphocytes), context.getResources().getString(
				R.string.Lymphocytes_abbr), R.raw.believe,
				R.drawable.light_bluefamily_5ddcd3_048, CellType.OTHER);
		CellButton butt2 = new CellButton(context.getResources().getString(
				R.string.Monocytes), context.getResources().getString(
				R.string.Monocytes_abbr), R.raw.attention,
				R.drawable.gray_familyone_adafb1_048, CellType.MYELOID);
		CellButton butt3 = new CellButton(context.getResources().getString(
				R.string.Eosinophils), context.getResources().getString(
				R.string.Eosinophils_abbr), R.raw.way,
				R.drawable.blue_purple_6666cc, CellType.MYELOID);
		CellButton butt4 = new CellButton(context.getResources().getString(
				R.string.Basophils), context.getResources().getString(
				R.string.Basophils_abbr), R.raw.look, R.drawable.green,
				CellType.MYELOID);
		CellButton butt5 = new CellButton(context.getResources().getString(
				R.string.Neutrophils), context.getResources().getString(
				R.string.Neutrophils_abbr), R.raw.here,
				R.drawable.greenfamily_00cc99, CellType.MYELOID);
		CellButton butt6 = new CellButton(context.getResources().getString(
				R.string.Bands), context.getResources().getString(
				R.string.Bands_abbr), R.raw.high, R.drawable.orange,
				CellType.MYELOID);
		CellButton butt7 = new CellButton(context.getResources().getString(
				R.string.Blasts), context.getResources().getString(
				R.string.Blasts_abbr), R.raw.know,
				R.drawable.orangefamily_ffae61, CellType.MYELOID);
		CellButton butt8 = new CellButton(context.getResources().getString(
				R.string.Reticulocytes), context.getResources().getString(
				R.string.Reticulocytes_abbr), R.raw.dark,
				R.drawable.light_orange_ffae61_048, CellType.ERYTHROID);

		ArrayList<CellButton> defaultCells = new ArrayList<CellButton>();
		defaultCells.add(butt1);
		defaultCells.add(butt2);
		defaultCells.add(butt3);
		defaultCells.add(butt4);
		defaultCells.add(butt5);
		defaultCells.add(butt6);
		defaultCells.add(butt7);
		defaultCells.add(butt8);
        */
		
		CellButton butt14 = new CellButton(context.getResources().getString(
				R.string.AtypicalLymphocytes), context.getResources()
				.getString(R.string.AtypicalLymphocytes_abbr), R.raw.sting,
				R.drawable.red_family, CellType.OTHER);
		CellButton butt15 = new CellButton(context.getResources().getString(
				R.string.Lymphocytes), context.getResources().getString(
				R.string.Lymphocytes_abbr), R.raw.arpeggio,
				R.drawable.yellow, CellType.OTHER);
		CellButton butt16 = new CellButton(context.getResources().getString(
                R.string.Lymphocytic_Blasts), context.getResources().getString(
				R.string.Lymphocytic_Blasts_abbr), R.raw.stopper,
				R.drawable.light_blueish_81b3ff_048, CellType.OTHER);
		CellButton butt17 = new CellButton(context.getResources().getString(
				R.string.T_cells), context.getResources().getString(
				R.string.T_cells_abbr), R.raw.close,
				R.drawable.light_blueish_81b3ff_048, CellType.OTHER);
		CellButton butt18 = new CellButton(context.getResources().getString(
				R.string.B_Cells), context.getResources().getString(
				R.string.B_Cells_abbr), R.raw.believe,
				R.drawable.pink_family, CellType.OTHER);
		CellButton butt19 = new CellButton(context.getResources().getString(
				R.string.CD4Plus_T_cells), context.getResources().getString(
				R.string.CD4Plus_T_cells_abbr), R.raw.beep1, R.drawable.green,
				CellType.OTHER);
		CellButton butt20 = new CellButton(context.getResources().getString(
				R.string.CD8Plus_T_cells), context.getResources().getString(
				R.string.CD8Plus_T_cells_abbr), R.raw.dramatic,
				R.drawable.greenfamily_00cc99, CellType.OTHER);
		CellButton butt21 = new CellButton(context.getResources().getString(
				R.string.Prolymphocytes), context.getResources().getString(
				R.string.Prolymphocytes_abbr), R.raw.brake, R.drawable.orange,
				CellType.OTHER);
		CellButton butt22 = new CellButton(context.getResources().getString(
				R.string.Plasma_cells), context.getResources()
				.getString(R.string.Plasma_cells_abbr), R.raw.beep2,
				R.drawable.orangefamily_ffae61, CellType.OTHER);
		CellButton butt23 = new CellButton(context.getResources().getString(
				R.string.Atypical_plasma_cells), context.getResources().getString(
				R.string.Atypical_plasma_cells_abbr), R.raw.attention,
				R.drawable.light_pink_fb97c9_048, CellType.OTHER);
		CellButton butt24 = new CellButton(context.getResources().getString(
				R.string.Immunoblasts), context.getResources().getString(
				R.string.Immunoblasts_abbr), R.raw.question,
				R.drawable.light_whitegray_e7d2ef_048, CellType.OTHER);
		CellButton butt25 = new CellButton(context.getResources().getString(
				R.string.Centroblasts), context.getResources().getString(
				R.string.Centroblasts_abbr), R.raw.attention,
				R.drawable.light_pink_fb97c9_048, CellType.OTHER);
		CellButton butt26 = new CellButton(context.getResources().getString(
				R.string.Centrocytes), context.getResources().getString(
				R.string.Centrocytes_abbr), R.raw.question,
				R.drawable.light_whitegray_e7d2ef_048, CellType.OTHER);
		CellButton butt27 = new CellButton(context.getResources().getString(
				R.string.NK_cells), context.getResources().getString(
				R.string.NK_cells_abbr), R.raw.attention,
				R.drawable.light_pink_fb97c9_048, CellType.OTHER);
		CellButton butt28 = new CellButton(context.getResources().getString(
				R.string.Reed_Sternberg_cells), context.getResources().getString(
				R.string.Reed_Sternberg_cells_abbr), R.raw.question,
				R.drawable.light_whitegray_e7d2ef_048, CellType.OTHER);
		
		ArrayList<CellButton> Lymphocytic_Lineage = new ArrayList<CellButton>();
		Lymphocytic_Lineage.add(butt14);
		Lymphocytic_Lineage.add(butt15);
		Lymphocytic_Lineage.add(butt16);
		Lymphocytic_Lineage.add(butt17);
		Lymphocytic_Lineage.add(butt18);
		Lymphocytic_Lineage.add(butt19);
		Lymphocytic_Lineage.add(butt20);
		Lymphocytic_Lineage.add(butt21);
		Lymphocytic_Lineage.add(butt22);
		Lymphocytic_Lineage.add(butt23);
		Lymphocytic_Lineage.add(butt24);
		Lymphocytic_Lineage.add(butt25);
		Lymphocytic_Lineage.add(butt26);
		Lymphocytic_Lineage.add(butt27);
		Lymphocytic_Lineage.add(butt28);
		
		/*
		CellButton butt9 = new CellButton(context.getResources().getString(
				R.string.AtypicalLymphocytes), context.getResources()
				.getString(R.string.AtypicalLymphocytes_abbr), R.raw.sting,
				R.drawable.light_grayblue_8387aa_048, CellType.OTHER);
		CellButton butt10 = new CellButton(context.getResources().getString(
				R.string.Meta_myelocytes), context.getResources().getString(
				R.string.Meta_myelocytes_abbr), R.raw.arpeggio,
				R.drawable.yellow, CellType.MYELOID);
		CellButton butt11 = new CellButton(context.getResources().getString(
				R.string.Myelocytes), context.getResources().getString(
				R.string.Myelocytes_abbr), R.raw.stopper,
				R.drawable.light_blueish_81b3ff_048, CellType.MYELOID);
		CellButton butt12 = new CellButton(context.getResources().getString(
				R.string.Promyelocytes), context.getResources().getString(
				R.string.Promyelocytes_abbr), R.raw.close,
				R.drawable.light_blueish_81b3ff_048, CellType.MYELOID);
		CellButton butt13 = new CellButton(context.getResources().getString(
				R.string.Promonocytes), context.getResources().getString(
				R.string.Promonocytes_abbr), R.raw.believe,
				R.drawable.blue_purple_6666cc, CellType.MYELOID);
		CellButton butt14 = new CellButton(context.getResources().getString(
				R.string.Red_blood_cells), context.getResources().getString(
				R.string.Red_blood_cells_abbr), R.raw.beep1, R.drawable.green,
				CellType.OTHER);
		CellButton butt15 = new CellButton(context.getResources().getString(
				R.string.White_Blood_Cells), context.getResources().getString(
				R.string.White_Blood_Cells_abbr), R.raw.dramatic,
				R.drawable.greenfamily_00cc99, CellType.OTHER);
		CellButton butt16 = new CellButton(context.getResources().getString(
				R.string.Plasma_cells), context.getResources().getString(
				R.string.Plasma_cells_abbr), R.raw.brake, R.drawable.orange,
				CellType.OTHER);
		CellButton butt17 = new CellButton(context.getResources().getString(
				R.string.Atypical_plasma_cells), context.getResources()
				.getString(R.string.Atypical_plasma_cells_abbr), R.raw.beep2,
				R.drawable.orangefamily_ffae61, CellType.OTHER);
		CellButton butt18 = new CellButton(context.getResources().getString(
				R.string.Mast_cells), context.getResources().getString(
				R.string.Mast_cells_abbr), R.raw.attention,
				R.drawable.light_pink_fb97c9_048, CellType.OTHER);
		CellButton butt19 = new CellButton(context.getResources().getString(
				R.string.Megakaryocytes), context.getResources().getString(
				R.string.Megakaryocytes_abbr), R.raw.question,
				R.drawable.light_whitegray_e7d2ef_048, CellType.OTHER);

		CellButton buttAddedLater = new CellButton(context.getResources()
				.getString(R.string.Myeloid), context.getResources().getString(
				R.string.Myeloid_abbr), R.raw.question,
				R.drawable.light_orange_ffae61_048, CellType.MYELOID);

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
		highYield.add(buttAddedLater);
*/
		
		
		CellButton butt29 = new CellButton(context.getResources().getString(
				R.string.Erythroblasts), context.getResources().getString(
				R.string.Erythroblasts_abbr), R.raw.arpeggio,
				R.drawable.yellow, CellType.ERYTHROID);
		CellButton butt30 = new CellButton(context.getResources().getString(
				R.string.Proerythroblasts), context.getResources().getString(
				R.string.Proerythroblasts_abbr), R.raw.brake,
				R.drawable.blue_family, CellType.ERYTHROID);
		CellButton butt31 = new CellButton(context.getResources().getString(
				R.string.Monoblasts), context.getResources().getString(
				R.string.Monoblasts_abbr), R.raw.here,
				R.drawable.light_gray_9198aa_048, CellType.MYELOID);
		CellButton butt32 = new CellButton(context.getResources().getString(
				R.string.Reticulocytes), context.getResources().getString(
				R.string.Reticulocytes_abbr), R.raw.know,
				R.drawable.yellow_family, CellType.ERYTHROID);
		CellButton butt33 = new CellButton(context.getResources().getString(
				R.string.Red_blood_cells), context.getResources().getString(
				R.string.Red_blood_cells_abbr), R.raw.dark,
				R.drawable.greenish_family, CellType.OTHER);
		CellButton butt34 = new CellButton(context.getResources().getString(
				R.string.Basophilic_normoblasts), context.getResources().getString(
				R.string.Basophilic_normoblasts_abbr), R.raw.sting, R.drawable.green,
				CellType.ERYTHROID);
		CellButton butt35 = new CellButton(context.getResources().getString(
				R.string.Orthochromatophilic_normoblasts), context.getResources().getString(
				R.string.Orthochromatophilic_normoblasts_abbr), R.raw.way,
				R.drawable.greenfamily_00cc99, CellType.ERYTHROID);
		
		ArrayList<CellButton> Erythrocytic_Lineage = new ArrayList<CellButton>();
		Erythrocytic_Lineage.add(butt29);
		Erythrocytic_Lineage.add(butt30);
		Erythrocytic_Lineage.add(butt31);
		Erythrocytic_Lineage.add(butt32);
		Erythrocytic_Lineage.add(butt33);
		Erythrocytic_Lineage.add(butt34);
		Erythrocytic_Lineage.add(butt35);
		
		/*
		CellButton butt20 = new CellButton(context.getResources().getString(
				R.string.Erythroblasts), context.getResources().getString(
				R.string.Erythroblasts_abbr), R.raw.arpeggio,
				R.drawable.yellow, CellType.ERYTHROID);
		CellButton butt21 = new CellButton(context.getResources().getString(
				R.string.Proerythroblasts), context.getResources().getString(
				R.string.Proerythroblasts_abbr), R.raw.brake,
				R.drawable.blue_purple_6666cc, CellType.ERYTHROID);
		CellButton monoBs = new CellButton(context.getResources().getString(
				R.string.Monoblasts), context.getResources().getString(
				R.string.Monoblasts_abbr), R.raw.here,
				R.drawable.light_gray_9198aa_048, CellType.MYELOID);
		CellButton butt22 = new CellButton(context.getResources().getString(
				R.string.T_cells), context.getResources().getString(
				R.string.T_cells_abbr), R.raw.know,
				R.drawable.light_grayblue_8387aa_048, CellType.MYELOID);
		CellButton butt23 = new CellButton(context.getResources().getString(
				R.string.B_Cells), context.getResources().getString(
				R.string.B_Cells_abbr), R.raw.dark,
				R.drawable.blue_purple_6666cc, CellType.OTHER);
		CellButton butt24 = new CellButton(context.getResources().getString(
				R.string.CD4Plus_T_cells), context.getResources().getString(
				R.string.CD4Plus_T_cells_abbr), R.raw.sting, R.drawable.green,
				CellType.OTHER);
		CellButton butt25 = new CellButton(context.getResources().getString(
				R.string.CD8Plus_T_cells), context.getResources().getString(
				R.string.CD8Plus_T_cells_abbr), R.raw.way,
				R.drawable.greenfamily_00cc99, CellType.OTHER);
		CellButton butt26 = new CellButton(context.getResources().getString(
				R.string.Reed_Sternberg_cells), context.getResources()
				.getString(R.string.Reed_Sternberg_cells_abbr), R.raw.here,
				R.drawable.orange, CellType.OTHER);
		ArrayList<CellButton> midYield = new ArrayList<CellButton>();
		midYield.add(butt20);
		midYield.add(butt21);
		midYield.add(monoBs);
		midYield.add(butt22);
		midYield.add(butt23);
		midYield.add(butt24);
		midYield.add(butt25);
		midYield.add(butt26);*/

		
		CellButton butt36 = new CellButton(context.getResources().getString(
				R.string.Monocytes), context.getResources()
				.getString(R.string.Monocytes_abbr), R.raw.high,
				R.drawable.orangefamily_ffae61, CellType.MYELOID);
		CellButton butt37 = new CellButton(context.getResources().getString(
				R.string.Promonocytes), context.getResources().getString(
				R.string.Promonocytes_abbr), R.raw.arpeggio,
				R.drawable.light_pink_fb97c9_048, CellType.MYELOID);
		CellButton butt38 = new CellButton(context.getResources().getString(
				R.string.Megakaryocytes), context.getResources()
				.getString(R.string.Megakaryocytes_abbr),
				R.raw.attention, R.drawable.light_brown_c78540_048,
				CellType.OTHER);
		CellButton butt39 = new CellButton(context.getResources().getString(
				R.string.Atypical_megakaryocytes), context.getResources()
				.getString(R.string.Atypical_megakaryocytes_abbr), R.raw.dark,
				R.drawable.light_blueish_81b3ff_048, CellType.OTHER);
		CellButton butt40 = new CellButton(context.getResources().getString(
				R.string.Megakaryoblasts), context.getResources()
				.getString(R.string.Megakaryoblasts_abbr), R.raw.know,
				R.drawable.yellow, CellType.OTHER);
		CellButton butt41 = new CellButton(context.getResources().getString(
				R.string.Dendritic_cells), context.getResources().getString(
				R.string.Dendritic_cells_abbr), R.raw.beep1,
				R.drawable.light_brown_c78540_048, CellType.OTHER);
		
		ArrayList<CellButton> Monocytic_and_Megakaryocytic_Lineages = new ArrayList<CellButton>();
		Monocytic_and_Megakaryocytic_Lineages.add(butt36);
		Monocytic_and_Megakaryocytic_Lineages.add(butt37);
		Monocytic_and_Megakaryocytic_Lineages.add(butt38);
		Monocytic_and_Megakaryocytic_Lineages.add(butt39);
		Monocytic_and_Megakaryocytic_Lineages.add(butt40);
		Monocytic_and_Megakaryocytic_Lineages.add(butt41);
		/*
		CellButton butt27 = new CellButton(context.getResources().getString(
				R.string.Atypical_eosinophils), context.getResources()
				.getString(R.string.Atypical_eosinophils_abbr), R.raw.high,
				R.drawable.orangefamily_ffae61, CellType.MYELOID);
		CellButton butt28 = new CellButton(context.getResources().getString(
				R.string.Atypical_basophils), context.getResources().getString(
				R.string.Atypical_basophils_abbr), R.raw.arpeggio,
				R.drawable.light_pink_fb97c9_048, CellType.MYELOID);
		CellButton butt29 = new CellButton(context.getResources().getString(
				R.string.Atypical_neutrophils), context.getResources()
				.getString(R.string.Atypical_neutrophils_abbr),
				R.raw.attention, R.drawable.light_brown_c78540_048,
				CellType.ERYTHROID);
		CellButton butt30 = new CellButton(context.getResources().getString(
				R.string.Atypical_neutrophils), context.getResources()
				.getString(R.string.Atypical_neutrophils_abbr), R.raw.dark,
				R.drawable.light_blueish_81b3ff_048, CellType.OTHER);
		CellButton butt31 = new CellButton(context.getResources().getString(
				R.string.Atypical_megakaryocytes), context.getResources()
				.getString(R.string.Atypical_megakaryocytes_abbr), R.raw.know,
				R.drawable.yellow, CellType.OTHER);
		CellButton butt32 = new CellButton(context.getResources().getString(
				R.string.Megakaryoblasts), context.getResources().getString(
				R.string.Megakaryoblasts_abbr), R.raw.beep1,
				R.drawable.light_brown_c78540_048, CellType.ERYTHROID);
		CellButton butt33 = new CellButton(context.getResources().getString(
				R.string.Basophilic_normoblasts), context.getResources()
				.getString(R.string.Basophilic_normoblasts_abbr), R.raw.shoot,
				R.drawable.light_pink_fb97c9_048, CellType.ERYTHROID);
		CellButton butt34 = new CellButton(context.getResources().getString(
				R.string.Orthochromatophilic_normoblasts), context
				.getResources().getString(
						R.string.Orthochromatophilic_normoblasts_abbr),
				R.raw.beep2, R.drawable.blue_purple_6666cc, CellType.OTHER);
		CellButton butt35 = new CellButton(context.getResources().getString(
				R.string.Immunoblasts), context.getResources().getString(
				R.string.Immunoblasts_abbr), R.raw.stopper, R.drawable.green,
				CellType.OTHER);
		CellButton butt36 = new CellButton(context.getResources().getString(
				R.string.Centroblasts), context.getResources().getString(
				R.string.Centroblasts_abbr), R.raw.believe,
				R.drawable.greenfamily_00cc99, CellType.OTHER);
		CellButton butt37 = new CellButton(context.getResources().getString(
				R.string.Centrocytes), context.getResources().getString(
				R.string.Centrocytes_abbr), R.raw.brake, R.drawable.orange,
				CellType.OTHER);
		CellButton butt38 = new CellButton(context.getResources().getString(
				R.string.Dendritic_cells), context.getResources().getString(
				R.string.Dendritic_cells_abbr), R.raw.way,
				R.drawable.orangefamily_ffae61, CellType.OTHER);
		CellButton butt39 = new CellButton(context.getResources().getString(
				R.string.NK_cells), context.getResources().getString(
				R.string.NK_cells_abbr), R.raw.dark,
				R.drawable.light_whitegray_e7d2ef_048, CellType.OTHER);
		CellButton butt40 = new CellButton(context.getResources().getString(
				R.string.NK_cells), context.getResources().getString(
				R.string.NK_cells_abbr), R.raw.dark,
				R.drawable.light_whitegray_e7d2ef_048, CellType.OTHER);

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
		lowYield.add(butt39);*/

		CellButton butt42 = new CellButton(context.getResources().getString(
				R.string.White_Blood_Cells), context.getResources().getString(
				R.string.White_Blood_Cells_abbr), R.raw.arpeggio,
				R.drawable.light_pink_fb97c9_048, CellType.OTHER);
		CellButton butt43 = new CellButton(context.getResources().getString(
				R.string.Lymph_Nodes), context.getResources()
				.getString(R.string.Lymph_Nodes_abbr),
				R.raw.attention, R.drawable.light_brown_c78540_048,
				CellType.OTHER);
		CellButton butt44 = new CellButton(context.getResources().getString(
				R.string.Positive_Lymph_Nodes), context.getResources()
				.getString(R.string.Positive_Lymph_Nodes_abbr), R.raw.dark,
				R.drawable.light_blueish_81b3ff_048, CellType.OTHER);
		CellButton butt45 = new CellButton(context.getResources().getString(
				R.string.Mitoses), context.getResources()
				.getString(R.string.Mitoses_abbr), R.raw.know,
				R.drawable.yellow, CellType.OTHER);
		CellButton butt46 = new CellButton(context.getResources().getString(
				R.string.High_Power_fields), context.getResources().getString(
				R.string.High_Power_fields_abbr), R.raw.beep1,
				R.drawable.light_brown_c78540_048, CellType.OTHER);
		
		ArrayList<CellButton> Miscellaneous = new ArrayList<CellButton>();
		Miscellaneous.add(butt42);
		Miscellaneous.add(butt43);
		Miscellaneous.add(butt44);
		Miscellaneous.add(butt45);
		Miscellaneous.add(butt46);
		
		
		
		
		updateButtonHolder(new ButtonHolder(Granulocytic_Lineage, Lymphocytic_Lineage, Erythrocytic_Lineage,
				Monocytic_and_Megakaryocytic_Lineages,Miscellaneous));
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
