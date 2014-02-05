package ihm;


import com.example.tppromenades.R;
import com.example.tppromenades.R.id;
import com.example.tppromenades.R.layout;
import com.example.tppromenades.R.menu;
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


import java.util.ArrayList;

import reglage.ReglageSingleton;

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

public class Connexion extends Activity implements LocationListener{

	 static final LatLng MUMBAI = new LatLng(19.0144100, 72.8479400);
 	private double							lat								= 0;
 	private double							lng								= 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        ReglageSingleton.getInstance().setRequetePourTriRandonnee(null);
        
        final Context context = this;
		
		final Button btnInscription = (Button) findViewById(R.id.btnInscription);
		btnInscription.setOnClickListener(new OnClickListener() {
				
		  public void onClick(View v) {
			Intent intent = new Intent(Connexion.this, Inscription.class);
			startActivity(intent);
			}
		}); 
		
		final Button btnConnexion = (Button) findViewById(R.id.btnConnexion);
		btnConnexion.setOnClickListener(new OnClickListener() {
				
		  public void onClick(View v) {
			  EditText etLogin = (EditText)findViewById(R.id.etLoginMainActivity);
			  EditText etPassword = (EditText)findViewById(R.id.etPasswordMainActivity);
			  String login = etLogin.getText().toString();
			  String pass = etPassword.getText().toString();
			  if(login.equals(pass))
			  {
				Intent intent = new Intent(Connexion.this, Accueil.class);
				startActivity(intent);
			  }
			  else
			  {
				  AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
				  alertDialogBuilder.setTitle("Erreur !");
				  alertDialogBuilder.setMessage("(Pour l'instant) Le mot de passe doit être identique au login");
				  alertDialogBuilder.setCancelable(false);
				  alertDialogBuilder.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,int id) {
							dialog.cancel();
						}
					});
					AlertDialog alertDialog = alertDialogBuilder.create();
					alertDialog.show();
			  }
			}
		});
		
		final Button btnMdpOublie = (Button) findViewById(R.id.btnMdpOublie);
		btnMdpOublie.setOnClickListener(new OnClickListener() {
				
		  public void onClick(View v) {
			Intent intent = new Intent(Connexion.this, MdpOublie.class);
			startActivity(intent);
			}
		});

		
	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		lat = location.getLatitude();
		lng = location.getLongitude();
		Toast.makeText(getBaseContext(),
				"Location change to : Latitude = " + lat + " Longitude = " + lng,
				Toast.LENGTH_SHORT).show();
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
