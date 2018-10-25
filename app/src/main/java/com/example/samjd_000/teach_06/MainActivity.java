package com.example.samjd_000.teach_06;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ProgressBar;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static android.R.layout.simple_expandable_list_item_1;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "loading";
    ProgressBar progressBar;
    ListView listView;
    ArrayAdapter<String> arrayAdapter;
    List<String> numbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setProgress(0);
        progressBar.setMax(10);
        listView = findViewById(R.id.lvNumbers);
    }
    public void create(View v) {

        FileOutputStream written = null;
        progressBar.setProgress(0);

        try {
            written = openFileOutput("numbers.txt", MODE_PRIVATE);
            String s = null;

            for(int i = 1; i <= 10; i++) {
                s = i + "\n";
                written.write(s.getBytes());
                progressBar.setProgress(i);
                Thread.sleep(250);
            }
            Toast.makeText(this, "Created", Toast.LENGTH_LONG).show();
            written.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void load(View v) {
        progressBar.setProgress(0);
        FileInputStream read = null;
        try {

            numbers = new ArrayList<String>();
            int counter = 0;

            File directory = this.getFilesDir();
            Scanner scanner = new Scanner(new File(directory, "numbers.txt"));
            while (scanner.hasNextLine()){
                String value = scanner.nextLine();
                numbers.add(value);
                counter+=1;
                progressBar.setProgress(counter);
                Thread.sleep(250);
            }

            /* PREVIOUS CODE
            Context ctx = getApplicationContext();
            FileInputStream fileInputStream = ctx.openFileInput("numbers.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);


            read = openFileInput("numbers.txt");
            InputStreamReader sReader = new InputStreamReader(read);
            BufferedReader bReader = new BufferedReader(sReader);

            while((s = bufferedReader.readLine()) != null) {
                numbers.add(s);
                //Toast.makeText(this, "Loaded \"" + s + "\"", Toast.LENGTH_SHORT).show();
                //Log.d(TAG, "currently reading " + s); // put this in the while loop

                Thread.sleep(250);
                progressBar.setProgress(Integer.parseInt(s));
            }
            read.close();
            */

             arrayAdapter = new ArrayAdapter<String>(this,
                     simple_expandable_list_item_1,
                     numbers);
             listView.setAdapter(arrayAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    public void clear(View v) {
        numbers.clear();
        arrayAdapter = new ArrayAdapter<String>(this,
                simple_expandable_list_item_1,
                numbers);
        listView.setAdapter(arrayAdapter);
    }

}
