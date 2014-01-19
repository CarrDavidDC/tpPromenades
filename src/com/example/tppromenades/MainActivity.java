package com.example.tppromenades;

import com.google.android.maps.MapActivity;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;

public class MainActivity extends Activity  {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final Context context = this;
		
		final Button btnInscription = (Button) findViewById(R.id.btnInscription);
		btnInscription.setOnClickListener(new OnClickListener() {
				
		  public void onClick(View v) {
			Intent intent = new Intent(MainActivity.this, Inscription.class);
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
				Intent intent = new Intent(MainActivity.this, Accueil.class);
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
			Intent intent = new Intent(MainActivity.this, MdpOublie.class);
			startActivity(intent);
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
