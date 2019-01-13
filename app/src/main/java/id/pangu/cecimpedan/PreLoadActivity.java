package id.pangu.cecimpedan;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ProgressBar;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.pangu.cecimpedan.Database.CecimpedanHelper;
import id.pangu.cecimpedan.Model.CecimpedanItem;
import id.pangu.cecimpedan.Util.Preference;

public class PreLoadActivity extends Activity {

    @BindView(R.id.progress_bar) ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_load);
        ButterKnife.bind(this);

        new LoadData().execute();
    }

    private class LoadData extends AsyncTask<Void, Integer, Void>{
        final String TAG = LoadData.class.getSimpleName();
        CecimpedanHelper cecimpedanHelper;
        Preference pref;
        double progress;

        @Override
        protected void onPreExecute() {
            //super.onPreExecute();
            cecimpedanHelper = new CecimpedanHelper(PreLoadActivity.this);
            pref = new Preference(PreLoadActivity.this);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Boolean firstRun = pref.getFirstRun();

            if(firstRun){

                ArrayList<CecimpedanItem> items = preLoadRaw();
                cecimpedanHelper.open();
                for (CecimpedanItem item : items) {
                    cecimpedanHelper.insert(item);
                }
                cecimpedanHelper.close();
                pref.setFirstRun(false);
            } else {
                try {
                    synchronized (this) {
                        this.wait(2000);
                    }
                } catch (Exception e) {
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            startActivity(new Intent(PreLoadActivity.this, MainActivity.class));
            finish();
        }
    }

    public ArrayList<CecimpedanItem> preLoadRaw() {

        ArrayList<CecimpedanItem> items = new ArrayList<>();
        String line = null;
        BufferedReader reader;
        try {
            Resources res = getResources();
            InputStream raw_dict = res.openRawResource(R.raw.data_cecimpedan);
            reader = new BufferedReader(new InputStreamReader(raw_dict));
            int count = 0;
            do {
                line = reader.readLine();
                String[] splitstr = line.split("=");

                CecimpedanItem item;

                item = new CecimpedanItem(splitstr[0], splitstr[1]);
                items.add(item);
                count++;
            } while (line != null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return items;
    }
}
