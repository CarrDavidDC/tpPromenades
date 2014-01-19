package com.example.tppromenades;

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

public class MainActivity extends Activity {
	

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

}
