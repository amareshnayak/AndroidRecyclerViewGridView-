package com.peeparound.wizventure.peeparoundsecond;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import com.google.android.gms.maps.GoogleMap;

public class GooglePlacesReadTask extends AsyncTask<Object, Integer, String> {
    String googlePlacesData = null;
    GoogleMap googleMap;
    ListView lv;
    Context ctx;
    GooglePlacesReadTask(Context ctx){
        this.ctx=ctx;

    }

    @Override
    protected String doInBackground(Object... inputObj) {
        try {
            lv = (ListView) inputObj[0];
            String googlePlacesUrl = (String) inputObj[1];
            Http http = new Http();
            googlePlacesData = http.read(googlePlacesUrl);
            System.out.println("kkkkkkkkkkkkkkgooglePlacesData"+googlePlacesData);
            Log.d("Places read task", googlePlacesData);
        } catch (Exception e) {
            Log.d("Google Place Read Task", e.toString());
        }
        return googlePlacesData;
    }

    @Override
    protected void onPostExecute(String result) {
        PlacesDisplayTask placesDisplayTask = new PlacesDisplayTask(ctx);
        Object[] toPass = new Object[2];
        toPass[0] = lv;
        toPass[1] = result;
        placesDisplayTask.execute(toPass);
    }
}