package com.example.tppromenades;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

public class MdpOublie extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mdp_oublie);
		final Context context = this;
		
		final ImageButton btnRetour = (ImageButton) findViewById(R.id.ibPrecedentMdpOublie);
		btnRetour.setOnClickListener(new OnClickListener() {
				
		  public void onClick(View v) {
				Intent intent = new Intent(MdpOublie.this, MainActivity.class);
				startActivity(intent);
			}
		});
		
		final Button btnEnvoi = (Button) findViewById(R.id.btnEnvoiMdpOublie);
		btnEnvoi.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) 
			{
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
				alertDialogBuilder.setTitle("Mot de passe envoyé !");
				alertDialogBuilder.setMessage("Votre mot de passe a été envoyé à l'adresse indiquée");
				alertDialogBuilder.setCancelable(false);
				alertDialogBuilder.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						dialog.cancel();
						Intent intent = new Intent(MdpOublie.this, MainActivity.class);
						startActivity(intent);
					}
				});
				AlertDialog alertDialog = alertDialogBuilder.create();
				alertDialog.show();
				
				
			}
			
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mdp_oublie, menu);
		return true;
	}

}
