package wir.diebesten.de;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class Settings extends Activity implements OnClickListener, AdapterView.OnItemClickListener {

	ListView lv;
	TextView header;
	ImageView iv;
	ImageButton ib;
	ArrayAdapter<String> listAdapter;
	int switcher; Intent i; Bundle extras;
	String[] populateA;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);
		header = (TextView) findViewById(R.id.settings_header);
		lv = (ListView) findViewById(R.id.listView);
		ib = (ImageButton) findViewById(R.id.imageButton);
		//set ib depending on eachitem of getCase()-listview
		if (getSwitcher()==7){
		ib.setOnClickListener(this);}
		handleListViewPopulation();
	}

	private void handleListViewPopulation() {
		ArrayList<String> objectList = new ArrayList<String>();
		if (getCase(getSwitcher()) != null) {
			objectList.addAll(Arrays.asList(getCase(getSwitcher())));
			listAdapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_1, objectList);
			lv.setAdapter(listAdapter);
			setListViewHeightBasedOnChildren(lv);
			listAdapter.notifyDataSetChanged();
			lv.setOnItemClickListener(this);
					}
	}

	private int getSwitcher() {
		i = getIntent();
		extras = i.getExtras();
		switcher = extras.getInt("stringB");
		return switcher;
	}

	/****
	 * Method for Setting the Height of the ListView dynamically. Hack to fix
	 * the issue of not showing all the items of the ListView when placed inside
	 * a ScrollView
	 ****/
	public static void setListViewHeightBasedOnChildren(ListView listView) {
		ListAdapter lA = listView.getAdapter();
		if (lA == null)
			return;
		int desiredWidth = MeasureSpec.makeMeasureSpec(listView.getWidth(),
				MeasureSpec.UNSPECIFIED);
		int totalHeight = 0;
		View view = null;
		for (int i = 0; i < lA.getCount(); i++) {
			view = lA.getView(i, view, listView);
			if (i == 0)
				view.setLayoutParams(new LayoutParams(desiredWidth,
						LayoutParams.WRAP_CONTENT));

			view.measure(desiredWidth, MeasureSpec.UNSPECIFIED);
			totalHeight += view.getMeasuredHeight();
		}
		LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight
				+ (listView.getDividerHeight() * (lA.getCount() - 1));
		listView.setLayoutParams(params);
		listView.requestLayout();
	}

	private String[] getCase(int swicher) {
		ListViewArrays lvArray = new ListViewArrays();
		populateA = null;
		String name = null;
		switch (switcher) {
		// profile
		case 1:
			populateA = lvArray.profiles;
			ib.setImageResource(R.drawable.modebars_feedbar);
			ib.setScaleType(ImageButton.ScaleType.FIT_XY);
			name = "Profile";
			break;
		// play
		case 2:
			populateA = lvArray.play;
			ib.setImageResource(R.drawable.interpretbackground);
			ib.setScaleType(ImageButton.ScaleType.FIT_XY);
			// hier screenshot von mit annasplaylist
			name = "Abspielen";
			break;
		// setting
		case 3:
			Intent timeDate = new Intent(this, TimeDate.class);
			startActivityForResult(timeDate, 1);
			//populateA = lvArray.settings;
			break;
		// sound
		case 4:
			populateA = lvArray.sounds;
			ib.setImageResource(R.drawable.boxen);
			ib.setScaleType(ImageButton.ScaleType.FIT_XY);
			name = "Soundeinstellungen";
			break;
		// friend
		case 5:
			populateA = lvArray.friends;
			ib.setImageResource(R.drawable.freundebackground);
			ib.setScaleType(ImageButton.ScaleType.FIT_XY);
			name = "Freunde";
			break;
		// device
		case 6:
			populateA = lvArray.devices;
			ib.setImageResource(R.drawable.deviceusb);
			ib.setScaleType(ImageButton.ScaleType.FIT_XY);
			name = "Geräte";
			break;
		// shazam
		case 7:
			lv.setVisibility(View.GONE);
			ib.setImageDrawable(getResources().getDrawable(R.drawable.shazam));
            ib.setBackgroundColor(Color.TRANSPARENT);
			name = "Shazam";
			return populateA;
		default:
			name = "Default";
			populateA = new String[] { "ERROR", "please restart" };
			break;
		}
		header.setText(name);
		return populateA;
	}

	@Override
	public void onClick(View arg0) {
		Toast.makeText(getApplicationContext(), "Searching for Song...", 
				Toast.LENGTH_LONG).show();
	}

	@Override
	public void onItemClick(AdapterView<?> arg, View item, int pos, long arg3) {
		//hier item in liste �berpr�fen und je nach item neues int an intend �bergeben
		// um in neuer activity neues layout / liste aufzurufen
	Intent i2nd = new Intent(this, SettingsSpecific.class);
			i2nd.putExtra("stringC", populateA[pos]);
			i2nd.putExtra("stringC", pos);
			i2nd.putExtra("stringC2", switcher);
	startActivityForResult(i2nd, -1);
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent returner) {
		System.out.println("im finish");
		finish();
	 }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
