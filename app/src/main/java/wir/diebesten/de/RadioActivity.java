package wir.diebesten.de;

import java.util.ArrayList;
import java.util.Arrays;

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
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class RadioActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {

    ListView lv;
    ImageView iv;
    ImageButton ib;
    TextView header;
    ArrayAdapter<String> listAdapter;
    String title; Intent i2nd; Bundle extras; int posOfTitle, posInTitle;
    ListViewArrays lvArray;
    String[] populateA;
    ArrayList<String> objectList;
    int switcher;
    Intent i, end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        lv = (ListView) findViewById(R.id.listView);
        lv.setOnItemClickListener(this);
        ib = (ImageButton)findViewById(R.id.imageButton);
        header = (TextView)findViewById(R.id.settings_header);
        end = new Intent(this, HomeActivity.class);
        setResult(-1, end);
        handleListViewPopulation();
    }

    private void handleListViewPopulation() {
        lvArray = new ListViewArrays();
        objectList = new ArrayList<String>();
        objectList.addAll(Arrays.asList(lvArray.radio));
        listAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, objectList);
        lv.setAdapter(listAdapter);
        setListViewHeightBasedOnChildren(lv);
        listAdapter.notifyDataSetChanged();
        ib.setVisibility(View.GONE);
        LayoutParams lp = (LayoutParams) lv.getLayoutParams();
        int width = lp.width;
        lp.width = width*2;
        lv.setLayoutParams(lp);
    }
    @Override
    public void onItemClick(AdapterView<?> arg, View item, int pos, long arg3) {
        Toast.makeText(getApplicationContext(), lvArray.radio[pos] + " wird gespielt", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onPause() {
        System.out.println("finish finish");
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

}
