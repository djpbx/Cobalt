package danski.dotareader;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {

    Button herolistbtn;
    Button matchesbtn;
    Button prefsbtn;
    Button tstmatch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        herolistbtn = (Button) findViewById(R.id.btn_herolist);
        herolistbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, HeroList.class));
            }
        });

        matchesbtn = (Button) findViewById(R.id.btn_matches);
        matchesbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*HeroArrayFiller hl = new HeroArrayFiller(MainActivity.this);
                Hero[] arrrayyayay = hl.getHeroes();*/
                startActivity(new Intent(MainActivity.this, MatchHistoryActivity.class));
            }
        });

        prefsbtn = (Button) findViewById(R.id.btn_prefs);
        prefsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Preferences.class));
            }
        });

        tstmatch = (Button) findViewById(R.id.testmatch);
        tstmatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent i = new Intent(MainActivity.this, MatchActivity.class);
                i.putExtra("matchid", 1579934962);
                startActivity(i);*/
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
