package id.pangu.cecimpedan.Util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import id.pangu.cecimpedan.R;

public class Preference {

    private SharedPreferences prefs;
    private Context context;

    public Preference(Context context) {
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        this.context = context;
    }

    public void setFirstRun(Boolean input){
        SharedPreferences.Editor editor = prefs.edit();
        String key = context.getResources().getString(R.string.first_run);
        editor.putBoolean(key, input);
        editor.apply();
    }

    public Boolean getFirstRun(){
        String key = context.getResources().getString(R.string.first_run);
        return prefs.getBoolean(key, true);
    }
}
