package com.example.tppromenades;


import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapController;

import android.location.Location;
import android.location.LocationManager;
import com.google.android.maps.MapActivity;

import java.util.ArrayList;

import bdd.DatabaseHandler;
import bdd.DownloadData;
import bdd.TablePromenade;
import metier.Promenade;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.widget.Toast;
import android.support.v4.app.FragmentActivity;

public class MainActivity extends Activity implements LocationListener{

	 static final LatLng MUMBAI = new LatLng(19.0144100, 72.8479400);
     private GoogleMap map;
 	private double							lat								= 0;
 	private double							lng								= 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
<<<<<<< HEAD
        setContentView(R.layout.activity_main);
        if (map == null) {
        	map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
                                .getMap();
            // Check if we were successful in obtaining the map.
            if (map != null) {
                // The Map is verified. It is now safe to manipulate the map.
            	Toast toast = Toast.makeText(getApplicationContext(), "LA MAP N EST PAS NULL", Toast.LENGTH_SHORT);
                toast.show();
                map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                
                CreateCourse createCourse = new CreateCourse("Ma première course","Description de la course",map);
                createCourse.createCourse();
                Polygon polygon = map.addPolygon(createCourse.getRectOptions());
               
                map.setMyLocationEnabled(true);
                map.animateCamera(CameraUpdateFactory.zoomIn());
                toast = Toast.makeText(getApplicationContext(), "Builder OK", Toast.LENGTH_SHORT);
                toast.show();
                
                CameraPosition cameraPosition = new CameraPosition.Builder().target(createCourse.getLatLng().get(0)).zoom(10.0f).build();
                CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
                map.moveCamera(cameraUpdate);                  
                
                
            }else{
            	Toast toast = Toast.makeText(getApplicationContext(), "LA MAP EST NULL", Toast.LENGTH_SHORT);
                toast.show();
                
            }
        }

		ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

		if (mWifi.isConnected()) {
			DatabaseHandler db = new DatabaseHandler(this);
			Toast.makeText(this.getApplicationContext(), "Create file database ok", Toast.LENGTH_LONG).show();
			DownloadData d = (DownloadData) new DownloadData(this, db, "https://download.data.grandlyon.com/ws/grandlyon/evg_esp_veg.evgsentiernature/all.json").execute();
		}
		else {
			Toast.makeText(this.getApplicationContext(), "Unable to download data without Wifi connection", Toast.LENGTH_LONG).show();
		}
	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		lat = location.getLatitude();
		lng = location.getLongitude();
		Toast.makeText(getBaseContext(),
				"Location change to : Latitude = " + lat + " Longitude = " + lng,
				Toast.LENGTH_SHORT).show();
=======
		setContentView(R.layout.activity_main);
>>>>>>> refs/remotes/origin/dev_matthieu
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
