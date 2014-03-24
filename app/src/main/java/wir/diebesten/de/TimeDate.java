package wir.diebesten.de;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class TimeDate extends ActionBarActivity implements
		OnSeekBarChangeListener, OnDateChangedListener, OnClickListener {

	SeekBar sb;
	ListViewArrays lva;
	ListView lv;
	TextView date, header;
	DatePicker dp;
	TimePicker tp;
	int day, month, year;
	final Context context = this;
	String WLANstring;
	Button b;
	ArrayAdapter<String> listAdapter;
	ArrayList <String> objectList;
	Intent returner;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.date);
		sb = (SeekBar)findViewById(R.id.seekbarBrightness);
		header = (TextView)findViewById(R.id.settings_header);
		header.setText("Einstellungen");
		lva = new ListViewArrays();
		sb.setProgress(lva.brightness);
		date = (TextView)findViewById(R.id.textviewDate);
		dp = (DatePicker)findViewById(R.id.datePicker);
		tp = (TimePicker)findViewById(R.id.timePicker);
		 Calendar c = Calendar.getInstance();
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);
		sb = (SeekBar)findViewById(R.id.seekbarBrightness);
		sb.setOnSeekBarChangeListener(this);
		returner = new Intent(this, HomeActivity.class);
		setResult(-1, returner);
//		date.setText(new StringBuilder()
//			.append(month + 1).append("-").append(day).append("-")
//			.append(year).append(" "));
		dp.init(year, month, day, this);
		b = (Button)findViewById(R.id.buttonWLAN);
		b.setOnClickListener(this);
	}
	

	@Override
	public void onClick(View v) {
		final Dialog dialog = new Dialog(context);
        dialog.setTitle( "Verbindung wählen" );
		dialog.setContentView(R.layout.dialog);
		lv=(ListView)dialog.findViewById(R.id.listViewDialog);
		objectList = new ArrayList<String>();
			objectList.addAll(Arrays.asList(lva.WLAN));
			System.out.println(lva.WLAN+"");
			listAdapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_1, objectList);
			lv.setAdapter(listAdapter);
			listAdapter.notifyDataSetChanged();
			lv.setOnItemClickListener(new OnItemClickListener()
			   {
			      @Override
			      public void onItemClick(AdapterView<?> adapter, View v, int pos,
			            long arg3) 
			      {
			    	  //WLANstring = lva.WLAN[pos];
			    	  dialog.dismiss();
			      }
			   });
 
		dialog.show();
	}
	
	 @Override
	    public void onPause() {
	       finish(); 
	       super.onPause();
	    }

	@Override
	public void onDateChanged(DatePicker arg0, int arg1, int arg2, int arg3) {
//		date.setText(new StringBuilder()
//		.append(month + 1).append("-").append(day).append("-")
//		.append(year).append(" "));
	}
	
	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		lva.brightness = progress;
//		 try {
//		      IHardwareService hardware = IHardwareService.Stub.asInterface(
//		ServiceManager.getService("hardware"));
//		      if (hardware != null) {
//		        hardware.setScreenBacklight(progress);
//		        System.out.println(progress+"");
//		        lva.brightness = progress;
//		      }
//		    } catch (RemoteException doe) {          
//		    }        
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
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
