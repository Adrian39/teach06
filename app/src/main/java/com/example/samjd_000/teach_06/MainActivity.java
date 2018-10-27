package com.example.samjd_000.teach_06;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import java.util.List;

import static android.R.layout.simple_expandable_list_item_1;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;
    private List<String> numbers;
    private String mFileName = "numbers.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setProgress(0);
        progressBar.setMax(10);
        listView = findViewById(R.id.lvNumbers);
        Button btnCreate = findViewById(R.id.btnCreate);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TaskCreate mTask = new TaskCreate(getApplicationContext(),mFileName, progressBar);
                mTask.execute();
            }
        });

        Button btnLoad = findViewById(R.id.btnLoad);
        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TaskLoad mLoad = new TaskLoad(getApplicationContext(),
                        mFileName,
                        progressBar,
                        listView);
                mLoad.execute();
                numbers = mLoad.getItems();
            }
        });

        Button btnClear = findViewById(R.id.btnClear);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearList();
            }
        });
    }
    public void clearList() {
        numbers.clear();
        arrayAdapter = new ArrayAdapter<String>(this,
                simple_expandable_list_item_1,
                numbers);
        listView.setAdapter(arrayAdapter);
    }
}
