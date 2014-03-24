package wir.diebesten.de;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.TextureView;
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
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class Seekbars extends ActionBarActivity implements
		OnSeekBarChangeListener {

	SeekBar sLoud, sHigh, sBass, sEqualizer;
	ListViewArrays lva;
	TextView header;
	int[] profileValues;
	Intent end;
	int positionIntent, positionIn;
	Intent get;
	Bundle getB;
	TextView tLoud, tHigh, tBass, tEqualizer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.seekbar);
		header = (TextView) findViewById(R.id.settings_header);
		sLoud = (SeekBar) findViewById(R.id.seekbarLaut);
		sHigh = (SeekBar) findViewById(R.id.seekbarHigh);
		sBass = (SeekBar) findViewById(R.id.seekbarBass);
		sEqualizer = (SeekBar) findViewById(R.id.seekbarEqualizer);
		tLoud = (TextView)findViewById(R.id.textviewLaut);
		tHigh = (TextView)findViewById(R.id.textviewHigh);
		tBass = (TextView)findViewById(R.id.textviewBass);
		tEqualizer=(TextView)findViewById(R.id.textviewEqualizer);
		sLoud.setOnSeekBarChangeListener(this);
		sHigh.setOnSeekBarChangeListener(this);
		sBass.setOnSeekBarChangeListener(this);
		sEqualizer.setOnSeekBarChangeListener(this);
		end = new Intent(this, HomeActivity.class);
		get = getIntent();
		getB = get.getExtras();
		setResult(-1, end);
		if (getB.getInt("posOfTitle")==4){
		setSoundeinstellungen();
		} else {
		checkWhichGenreForPreSettings();}
	}
	
	private void setSoundeinstellungen() {
		tLoud.setText("Box 1");
		tEqualizer.setText("Box 2");
		tBass.setText("Box 3");
		tHigh.setText("Box 4");
		lva = new ListViewArrays();
		String name = lva.profileSpecific[getB.getInt("posInTitle")];
		header.setText(name);
	}

	private void checkWhichGenreForPreSettings() {
		lva = new ListViewArrays();
		String name = lva.profiles[getB.getInt("posInTitle")];
		header.setText(name);
		positionIntent = getB.getInt("posOfTitle");
		positionIn = getB.getInt("posInTitle");
		System.out.println(positionIntent + "IMPORTANT" + positionIn);
		Intent returner = new Intent(this, SettingsSpecific.class);
		// returner.putExtra("posOfTitle", positionIntent);
		// returner.putExtra("posInTitle", positionIn );
		// System.out.println(positionIntent+" xxx "+positionIn);
		// setResult(Activity.RESULT_OK, returner);
		setResult(-1, returner);
		switch (positionIn) {
		// House
		case 0:
			profileValues = lva.house;
			break;
		// Pop
		case 1:
			profileValues = lva.pop;
			break;
		// Black
		case 2:
			profileValues = lva.black;
			break;
		// Jazz
		case 3:
			profileValues = lva.jazz;
			break;
		// Party_Modus
		case 4:
			profileValues = lva.partyModus;
			break;
		// SexyTime
		case 5:
			profileValues = lva.sexyTime;
			break;
		// Entspannung
		case 6:
			profileValues = lva.entspannung;
			break;
		// Hinzufügen
		case 7:
			profileValues = lva.hinzu;
			break;
		default:
			return;
		}
		sLoud.setProgress(profileValues[0]);
		sEqualizer.setProgress(profileValues[1]);
		sBass.setProgress(profileValues[2]);
		sHigh.setProgress(profileValues[3]);
	}

	@Override
	public void onPause() {
		finish();
		super.onPause();
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		if (getB.getInt("posOfTitle")==4){
			Toast.makeText(getApplicationContext(), progress+"", Toast.LENGTH_SHORT).show();
		} else {
		if (seekBar.getId() == R.id.seekbarBass) {
			Toast.makeText(getApplicationContext(),
					"Bass eingestellt: " + progress, Toast.LENGTH_SHORT).show();
			profileValues[2] = progress;
		}
		if (seekBar.getId() == R.id.seekbarLaut) {
			Toast.makeText(getApplicationContext(),
					"neue Lautstärke: " + progress, Toast.LENGTH_LONG).show();
			profileValues[0] = progress;
		}
		if (seekBar.getId() == R.id.seekbarHigh) {
			Toast.makeText(getApplicationContext(),
					"Höhen eingestellt: " + progress, Toast.LENGTH_LONG).show();
			profileValues[3] = progress;
		}
		if (seekBar.getId() == R.id.seekbarEqualizer) {
			Toast.makeText(getApplicationContext(),
					"Equalizer eingestellt: " + progress, Toast.LENGTH_LONG)
					.show();
			profileValues[1] = progress;
		}}
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {

	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub

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
