package com.example.samjd_000.teach_06;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static android.R.layout.simple_expandable_list_item_1;

public class TaskLoad extends AsyncTask<Void, Integer, Void> {
    private Context mContext;
    private String mFileName;
    private ProgressBar mProgressBar;
    private List items = new ArrayList<String>();
    private ListView mListView;
    private int counter;
    ArrayAdapter<String> arrayAdapter;

    TaskLoad(Context context, String fileName, ProgressBar progressBar, ListView listView){
        this.mContext = context;
        this.mFileName = fileName;
        this.mProgressBar = progressBar;
        this.mListView = listView;
    }
    public List getItems(){
        return items;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mProgressBar.setProgress(0);
    }

    @Override
    protected Void doInBackground(Void... voids) {

        try {
            File directory = mContext.getFilesDir();
            Scanner scanner = new Scanner(new File(directory, mFileName));
            while (scanner.hasNextLine()){
                String value = scanner.nextLine();
                items.add(value);
                counter+=1;
                publishProgress(counter);
                Thread.sleep(250);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        mProgressBar.setProgress(values[0]);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        arrayAdapter = new ArrayAdapter<String>(mContext,
                simple_expandable_list_item_1,
                items);
        mListView.setAdapter(arrayAdapter);
    }
}
