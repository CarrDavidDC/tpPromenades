package com.example.tppromenades;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class EnregistrerCheminRandonnee extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_enregistrer_chemin_randonnee);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.enregistrer_chemin_randonnee, menu);
		return true;
	}
 
}
