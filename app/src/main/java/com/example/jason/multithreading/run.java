package com.example.jason.multithreading;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class run extends ActionBarActivity {
    String FILE_NAME = "numberFile";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_run, menu);
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

    public void createFile(View view){
        String filename = FILE_NAME;
        new File(this.getFilesDir(), filename);
        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            for(int x = 1; x < 11; x++){
                String output = x + "\n";
                outputStream.write(output.getBytes());
            }
            outputStream.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }


    public void loadFile(View view) {
        String filename = FILE_NAME;
        String line;
        ListView listView = (ListView) findViewById(R.id.listView);
        ArrayList<String> numbers = new ArrayList<>();
        ArrayAdapter<String> listAdapter;

        try {
            FileInputStream inputStream = this.openFileInput(filename);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            while ((line = reader.readLine()) != null){
                numbers.add(line);
            }
            listAdapter = new ArrayAdapter<>(this, R.layout.simplerow, numbers);
            listView.setAdapter(listAdapter);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
}
