package com.example.tppromenades;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

public class Inscription extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inscription);
		
		final Button btnInscriptionViaMail = (Button) findViewById(R.id.btnInscriptionMail);
		btnInscriptionViaMail.setOnClickListener(new OnClickListener() {
				
		  public void onClick(View v) {
			Intent intent = new Intent(Inscription.this, InscriptionViaMail.class);
			startActivity(intent);
			}
		});
		
		final ImageButton btnRetour = (ImageButton) findViewById(R.id.ibPrecedentInscription);
		btnRetour.setOnClickListener(new OnClickListener() {
				
		  public void onClick(View v) {
			Intent intent = new Intent(Inscription.this, MainActivity.class);
			startActivity(intent);
			}
		});
		 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.inscription, menu);
		return true;
	}

}
