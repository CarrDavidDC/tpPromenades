package ihm;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMyLocationChangeListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import ihm.Accueil;
import ihm.Inscription;
import ihm.MdpOublie;

import java.util.ArrayList;
import com.example.tppromenades.R;
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
import android.widget.Toast;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;

public class EnregistrerPromenade extends FragmentActivity  implements LocationListener,OnClickListener {
	private GoogleMap map;
	private String provider;
	//private MapView mapView;
	//private MapController mc;
	private LocationManager lm;
	private double latitude;
	private double longitude;
	private double altitude;
	private float accuracy;
	private boolean enCoursEnregistrement;
	private Button idEnregistrementPromenade;
	private Button idEnregistrer;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		enCoursEnregistrement = false;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_enregistrer_promenade);
		  // Getting reference to the SupportMapFragment of activity_main.xml
		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
                .getMap();
		idEnregistrementPromenade = (Button) findViewById(R.id.idEnregistrementPromenade);
		idEnregistrementPromenade.setOnClickListener(this);
		idEnregistrer = (Button) findViewById(R.id.idEnregistrer);
		idEnregistrer.setOnClickListener(this);
		idEnregistrer.setEnabled(false);
        // Enabling MyLocation Layer of Google Map
        map.setMyLocationEnabled(true);
       // map.setOnMyLocationChangeListener((OnMyLocationChangeListener) this);

        // Getting LocationManager object from System Service LOCATION_SERVICE
		lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        // Creating a criteria object to retrieve provider
        Criteria criteria = new Criteria();

        // Getting the name of the best provider
        provider = lm.getBestProvider(criteria, true);
	}
	
		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			// Inflate the menu; this adds items to the action bar if it is present.
			return true;
		}
      
		@Override
		protected void onResume() {
		    super.onResume();
		    lm.requestLocationUpdates(provider, 2000, 1, this);
		}

		@Override
		protected void onPause() {
		    super.onPause();
		    lm.removeUpdates(this);
		}
		
	  @Override
	  public void onLocationChanged(Location location) {
		  
			  	latitude = location.getLatitude();
			  	longitude = location.getLongitude();
			  	altitude = location.getAltitude();
			  	accuracy = location.getAccuracy();
			  	
			  	String msg = String.format(
			  			getResources().getString(R.string.new_location), latitude,
			  			longitude, altitude, accuracy);
			  	
			  	updatePosition();
		  
	  }
	  
	  private void updatePosition()
	  {
		  if(enCoursEnregistrement){
		  LatLng myPosition = new LatLng(latitude, longitude);
		  map.clear();
		  map.addMarker(new MarkerOptions().position(myPosition).title("Start"));
		  CameraPosition cameraPosition = new CameraPosition.Builder().target(myPosition).zoom(16.0f).build();
          CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
          map.moveCamera(cameraUpdate); 
		  }
	  }

	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClick(View v) {

		Toast.makeText(getBaseContext(),"On click",Toast.LENGTH_SHORT).show();
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.idEnregistrementPromenade:
				if(enCoursEnregistrement)
				{
					enCoursEnregistrement = false;
					idEnregistrer.setEnabled(true);
					idEnregistrementPromenade.setEnabled(false);
				}else{
					idEnregistrementPromenade.setText("Stop");
					enCoursEnregistrement = true;
					updatePosition();
				}
			break;
		default:
			break;
	}
	}
}
