package com.example.tppromenades;


import com.example.tppromenades.CreateCourse;
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
public class MainActivity extends MapActivity  {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
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

		setContentView(R.layout.activity_main);
<<<<<<< HEAD
		System.out.println("dev44 hugo");
=======
		System.out.println("test");
	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		lat = location.getLatitude();
		lng = location.getLongitude();
		Toast.makeText(getBaseContext(),
				"Location change to : Latitude = " + lat + " Longitude = " + lng,
				Toast.LENGTH_SHORT).show();
		/*GeoPoint p = new GeoPoint((int) (lat * 1E6), (int) (lng * 1E6));
		mc.animateTo(p);
		mc.setCenter(p);*/
	}

}
