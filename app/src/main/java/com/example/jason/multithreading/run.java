package com.example.jason.multithreading;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class run extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_run, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }

    public void create(View view) {

        AsyncTask<Object, Integer, String> task = new AsyncTask<Object, Integer, String>() {

            @Override
            protected String doInBackground(Object... params) {

                try {
                    String filename = "numbers.txt";
                    FileOutputStream outputStream;

                    outputStream = openFileOutput(filename, Context.MODE_PRIVATE);

                    for (int i = 1; i <= 10; ++i) {
                        outputStream.write(i);
                        Thread.sleep(250);
                        publishProgress(i);
                    }

                    outputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return "Completed";
            }

            @Override
            protected void onProgressUpdate(Integer... in) {
                ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
                progressBar.setProgress(in[0]);

            }
            @Override
            protected void onPreExecute() {
                // create button
                Button create = (Button) findViewById(R.id.create);
                create.setEnabled(false);
                // load button
                Button load = (Button) findViewById(R.id.load);
                load.setEnabled(false);
                // clear button
                Button clear = (Button) findViewById(R.id.clear);
                clear.setEnabled(false);
            }

            @Override
            protected void onPostExecute(String result) {
                // create button
                Button create = (Button) findViewById(R.id.create);
                create.setEnabled(true);
                // load button
                Button load = (Button) findViewById(R.id.load);
                load.setEnabled(true);
                // clear button
                Button clear = (Button) findViewById(R.id.clear);
                clear.setEnabled(true);
            }
        };

        task.execute();

    }

    public void load (View view) {

        AsyncTask<Object, Integer, ArrayList<String>> task = new AsyncTask<Object, Integer,
                ArrayList<String>>() {

            @Override
            protected ArrayList<String> doInBackground(Object... params) {
                ArrayList<String> result = new ArrayList<>();
                try {
                    String filename = "numbers.txt";
                    FileInputStream inputStream;
                    inputStream = openFileInput(filename);
                    for (int i = 1; i <= 10; ++i) {
                        result.add(String.valueOf(inputStream.read()));
                        Thread.sleep(250);
                        publishProgress(i);
                    }
                    inputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return result;
            }

            @Override
            protected void onProgressUpdate(Integer... in) {
                ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
                progressBar.setProgress(in[0]);
            }

            @Override
            protected void onPreExecute() {
                // create button
                Button create = (Button) findViewById(R.id.create);
                create.setEnabled(false);
                // load button
                Button load = (Button) findViewById(R.id.load);
                load.setEnabled(false);
                // clear button
                Button clear = (Button) findViewById(R.id.clear);
                clear.setEnabled(false);
            }

            @Override
            protected void onPostExecute(ArrayList<String> result) {
                // create button
                Button create = (Button) findViewById(R.id.create);
                create.setEnabled(true);
                // load button
                Button load = (Button) findViewById(R.id.load);
                load.setEnabled(true);
                // clear button
                Button clear = (Button) findViewById(R.id.clear);
                clear.setEnabled(true);

                // fill the ListView
                ArrayAdapter<String> adapter = new ArrayAdapter<String>
                        (run.this, android.R.layout.simple_list_item_1, result);
                ListView listView = (ListView) findViewById(R.id.listView);
                listView.setAdapter(adapter);
            }
        };
        task.execute();
    }

    public void clear (View view) {

        AsyncTask<Object, Integer, ArrayList<String>> task = new AsyncTask<Object, Integer,
                ArrayList<String>>() {

            @Override
            protected ArrayList<String> doInBackground(Object... params) {
                ArrayList<String> result = new ArrayList<>();
                publishProgress(0);
                return result;
            }

            @Override
            protected void onProgressUpdate(Integer... in) {
                ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
                progressBar.setProgress(in[0]);

            }

            @Override
            protected void onPreExecute() {
                // create button
                Button create = (Button) findViewById(R.id.create);
                create.setEnabled(false);
                // load button
                Button load = (Button) findViewById(R.id.load);
                load.setEnabled(false);
                // clear button
                Button clear = (Button) findViewById(R.id.clear);
                clear.setEnabled(false);
            }

            @Override
            protected void onPostExecute(ArrayList<String> result) {
                // create button
                Button create = (Button) findViewById(R.id.create);
                create.setEnabled(true);
                // load button
                Button load = (Button) findViewById(R.id.load);
                load.setEnabled(true);
                // clear button
                Button clear = (Button) findViewById(R.id.clear);
                clear.setEnabled(true);

                // fill the ListView
                ArrayAdapter<String> adapter =
                        new ArrayAdapter<String>(run.this, android.R.layout.simple_list_item_1,
                                result);
                ListView listView = (ListView) findViewById(R.id.listView);
                listView.setAdapter(adapter);
            }
        };
        task.execute();
    }
}