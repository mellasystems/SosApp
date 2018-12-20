package ado.edu.itla.sosapp.repositorio;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Session {

    private SharedPreferences prefs;

    public Session(Context context) {
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void set(String key, String value) {
        prefs.edit().putString(key, value).commit();
    }

    public String get(String key) {
        String value = prefs.getString(key, "");
        return value;
    }
}