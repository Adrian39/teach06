package com.example.samjd_000.teach_06;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.Toast;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class TaskCreate extends AsyncTask <Void, Integer, Void>{
    private Context mContext;
    private String mFileName;
    private ProgressBar mProgressBar;

    TaskCreate(Context context, String fileName, ProgressBar progressBar){
        this.mContext = context;
        this.mFileName = fileName;
        this.mProgressBar = progressBar;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mProgressBar.setProgress(0);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try{
            FileOutputStream outputStream = mContext.openFileOutput(mFileName, Context.MODE_PRIVATE);
            for (int i = 0; i < 10; i++){
                String input = i + "\n";
                outputStream.write(input.getBytes());
                publishProgress(i+1);
                Thread.sleep(250);
            }
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        mProgressBar.setProgress(values[0]);
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        mProgressBar.setProgress(10);
        Toast.makeText(mContext, "Created", Toast.LENGTH_LONG).show();
        super.onPostExecute(aVoid);
    }
}
