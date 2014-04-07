package wir.diebesten.de;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.MeasureSpec;
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
import android.widget.Toast;

public class LastLayer extends ActionBarActivity implements OnItemClickListener {

	ListView lv;
	ImageView iv;
	ImageButton ib;
	TextView header, tickertext;
	ArrayAdapter<String> listAdapter;
	String title, name;
	Intent i2nd;
	Bundle extras;
	int posOfTitle, posInTitle;
	ListViewArrays lvArray;
	String[] populateA;
	ArrayList<String> objectList;
	int switcher;
	Intent i, end;
	String interpret, genre, album, titel, ticker;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);
		
		lv = (ListView) findViewById(R.id.listView);
		ib = (ImageButton) findViewById(R.id.imageButton);
		header = (TextView) findViewById(R.id.settings_header);
		end = new Intent(this, HomeActivity.class);
		tickertext = (TextView)findViewById(R.id.tickertext);
		lvArray = new ListViewArrays();
		tickertext.setText(lvArray.currentTicker[0]);
		lv.setOnItemClickListener(this);
		handleListViewPopulation();
	}

	private void handleListViewPopulation() {
		lvArray = new ListViewArrays();
		objectList = new ArrayList<String>();
		objectList.addAll(Arrays.asList(getCase(getSwitcher())));
		listAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, objectList);
		lv.setAdapter(listAdapter);
		setListViewHeightBasedOnChildren(lv);
		listAdapter.notifyDataSetChanged();
		ib.setVisibility(View.GONE);
		LayoutParams lp = (LayoutParams) lv.getLayoutParams();
		int width = lp.width;
		lp.width = width * 2;
		lv.setLayoutParams(lp);
	}

	private int getSwitcher() {
		i = getIntent();
		extras = i.getExtras();
		// posInTitle
		switcher = extras.getInt("whichCase");
		return switcher;
	}

	private String[] getCase(int swicher) {
		populateA = null;
		posOfTitle = extras.getInt("posOfTitle");
		if (posOfTitle == 2) {
			switch (switcher) {
			// titel
			case 0:
				switch (extras.getInt("whichOneInPos")) {
				// Rose
				case 0:
					// �berlegen ob bei Titel nicht durch titelklick
					// action beendet und zur�ck zu startactivity
					break;
				}
				// interpret
			case 1:
				switch (extras.getInt("whichOneInPos")) {
				case 0:
					populateA = lvArray.listRose;
					name = "Rose";
					break;
				case 1:
					populateA = lvArray.listDybdahl;
					name = "Thomas Dybdahl";
					break;
				case 2:
					populateA = lvArray.listMantey;
					name = "Mantey";
					break;
				case 3:
					populateA = lvArray.listKodaline;
					name = "Kodaline";
					break;
				case 4:
					populateA = lvArray.listSkunkAnansie;
					name = "Skunk Anansie";
					break;
				case 5:
					populateA = lvArray.listAmosLee;
					name = "Amos Lee";
					break;
				case 6:
					populateA = lvArray.listOK_KID;
					name = "OK KID";
					break;
				case 7:
					populateA = lvArray.listGoulding;
					name = "Ellie Goulding";
					break;
				case 8:
					populateA = lvArray.listCandy;
					name = "Brooke Candy";
					break;
				default:
					name = "Default";
					populateA = new String[] { "error" };
					break;
				}
				break;
			// album
			case 2:
				switch (extras.getInt("whichOneInPos")) {
				case 0:
					populateA = lvArray.albumLesSouvenirs;
					name = lvArray.playAlbum[0];
					break;
				case 1:
					populateA = lvArray.albumSongs;
					name = lvArray.playAlbum[1];
					break;
				case 2:
					populateA = lvArray.albumSoundcloud;
					name = lvArray.playAlbum[2];
					break;
				case 3:
					populateA = lvArray.albumInAPerfectWorld;
					name = lvArray.playAlbum[3];
					break;
				case 4:
					populateA = lvArray.albumBlackTraffic;
					name = lvArray.playAlbum[4];
					break;
				case 5:
					populateA = lvArray.albumAmosLee;
					name = lvArray.playAlbum[5];
					break;
				case 6:
					populateA = lvArray.albumOkKid;
					name = lvArray.playAlbum[6];
					break;
				case 7:
					populateA = lvArray.albumYoutube;
					name = lvArray.playAlbum[7];
					break;
				case 8:
					populateA = lvArray.albumEverybodyDoes;
					name = lvArray.playAlbum[8];
					break;
				default:
					populateA = new String[] { "error" };
					name = lvArray.playAlbum[9];
					break;
				}
				break;
			// genre
			case 3:
				switch (extras.getInt("whichOneInPos")) {
				case 0:
					populateA = lvArray.genrePop;
					name = lvArray.playGenre[0];
					break;
				case 1:
					populateA = lvArray.genreFolk;
					name = lvArray.playGenre[1];
					break;
				case 2:
					populateA = lvArray.genreElectro;
					name = lvArray.playGenre[2];
					break;
				case 3:
					populateA = lvArray.genreIndie;
					name = lvArray.playGenre[3];
					break;
				case 4:
					populateA = lvArray.genreRock;
					name = lvArray.playGenre[4];
					break;
				case 5:
					populateA = lvArray.genreFolkSoul;
					name = lvArray.playGenre[5];
					break;
				case 6:
					populateA = lvArray.genreDeutschrap;
					name = lvArray.playGenre[6];
					break;
				case 7:
					populateA = lvArray.genreRap;
					name = lvArray.playGenre[7];
					break;
				default:
					name = "default";
					populateA = new String[] { "error" };
					break;
				}
				break;
			// playlist
			case 4:
				name = "akutelle Playlist";
				switch (extras.getInt("whichOneInPos")) {
				case 0:
					populateA = lvArray.playPlaylist;
					name = lvArray.playlist[0];
					break;
				case 1:
					populateA = lvArray.playPlaylist;
					name = lvArray.playlist[1];
					break;
				case 2:
					populateA = lvArray.playPlaylist;
					name = lvArray.playlist[2];
					break;
				case 3:
					populateA = lvArray.playPlaylist;
					name = lvArray.playlist[3];
					break;
				default:
					populateA = lvArray.playPlaylist;
					name = "default";
					break;
				}
				break;
			default:
				populateA = new String[] { "ERROR", "please restart" };
				break;
			}
		} else if (posOfTitle == 5 || posOfTitle == 6) {
			populateA = lvArray.playPlaylist;
			switch (extras.getInt("whichOneInPos")) {
			case 0:
				name = lvArray.playlist[0];
				break;
			case 1:
				name = lvArray.playlist[1];
				break;
			case 2:
				name = lvArray.playlist[2];
				break;
			case 3:
				name = lvArray.playlist[3];
				break;
			default:
				populateA = lvArray.playPlaylist;
				break;
			}
		}

		header.setText(name);
		return populateA;
	}

	@Override
	public void onPause() {
		finish();
		super.onPause();
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

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Toast.makeText(getApplicationContext(), "Lied wird gespielt",
				Toast.LENGTH_SHORT).show();
		posOfTitle = extras.getInt("posOfTitle");
		interpret = "Rose"; genre ="Pop"; album="De ma fenetre"; titel =" Les Souvenirs";
		String ticker = "Interpret: "+interpret+" Titel: "+titel+
				" Album: "+album+" Genre: "+genre;
		tickertext.setText(ticker);
		if (posOfTitle == 2) {
			switch (switcher) {
			// titel
			case 0:
				switch (extras.getInt("whichOneInPos")) {
				case 0:
					interpret = "Rose"; titel = populateA[position]; album = name; genre="Folk";
					break;
				case 1:
					interpret = "Thomas Dybdahl"; titel = populateA[position]; album = name; genre="Folk";
					break;
				case 2:
					interpret = "Mantey"; titel = populateA[position]; album = name; genre="Electro";
					break;
				case 3:
					interpret = "Kodaline"; titel = populateA[position]; album = name; genre="Alternative";
					break;
				case 4:
					interpret = "Skunk Anansie"; titel = populateA[position]; album = name; genre="Rock";
					break;
				case 5:
					interpret = "Amos Lee"; titel = populateA[position]; album = name; genre="Folk";
					break;
				case 6:
					interpret = "OK KID"; titel = populateA[position]; album = name; genre="Rap";
					break;
				case 7:
					interpret = "Ellie Goulding"; titel = populateA[position]; album = name; genre="Pop";
					break;
				case 8:
					interpret = "Brooke Candy"; titel = populateA[position]; album = name; genre="HipHop";
					break;
				default:
					name = "Default";
					populateA = new String[] { "error" };
					break;
				}
				
				// interpret
			case 1:
				switch (extras.getInt("whichOneInPos")) {
				case 0:
					interpret = "Rose"; titel = populateA[position]; album = name; genre="Folk";
					break;
				case 1:
					interpret = "Thomas Dybdahl"; titel = populateA[position]; album = name; genre="Folk";
					break;
				case 2:
					interpret = "Mantey"; titel = populateA[position]; album = name; genre="Electro";
					break;
				case 3:
					interpret = "Kodaline"; titel = populateA[position]; album = name; genre="Alternative";
					break;
				case 4:
					interpret = "Skunk Anansie"; titel = populateA[position]; album = name; genre="Rock";
					break;
				case 5:
					interpret = "Amos Lee"; titel = populateA[position]; album = name; genre="Folk";
					break;
				case 6:
					interpret = "OK KID"; titel = populateA[position]; album = name; genre="Rap";
					break;
				case 7:
					interpret = "Ellie Goulding"; titel = populateA[position]; album = name; genre="Pop";
					break;
				case 8:
					interpret = "Brooke Candy"; titel = populateA[position]; album = name; genre="HipHop";
					break;
				default:
					name = "Default";
					populateA = new String[] { "error" };
					break;
				}
				break;
			// album
			case 2:
				switch (extras.getInt("whichOneInPos")) {
				case 0:
					interpret = "Rose"; titel = populateA[position]; album = "Les Souvenirs sous ma frange"; genre="Pop";
					break;
				case 1:
					interpret = "Thomas Dybdahl"; titel = populateA[position]; album = "Songs"; genre="Folk";
					break;
				case 2:
					interpret = "Jonas Mantey"; titel = populateA[position]; album = "Soundcloud"; genre="Electro";
					break;
				case 3:
					interpret = "Kodaline"; titel = populateA[position]; album = "In a perfect world"; genre="Alternative";
					break;
				case 4:
					interpret = "Skunk Anansie"; titel = populateA[position]; album = "Black Traffic"; genre="Rock";
					break;
				case 5:
					interpret = "Amos Lee"; titel = populateA[position]; album = "Amos Lee"; genre="Folk";
					break;
				case 6:
					interpret = "OK KID"; titel = populateA[position]; album = "OK KID"; genre="Rap";
					break;
				case 7:
					interpret = "Ellie Goulding"; titel = populateA[position]; album = "Youtube"; genre="Pop";
					break;
				case 8:
					interpret = "Brooke Candy"; titel = populateA[position]; album = "Youtube"; genre="Rap";
					break;
				default:
					populateA = new String[] { "error" };
					break;
				}
				break;
			// genre
			case 3:
				switch (extras.getInt("whichOneInPos")) {
				case 0:
					interpret = populateA[position]; titel = "On my knees"; album = "Songs"; genre="Pop";
					break;
				case 1:
					interpret = populateA[position]; titel = "Arms of a woman"; album = "Amos Lee"; genre="Folk";
					break;
				case 2:
					interpret = populateA[position]; titel = "Frei"; album = "Soundcloud"; genre="Electro";
					break;
				case 3:
					interpret = populateA[position]; titel = "High Hopes"; album = "In a perfect world"; genre="Indie";
					break;
				case 4:
					interpret = populateA[position]; titel = "Because of you"; album = "Skunk Anansie"; genre="Rock";
					break;
				case 5:
					interpret = populateA[position]; titel = "One day you dance for me NYC"; album = "Songs"; genre="Folk/Soul";
					break;
				case 6:
					interpret = populateA[position]; titel = "Kaffee warm"; album = "OK KID"; genre="Deutschrap";
					break;
				case 7:
					interpret = populateA[position]; titel = "Everybody does"; album = "Youtube"; genre="Rap";
					break;
				default:
					name = "default";
					populateA = new String[] { "error" };
					break;
				}
				break;
			// playlist
			case 4:
				name = "akutelle Playlist";
				switch (extras.getInt("whichOneInPos")) {
				case 0:
					interpret = "Jonas Mantey"; titel =  populateA[position]; album = "Soundcloub"; genre="Indie";
					break;
				case 1:
					interpret = "Thomas Dybdahl"; titel =  populateA[position]; album = "Songs"; genre="Electro";
					break;
				case 2:
					interpret = "Kodaline"; titel =  populateA[position]; album = "In a perfect world"; genre="Pop";
					break;
				case 3:
					interpret = "Ellie Goulding"; titel =  populateA[position]; album = "Youtube"; genre="Rock";
					break;
				default:
					name = "default";
					break;
				}
				break;
			default:
				populateA = new String[] { "ERROR", "please restart" };
				break;
			}
		} else if (posOfTitle == 5 || posOfTitle == 6) {
			switch (extras.getInt("whichOneInPos")) {
			case 0:
				interpret = "Jonas Mantey"; titel =  populateA[position]; album = "Soundcloub"; genre="Indie";
				break;
			case 1:
				interpret = "Thomas Dybdahl"; titel =  populateA[position]; album = "Songs"; genre="Electro";
				break;
			case 2:
				interpret = "Kodaline"; titel =  populateA[position]; album = "In a perfect world"; genre="Pop";
				break;
			case 3:
				interpret = "Ellie Goulding"; titel =  populateA[position]; album = "Youtube"; genre="Rock";
				break;
			default:
				populateA = lvArray.playPlaylist;
				break;
			}
		}
		ticker = "  Interpret: "+interpret+" Titel: "+titel+
				"  Album: "+album+"  Genre: "+genre+" ";
		lvArray.currentTicker[0]=ticker;
		end.putExtra("tickertext", ticker);
		setResult(-1, end);
	}

}
