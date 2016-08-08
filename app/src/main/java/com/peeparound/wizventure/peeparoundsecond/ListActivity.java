package com.peeparound.wizventure.peeparoundsecond;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Amaresh on 24-07-2016.
 */
public class ListActivity extends Activity implements LocationListener,AdapterView.OnItemClickListener {
    GoogleMap map;
    private static final String GOOGLE_API_KEY = "AIzaSyB4XeZsxstbxHomPVCBcs-ZYxa_eAH3-f0";
    private int PROXIMITY_RADIUS = 1500;
    private Button atm;
    private Button pub;
    private Button theater;
    private Button hospital;
    private Button temple;
    double latitude = 0;
    double longitude = 0;
    String googleUrl="https://maps.googleapis.com/maps/api/place/nearbysearch/json?";
    static final String KEY_TITLE = "title";
    ListView list;
    LazyAdapter adapter;
    String str;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listofplaces);

        list = (ListView) findViewById(R.id.list);
        list.setOnItemClickListener(this);
        PlacesDisplayTask pc=new PlacesDisplayTask(this);
        Bundle bndl=getIntent().getExtras();
        str=bndl.getString("places");
       LocationManager locationManager;
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);

/*
        if (location != null) {
            onLocationChanged(location);
        }
*/
       // locationManager.requestLocationUpdates(bestProvider, 20000, 0, this);





    }

    private boolean isGooglePlayServicesAvailable() {
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (ConnectionResult.SUCCESS == status) {
            return true;
        } else {
            GooglePlayServicesUtil.getErrorDialog(status, this, 0).show();
            return false;
        }
    }


    @Override
    public void onLocationChanged(Location location) {

        latitude = location.getLatitude();
        longitude = location.getLongitude();
        LatLng latLng = new LatLng(latitude, longitude);
        System.out.println("latitudelatitudelatitude"+latitude+"longitudelongitudelongitude"+longitude);
        // map.clear();;
        StringBuilder googlePlacesUrl = new StringBuilder(googleUrl);
        googlePlacesUrl.append("location=" + latitude + "," + longitude);
        googlePlacesUrl.append("&radius=" + PROXIMITY_RADIUS);
        googlePlacesUrl.append("&types=" + str);
        googlePlacesUrl.append("&sensor=true");
        googlePlacesUrl.append("&key=" + GOOGLE_API_KEY);
        Log.d("ATM URL:",""+googlePlacesUrl);
        GooglePlacesReadTask googlePlacesReadTask = new GooglePlacesReadTask(this);
        Object[] toPass = new Object[2];
        toPass[0]=list;
        toPass[1] = googlePlacesUrl.toString();
        googlePlacesReadTask.execute(toPass);
       /* map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        map.animateCamera(CameraUpdateFactory.zoomTo(14f));
        MarkerOptions markerOptions=new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.logo));;
        markerOptions.position(latLng);
        markerOptions.title(" Me" );
        map.addMarker(markerOptions);
*/    }

    @Override
    public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


        Intent intent=new Intent(this,Details.class);
        startActivity(intent);

    }
}