package edu.svsu.cs403.cs403_fa2015;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;

public class gpcorser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpcorser);
        Log.d("DEBUG", "onCreate was just called!");
    }

    protected void onResume() {
        super.onResume();
        Log.d("DEBUG", "onResume was just called!");
    }

    protected void onPause() {
        super.onPause();
        Log.d("DEBUG", "onPause was just called!");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_selector, menu);
        return true;
    }

}


