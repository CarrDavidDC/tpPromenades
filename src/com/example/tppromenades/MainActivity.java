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

import android.location.Location;
import android.location.LocationManager;

import ihm.Accueil;
import ihm.Inscription;
import ihm.MdpOublie;

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
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.MotionEvent;
import android.widget.Toast;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;

public class MainActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
   
		ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

		if (mWifi.isConnected()) {
			DatabaseHandler db = new DatabaseHandler(this);
			DownloadData d = (DownloadData) new DownloadData(this, db, "https://download.data.grandlyon.com/ws/grandlyon/evg_esp_veg.evgsentiernature/all.json").execute();
		}
		else {
			Toast.makeText(this.getApplicationContext(), "Unable to download data without Wifi connection", Toast.LENGTH_LONG).show();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent evt)
	{
	    if(evt.getAction() == MotionEvent.ACTION_DOWN)
	    {
	    	Intent intent = new Intent(MainActivity.this, Accueil.class);
			startActivity(intent);
	    }
	    return true;
	}  
}
