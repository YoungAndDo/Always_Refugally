package com.example.always_refugally.Util;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

/**
 * Created by yd199 on 2016-12-01.
 */

public class LoadingDialog extends AsyncTask<Void, Void, Void> {

    Context context;
    ProgressDialog pd;
    LoadingTask loadingTask;

    public LoadingDialog(Context context, LoadingTask loadingTask){
        this.context = context;
        this.loadingTask = loadingTask;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        loadingTask.task();
        return null;
    }

    @Override
    protected void onPreExecute(){
        pd = ProgressDialog.show(context, "Loading", "wait...", true);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        pd.dismiss();
    }
}
