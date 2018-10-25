package com.example.samjd_000.teach_06;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.widget.ProgressBar;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "onLoad";
    ProgressBar progressBar = new ProgressBar(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setProgress(0);
    }
    public void create(View v) {

        FileOutputStream written = null;

        try {
            written = openFileOutput("numbers.txt", MODE_PRIVATE);
            String s = null;

            for(int i = 1; i <= 10; i++) {
                s = i + "\n";
                written.write(s.getBytes());
                progressBar.setProgress(i);
            }

            Thread.sleep(250);
            Toast.makeText(this, "Created", Toast.LENGTH_LONG).show();
            written.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void load(View v) {
        FileInputStream read = null;
        try {

            List<String> numbers = new ArrayList<String>();
            Context ctx = getApplicationContext();
            FileInputStream fileInputStream = ctx.openFileInput("numbers.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

/*
            read = openFileInput("numbers.txt");
            InputStreamReader sReader = new InputStreamReader(read);
            BufferedReader bReader = new BufferedReader(sReader);*/
            String s;

            while((s = bufferedReader.readLine()) != null) {
                numbers.add(s);
                //Toast.makeText(this, "Loaded \"" + s + "\"", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "currently reading " + s); // put this in the while loop
                Thread.sleep(1000);
                progressBar.setProgress(Integer.parseInt(s));
            }
            read.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    public void clear(View v) {

    }

}
