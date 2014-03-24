package wir.diebesten.de;

import java.util.ArrayList;
import java.util.Arrays;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class SettingsSpecific extends Activity implements
		OnClickListener, OnItemClickListener {

	ListView lv;
	ImageView iv;
	ImageButton ib;
	TextView header;
	ArrayAdapter<String> listAdapter;
	String title;
	Intent i2nd;
	Bundle extras;
	int posOfTitle, posInTitle;
	ListViewArrays lvArray;
	String[] populateA;
	int of, in;
	ArrayList<String> objectList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);
		lv = (ListView) findViewById(R.id.listView);
		ib = (ImageButton) findViewById(R.id.imageButton);
		ib.setBackgroundColor(Color.TRANSPARENT);
		of = getIntentContent()[0];
		in = getIntentContent()[1];
		header = (TextView)findViewById(R.id.settings_header);
		handleListViewPopulation(of, in);
	}

	@SuppressLint("NewApi")
	private void handleListViewPopulation(int of, int in) {
		objectList = new ArrayList<String>();
		if (getCase(getIntentContent()[0], getIntentContent()[1]) != null) {
			objectList.addAll(Arrays.asList(getCase(of, in)));
			listAdapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_1, objectList);
			lv.setAdapter(listAdapter);
			setListViewHeightBasedOnChildren(lv);
			listAdapter.notifyDataSetChanged();
			lv.setOnItemClickListener(this);
			if (posOfTitle == 6) {
				ib.setVisibility(View.GONE);
				LayoutParams lp = (LayoutParams) lv.getLayoutParams();
			  int width = lp.width;
			  lp.width = width*2;
			  lv.setLayoutParams(lp);
			  lv.setOnItemClickListener(null);
			}
		}
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

	private int[] getIntentContent() {
		i2nd = getIntent();
		extras = i2nd.getExtras();
		//title = extras.getString("stringC");
		posInTitle = extras.getInt("stringC");
		posOfTitle = extras.getInt("stringC2");
		 int[] intents = new int[] {posOfTitle, posInTitle};
		 return intents;}
	
		
		
		private String[] getCase(int posOfTitle, int posInTitle) {
			lvArray = new ListViewArrays();
			populateA = null;
			String name = null;
		switch (posOfTitle) {
		// profile
		case 1:
			Intent seek = new Intent(this, Seekbars.class);
			seek.putExtra("posInTitle", posInTitle);
			seek.putExtra("posOfTitle", posOfTitle);
			startActivityForResult(seek, 1);
			return null;
			// play
		case 2:
			switch (posInTitle) {
			case 0:
				populateA = lvArray.playTitle;
				name = lvArray.play[0];
				break;
			case 1:
				populateA = lvArray.playInterpret;
				ib.setImageResource(R.drawable.artistokkid);
				ib.setScaleType(ImageButton.ScaleType.FIT_XY);
				name = lvArray.play[1];
				break;
			case 2:
				populateA = lvArray.playAlbum;
				ib.setImageResource(R.drawable.artistokkid);
				ib.setScaleType(ImageButton.ScaleType.FIT_XY);
				name = lvArray.play[2];
				break;
			case 3:
				populateA = lvArray.playGenre;
				ib.setImageResource(R.drawable.artistokkid);
				ib.setScaleType(ImageButton.ScaleType.FIT_XY);
				name = lvArray.play[3];
				break;
			case 4:
				populateA = lvArray.playlist;
				ib.setImageResource(R.drawable.artistokkid);
				ib.setScaleType(ImageButton.ScaleType.FIT_XY);
				name = lvArray.play[4];
				break;
			default:
				name = "default";
				break;
			}
			header.setText(name);
			// vorschau interpret
			return populateA;
			// setting
		case 3:
			Intent timeDate = new Intent(this, TimeDate.class);
			timeDate.putExtra("posInTitle", posInTitle);
			startActivityForResult(timeDate, 1);
			return null;
			// sound
		case 4:
			// hier revidieren
			// unterpunkte bass....
			// dann boxen�bersicht! mit jeweils 1er einstellm�glichkeit 
			// und auswahlh�kchen um alle einzustellen!!!
			Intent seeker = new Intent(this, Seekbars.class);
			seeker.putExtra("posInTitle", posInTitle);
			seeker.putExtra("posOfTitle", posOfTitle);
			startActivityForResult(seeker, 1);
			return null;
			// friend
		case 5:
			// mit playplaylist bzw. playlist des jeweiligen Freundes
			switch (posInTitle) {
			case 0:
				populateA = lvArray.playlist;
				name = lvArray.friends[0];
				break;
			case 1:
				populateA = lvArray.playlist;
				name = lvArray.friends[1];
				break;
			case 2:
				populateA = lvArray.playlist;
				name = lvArray.friends[2];
				break;
			case 3:
				populateA = lvArray.playlist;
				name = lvArray.friends[3];
				break;
			case 4:
				populateA = lvArray.playlist;
				name = lvArray.friends[4];
				break;
			case 5:
				populateA = lvArray.playlist;
				name = lvArray.friends[5];
				break;
			case 6:
				populateA = lvArray.playlist;
				name = lvArray.friends[6];
				break;
			case 7:
				populateA = lvArray.playlist;
				name = lvArray.friends[7];
				break;
			case 8:
				populateA = lvArray.playlist;
				name = lvArray.friends[8];
				break;
			case 9:
				populateA = lvArray.playlist;
				name = lvArray.friends[9];
				break;
			case 10:
				// unclickable! setzten!
				
			default:
				name = "default";
				break;
			}
			header.setText(name);
			// vorschau playPlaylist
			ib.setImageResource(R.drawable.playlistannaspartysp);
			ib.setScaleType(ImageButton.ScaleType.FIT_XY);
			return populateA;
		// device
		case 6:
			// hier playplaylist des jeweiligen Device 
			// von settings im intent pos mitnehmen in liste und nach listenpos
			// verschiedene playlists
			switch (posInTitle) {
			case 0:
				populateA = lvArray.playPlaylist;
				name = lvArray.devices[0];
				break;
			case 1:
				populateA = lvArray.playPlaylist;
				name = lvArray.devices[1];
				break;
			case 2:
				populateA = lvArray.playPlaylist;
				name = lvArray.devices[2];
				break;
			case 3:
				populateA = lvArray.playPlaylist;
				name = lvArray.devices[3];
				break;
		default:
			populateA = new String[] { "ERROR", "please restart" };
			break;
		}
			header.setText(name);
			ib.setImageResource(R.drawable.deviceusb);
		return populateA;
	}
		header.setText(name);return populateA;
		}
		
	@Override
	public void onClick(View arg0) {	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long arg3) {
		if (posOfTitle == 2 || posOfTitle == 5) {
			System.out.println("Im in!");
			Intent i3nd = new Intent(this, LastLayer.class);
			i3nd.putExtra("whichCase", posInTitle);
			i3nd.putExtra("whichOneInPos", pos);
			i3nd.putExtra("posOfTitle", posOfTitle);
			startActivityForResult(i3nd, -1);
		}
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
