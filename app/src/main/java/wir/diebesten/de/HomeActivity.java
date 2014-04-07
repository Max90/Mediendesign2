package wir.diebesten.de;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

public class HomeActivity extends ActionBarActivity {

    public static boolean radioOn = false;
    static Context context;
    private Menu optionsMenu;
    private Boolean playState = false;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

       if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment()).commit();
        }ListViewArrays lvArray = new ListViewArrays();
      TextView txtView = (TextView) findViewById(R.id.tickertext); txtView.setText(lvArray.currentTicker[0]);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	  System.out.println("help!");
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.container);
        fragment.onActivityResult(requestCode, resultCode, data);
      
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
        if(id == R.id.radio){
            radioOn = !radioOn;
            context = this;
            invalidateOptionsMenu();
        }
//        if(id == R.id.shuffle_settings){
//            playState = !playState;
//            invalidateOptionsMenu();
//        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        //System.out.println("indtsdf " + menu.findItem(R.id.shuffle_settings).getItemId());
//      if  (!playState) {
//         menu.findItem(R.id.shuffle_settings).setIcon(R.drawable.repeat_256);
//    }
//    if(playState) {
//        menu.findItem(R.id.shuffle_settings).setIcon(R.drawable.shuffle_256);
//        }

        if(!radioOn){
            menu.findItem(R.id.radio).setIcon(R.drawable.cd_256);
        }
        if (radioOn) {
            menu.findItem(R.id.radio).setIcon(R.drawable.radio_256);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment implements
            OnClickListener {

        ImageButton profile, play, setting, sound, friend, device, shazam;
  	  String interpret, titel, album, genre, tickertext;
	    String ticker; TextView txtView;
	    ListViewArrays lvArray = new ListViewArrays();
        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
        
            View rootView = inflater.inflate(R.layout.activity_home, container,
                    false);
            if(getResources().getConfiguration().orientation==2){
                profile = (ImageButton) rootView.findViewById(R.id.profileButton);
                play = (ImageButton) rootView.findViewById(R.id.playButton);
                setting = (ImageButton) rootView.findViewById(R.id.settingButton);
                sound = (ImageButton) rootView.findViewById(R.id.soundButton);
                friend = (ImageButton) rootView.findViewById(R.id.friendButton);
                device = (ImageButton) rootView.findViewById(R.id.deviceButton);
                shazam = (ImageButton) rootView.findViewById(R.id.shazamButton);
                txtView =(TextView) rootView.findViewById(R.id.tickertext);
                txtView.setText(lvArray.currentTicker[0]);
                txtView.setVisibility(View.GONE);
                ticker = "  Interpret: "+interpret+" Titel: "+titel+
        				"  Album: "+album+"  Genre: "+genre+" ";
              
                if(getActivity().getIntent().getExtras() != null){
                ticker = getActivity().getIntent().getExtras().getString("tickertext");
                } else {
                	 ticker = "  Interpret: "+interpret+" Titel: "+titel+
             				"  Album: "+album+"  Genre: "+genre+" ";
                	 txtView.setText(ticker);
                }
                txtView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.coverellie, 0, 0, 0);
                ImageButton[] buttonA = { profile, play, setting, sound, friend,
                        device, shazam };
            	
                for (int i = 0; i < buttonA.length; i++) {
                    buttonA[i].setOnClickListener(this);
                    buttonA[i].setBackgroundColor(Color.TRANSPARENT);
                }
            }

            return rootView;
        }
        
        @Override
        public void onResume() {
        	// TODO Auto-generated method stub
        	super.onResume();
        	System.out.println("ICH BIN HIER");
        	if(getActivity().getIntent().getExtras() != null){
        		if (getActivity().getIntent().getExtras().getString("tickertext") != null) {
                ticker = getActivity().getIntent().getExtras().getString("tickertext");
                System.out.println(ticker+"INTENT");
                txtView.setText(ticker);
                } System.out.println("ich null");}else {
                	System.out.println("else");
                	 ticker = "  Interpret: "+interpret+" Titel: "+titel+
             				"  Album: "+album+"  Genre: "+genre+" ";
                	 txtView.setText(ticker);
                }
        }
        @Override
		public void onActivityResult(int requestCode, int resultCode, Intent data) {
        	System.out.println("im finish");
    		Bundle b = data.getExtras();
    		String tickers = b.getString("tickertext");
    		System.out.println(tickers + "WICHTIG");
    		txtView.setText(tickers);
        }

        @Override
        public void onClick(View v) {
            Intent i = new Intent(getActivity(), Settings.class);
            switch(v.getId()) {
                case R.id.profileButton:
                    i.putExtra( "stringB",1);
                    break;
                case R.id.playButton:
                    if(radioOn == true) {
                        Intent radio = new Intent(context , RadioActivity.class);
                        startActivity(radio);
                        return;
                    }else {
                        i.putExtra("stringB", 2);
                    }
                    break;
                case R.id.settingButton:
                    i.putExtra( "stringB",3);
                    break;
                case R.id.soundButton:
                    i.putExtra( "stringB",4);
                    break;
                case R.id.friendButton:
                    i.putExtra("stringB", 5);
                    break;
                case R.id.deviceButton:
                    i.putExtra("stringB",6);
                    break;
                case R.id.shazamButton:
                    i.putExtra("stringB", 7);
                    break;
                default:
                    break;
            }
            startActivity(i);
        }
     
    }

}