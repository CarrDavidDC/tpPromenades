package com.example.tppromenades;


import java.util.concurrent.ExecutionException;

import ihm.Accueil;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask.Status;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.widget.Toast;
import bdd.DatabaseHandler;
import bdd.DownloadData;
import bdd.TablePromenade;

public class MainActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
   
		ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

		if (mWifi.isConnected()) {
			DatabaseHandler db = new DatabaseHandler(this);
			DownloadData d = (DownloadData) new DownloadData(this, db).execute();
			
			try {
				while(d.get()!=null) {
					
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("DOWNLOAD TERMINE");
			d.get_tp().selectionnerTout(this.getApplicationContext());
			//DownloadData d = (DownloadData) new DownloadData(this, db, "https://download.data.grandlyon.com/ws/grandlyon/evg_esp_veg.evgsentiernature/all.json").execute();
			//new TablePromenade(db).selectionnerTout(this.getApplicationContext());
			//DownloadData d = (DownloadData) new DownloadData(this, db, "https://download.data.grandlyon.com/ws/grandlyon/evg_esp_veg.evgsentiernature/the_geom.json").execute();
			//d.set_url("https://download.data.grandlyon.com/ws/grandlyon/evg_esp_veg.evgsentiernature/the_geom.json");
			//d.execute();
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
	    /*if(evt.getAction() == MotionEvent.ACTION_DOWN)
	    {
	    	Intent intent = new Intent(MainActivity.this, Accueil.class);
			startActivity(intent);
	    }*/
	    return true;
	    
	}  
}
