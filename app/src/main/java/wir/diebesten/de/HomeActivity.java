package wir.diebesten.de;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
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
import android.widget.Toast;

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
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent returner) {
        System.out.println("im finish");
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
            radioOn = true;
            context = this;
        }
        if(id == R.id.cd){
            radioOn = false;
        }
        if(id == R.id.shuffle_settings){
            invalidateOptionsMenu();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        //System.out.println("indtsdf " + menu.findItem(R.id.shuffle_settings).getItemId());
        if  (!playState) {
            menu.findItem(R.id.shuffle_settings).setIcon(R.drawable.loop);
            playState = true;
        }else {
            menu.findItem(R.id.shuffle_settings).setIcon(R.drawable.media_shuffle);
            playState = false;
        }
        return super.onPrepareOptionsMenu(menu);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment implements
            OnClickListener {

        ImageButton profile, play, setting, sound, friend, device, shazam;

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.activity_home, container,
                    false);
            System.out.println(getResources().getConfiguration().orientation);
            if(getResources().getConfiguration().orientation==2){
                profile = (ImageButton) rootView.findViewById(R.id.profileButton);
                play = (ImageButton) rootView.findViewById(R.id.playButton);
                setting = (ImageButton) rootView.findViewById(R.id.settingButton);
                sound = (ImageButton) rootView.findViewById(R.id.soundButton);
                System.out.println(sound);
                System.out.println(sound.getResources());
                friend = (ImageButton) rootView.findViewById(R.id.friendButton);
                device = (ImageButton) rootView.findViewById(R.id.deviceButton);
                shazam = (ImageButton) rootView.findViewById(R.id.shazamButton);

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