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
import android.widget.EditText;
import android.widget.ImageButton;

public class InscriptionViaMail extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inscription_via_mail);
		final Context context = this;
		
		final ImageButton btnRetour = (ImageButton) findViewById(R.id.ibPrecedentInscriptionViaMail);
		btnRetour.setOnClickListener(new OnClickListener() {
				
		  public void onClick(View v) {
			Intent intent = new Intent(InscriptionViaMail.this, Inscription.class);
			startActivity(intent);
			}
		});
		 
		final Button btnEnregistrer = (Button) findViewById(R.id.btnEnregistrer);
		btnEnregistrer.setOnClickListener(new OnClickListener() {
				
		  public void onClick(View v) {
			  EditText etPassword = (EditText)findViewById(R.id.etPasswordInscriptionViaMail);
			  EditText etPasswordConfirm = (EditText)findViewById(R.id.etPasswordConfirmInscriptionViaMail);
			  String pass = etPassword.getText().toString();
			  String passconfirm = etPasswordConfirm.getText().toString();
			  if(!pass.equals(passconfirm))
			  {
				  AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
				  alertDialogBuilder.setTitle("Erreur !");
				  alertDialogBuilder.setMessage("Les deux mots de passe doivent être identiques");
				  alertDialogBuilder.setCancelable(false);
				  alertDialogBuilder.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,int id) {
							dialog.cancel();
						}
					});
					AlertDialog alertDialog = alertDialogBuilder.create();
					alertDialog.show();
			  }
			  else
			  {
				  AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
				  alertDialogBuilder.setTitle("Félicitation !");
				  alertDialogBuilder.setMessage("Vous pouvez désormais vous connecter");
				  alertDialogBuilder.setCancelable(false);
				  alertDialogBuilder.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,int id) {
							dialog.cancel();
						}
					});
					AlertDialog alertDialog = alertDialogBuilder.create();
					alertDialog.show();
						
					Intent intent = new Intent(InscriptionViaMail.this, MainActivity.class);
					startActivity(intent);
			  }
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.inscription_via_mail, menu);
		return true;
	}

}
