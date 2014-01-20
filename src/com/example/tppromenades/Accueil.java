package com.example.tppromenades;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class Accueil extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_accueil);
		
		final ImageButton btnAjout = (ImageButton) findViewById(R.id.ibAddRandonneeAccueil);
		btnAjout.setOnClickListener(new OnClickListener() {
				
		  public void onClick(View v) {
			Intent intent = new Intent(Accueil.this, AjoutRandonnee.class);
			startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.accueil, menu);
		return true;
	}

}
