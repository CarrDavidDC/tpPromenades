package com.example.tppromenades;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.support.v4.app.FragmentActivity;
public class MainActivity extends FragmentActivity {

	 static final LatLng MUMBAI = new LatLng(19.0144100, 72.8479400);
     private GoogleMap map;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
     //   map.moveCamera(CameraUpdateFactory.newLatLngZoom(MUMBAI, 15));
        // Zoom in, animating the camera.
     //   map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
