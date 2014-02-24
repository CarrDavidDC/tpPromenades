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
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import Parser.Parser;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import ihm.Accueil;

import java.util.ArrayList;
import java.util.StringTokenizer;

import com.example.tppromenades.R;
import bdd.DatabaseHandler;
import bdd.DownloadData;
import bdd.TablePromenade;
import listener.CounterListener;
import map.CreateCourse;
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
import android.graphics.Color;
import android.view.Menu;
import android.widget.Toast;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;

public class DetailsRandonneeApercuMap extends FragmentActivity  implements LocationListener,OnClickListener {
	private GoogleMap map;
	private String provider;
	private LocationManager lm;
	private double latitude;
	private double longitude;
	private double altitude;
	private float accuracy;
	private boolean enCoursEnregistrement;
	private boolean distanceAvecParcours;
	private Button idEnregistrementPromenade;
	private Button idEnregistrer;
	private Promenade maPromenade;
	private ArrayList<LatLng> arrayListPosition;
	private boolean firstLocationChange;
	private PolylineOptions polyLine;
	private ArrayList<Double> listOfAltitude;
	private static final String _MULTILINESTRING = "MULTILINESTRING";
	private static final String _LEFTPARENTHESIS = "(";
	private static final String _RIGHTPARENTHESIS = ")";
	private CounterListener departPromenade;
	private static final String _COMMA = ",";
	private static final String _SPACE = " ";
	
	private double distanceMaxAvecParcours = 300;
	
	public double getDistanceMaxAvecParcours(){
		return this.distanceMaxAvecParcours;
	}
	
	public LatLng getLatLngMyPosition()
	{
		return new LatLng(latitude,longitude);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		enCoursEnregistrement = false;
		firstLocationChange = true;
		distanceAvecParcours = false;
		listOfAltitude = new ArrayList<Double>();
		this.polyLine = new PolylineOptions();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_enregistrer_promenade);
		  // Getting reference to the SupportMapFragment of activity_main.xml
		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
                .getMap();
		Intent i = getIntent();
		maPromenade = (Promenade)i.getSerializableExtra("Promenade");	
		idEnregistrementPromenade = (Button) findViewById(R.id.idEnregistrementPromenade);
		idEnregistrer = (Button) findViewById(R.id.idEnregistrer);
        arrayListPosition = new ArrayList<LatLng>();

       // map.setOnMyLocationChangeListener((OnMyLocationChangeListener) this);

        // Getting LocationManager object from System Service LOCATION_SERVICE
		lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        // Creating a criteria object to retrieve provider
        Criteria criteria = new Criteria();

        // Getting the name of the best provider
        provider = lm.getBestProvider(criteria, true);

        PolylineOptions  polyLine = new PolylineOptions();
        ArrayList<LatLng> listLatLng  = new ArrayList<LatLng>();
        listLatLng = Parser.stringToArrayListLatLng(maPromenade.get_way());
		CounterListener listenerValidation = new CounterListener("enregistrerPromenadeHistorique",maPromenade, this,this);
		departPromenade = new CounterListener("departPromenadeHistorique",maPromenade, this,this,listLatLng);
		idEnregistrementPromenade.setOnClickListener(departPromenade);
		idEnregistrer.setOnClickListener(listenerValidation);
		idEnregistrer.setEnabled(false);
        // Enabling MyLocation Layer of Google Map
        map.setMyLocationEnabled(true);
        for(int z = 0; z < listLatLng.size();z++)
        {
        	if(z == 0)
        	{
        		map.addMarker(new MarkerOptions().position(listLatLng.get(z)).title("Début"));
        		CameraPosition cameraPosition = new CameraPosition.Builder().target(listLatLng.get(z)).zoom(14.0f).build();
  			  	CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
  			  	map.moveCamera(cameraUpdate); 
        	}
        	if(z == (listLatLng.size()-1))
        	{
        		map.addMarker(new MarkerOptions().position(listLatLng.get(z)).title("Fin"));
        	}
        	polyLine.add(listLatLng.get(z));
        }
        Polyline line = map.addPolyline(polyLine);
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
	  
	  public void updatePosition()
	  {
		  LatLng myPosition = null;
		  if(firstLocationChange)
		  {
			  myPosition = new LatLng(latitude, longitude);
			  CameraPosition cameraPosition = new CameraPosition.Builder().target(myPosition).zoom(16.0f).build();
			  CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
			  map.moveCamera(cameraUpdate); 
			  arrayListPosition.add(myPosition);
			  firstLocationChange = false;
		  }else if(enCoursEnregistrement){
			  myPosition = new LatLng(latitude, longitude);
			  CameraPosition cameraPosition = new CameraPosition.Builder().target(myPosition).zoom(16.0f).build();
			  CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
			  map.moveCamera(cameraUpdate); 

			  if(departPromenade.calculDistancePositionParcours())
			  {
				  arrayListPosition.add(myPosition);
				  listOfAltitude.add(altitude);
				  polyLine.add(myPosition);
				  if(arrayListPosition.size() > 1)
				  {				  
					  Polyline line = map.addPolyline(polyLine.color(Color.RED));
				  }
			  }
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
		// TODO Auto-generated method stub
		switch (v.getId()) {
		default:
			break;
		}
	}
	
	public double getLatitude()
	{
		return this.latitude;
	}
	
	public double getLongitude()
	{
		return this.longitude;
	}
	
	public Button getButtonIdEnregistrer()
	{
		return this.idEnregistrer;
	}
	
	public Button getButtonIdEnregistrement()
	{
		return this.idEnregistrementPromenade;
	}
	
	public boolean getEnCoursEnregistrement()
	{
		return this.enCoursEnregistrement;
	}
	
	public void setEnCoursEnregistrement(boolean b)
	{
		this.enCoursEnregistrement = b;
	}
	
	public ArrayList<LatLng> getArrayListPosition()
	{
		return this.arrayListPosition;
	}
	
	public Promenade getMaPromenade(){
		return this.maPromenade;
	}
}
