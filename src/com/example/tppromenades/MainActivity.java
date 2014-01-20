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
 	private MapController				mc								= null;
    /* private static final LatLng MELBOURNE = new LatLng(-37.813, 144.962);
     private Marker melbourne = map.addMarker(new MarkerOptions()
                               .position(MELBOURNE)
                               .title("Melbourne")
                               .snippet("Population: 4,137,400")
                               .icon(BitmapDescriptorFactory.fromResource(R.drawable.arrow)));*/

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    //    map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
     //   map.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        // Do a null check to confirm that we have not already instantiated the map.
        
        if (map == null) {
        	map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
                                .getMap();
            // Check if we were successful in obtaining the map.
            if (map != null) {
                // The Map is verified. It is now safe to manipulate the map.
            	Toast toast = Toast.makeText(getApplicationContext(), "LA MAP N EST PAS NULL", Toast.LENGTH_SHORT);
                toast.show();
                map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                map.addMarker(new MarkerOptions()
                .position(new LatLng(0, 0))
                .title("Hello world"));
                
                LatLng MELBOURNE = new LatLng(-37.813, 144.962);
                Marker melbourne = map.addMarker(new MarkerOptions()
                                          .position(MELBOURNE)
                                          .title("Melbourne")
                                          .snippet("Population: 4,137,400"));
                
                PolygonOptions rectOptions = new PolygonOptions()
                .add(new LatLng(37.35, -122.0),
                     new LatLng(37.45, -122.0),
                     new LatLng(37.45, -122.2),
                     new LatLng(37.35, -122.2),
                     new LatLng(37.35, -122.0));

                // Get back the mutable Polygon
                Polygon polygon = map.addPolygon(rectOptions);
                LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                map.setMyLocationEnabled(true);
                map.animateCamera(CameraUpdateFactory.zoomIn());
                LatLngBounds.Builder builder = new LatLngBounds.Builder();
                toast = Toast.makeText(getApplicationContext(), "Builder OK", Toast.LENGTH_SHORT);
                toast.show();
                
                builder.include(melbourne.getPosition());
                LatLngBounds bounds = builder.build();
                toast = Toast.makeText(getApplicationContext(), "Include OK", Toast.LENGTH_SHORT);
                toast.show();
                int padding = 0; // offset from edges of the map in pixels
                CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
                toast = Toast.makeText(getApplicationContext(), "Camera OK", Toast.LENGTH_SHORT);
                toast.show();
                //map.moveCamera(cu);
                toast = Toast.makeText(getApplicationContext(), "Move OK", Toast.LENGTH_SHORT);
                toast.show();
               // map.animateCamera(cu);
                
                CameraPosition cameraPosition = new CameraPosition.Builder().target(MELBOURNE).zoom(14.0f).build();
                CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
                map.moveCamera(cameraUpdate);   
            }else{
            	Toast toast = Toast.makeText(getApplicationContext(), "LA MAP EST NULL", Toast.LENGTH_SHORT);
                toast.show();
                
            }
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
		/*GeoPoint p = new GeoPoint((int) (lat * 1E6), (int) (lng * 1E6));
		mc.animateTo(p);
		mc.setCenter(p);*/
	}

}
