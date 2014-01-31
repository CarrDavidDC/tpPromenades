package listener;

import ihm.Accueil;
import ihm.AjoutRandonnee;
import ihm.Connexion;

import java.util.HashMap;

import bdd.DatabaseHandler;
import bdd.TablePromenade;

import metier.Promenade;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;
public class CounterListener implements OnClickListener,OnTouchListener{

	private String action;
	private EditText distanceRandonnee;
	private double pas;
	private Handler mHandler;
	private AjoutRandonnee ajoutRandonnee;
	private Context context;
	
    int compteur = 0;
    double pasTemp = pas;
	Runnable mAddition = new Runnable() {  	            
        @Override public void run() {		        	
        	double a=Double.parseDouble(distanceRandonnee.getText().toString());
        	double b = 0;
        	if(action.equals("+"))
    		{
    			b=a+pasTemp;
    		}else if(action.equals("-"))
    		{
    			b=a-pasTemp;
    			if(b < 0)
    			{
    				b = 0;
    			}
    		}
            double roundOff = (double) Math.round(b * 10) / 10;
            distanceRandonnee.setText(new Double(roundOff).toString());
            mHandler.postDelayed(this, 500);	
            compteur++;
            if((compteur%2)==0)
            {
            	pasTemp+=0.5;
            }
        }
    };
    
	public CounterListener(String signe, EditText distanceRandonnee,
			double pas,AjoutRandonnee ajoutRando,Context c) {
		super();
		this.action = signe;
		this.distanceRandonnee = distanceRandonnee;
		this.pas = pas;
		this.ajoutRandonnee = ajoutRando;
		this.context  = c;
	}
	
	@Override
	public void onClick(View v) {
		if(action.equals("validation"))
		{
			traitementAjoutRandonnee();
		}else{
			// TODO Auto-generated method stub
			double a=Double.parseDouble(distanceRandonnee.getText().toString());
			double b = 0;
			if(action.equals("+"))
			{
				b=a+pas;
			}else if(action.equals("-"))
			{
				b=a-pas;
				if(b < 0)
				{
					b = 0;
				}
			}
	        double roundOff = (double) Math.round(b * 10) / 10;
	        distanceRandonnee.setText(new Double(roundOff).toString());
		}
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch(event.getAction()) {
        case MotionEvent.ACTION_DOWN:
            mHandler = new Handler();
            mHandler.postDelayed(mAddition, 500);
            break;
        case MotionEvent.ACTION_UP:
            mHandler.removeCallbacks(mAddition);
            compteur = 0;
            pasTemp = pas;
            return false;
        }
        return false;
	}
	

	private void traitementAjoutRandonnee(){
		String nomRandonnee = ajoutRandonnee.getEtNomRandonnee().getText().toString();
		float difficulte = ajoutRandonnee.getDifficulteRandonnee().getRating();
		double distance = Double.valueOf(ajoutRandonnee.getDistanceRandonnee().getText().toString());
		int heure = Integer.valueOf(ajoutRandonnee.getListeboxHeure().getSelectedItem().toString());
		int minute = Integer.valueOf(ajoutRandonnee.getListeboxMinute().getSelectedItem().toString());
		String description = ajoutRandonnee.getEtDescription().getText().toString();
		if(nomRandonnee.equals("") || distance == 0 || heure == 0 || minute == 0 || difficulte == 0 || description.equals(""))
		{
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
			 alertDialogBuilder.setTitle("Erreur !");
			  alertDialogBuilder.setMessage("Vous devez renseigner l'ensemble des champs du formulaire");
			  alertDialogBuilder.setCancelable(false);
			  alertDialogBuilder.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						dialog.cancel();
					}
				});
				AlertDialog alertDialog = alertDialogBuilder.create();
				alertDialog.show();
		}else{
			Promenade maPromenade = new Promenade(nomRandonnee, description, distance,heure+"h"+minute,difficulte);
			TablePromenade tableProme = new TablePromenade(new DatabaseHandler(context));
			tableProme.ajouter(maPromenade);
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
			alertDialogBuilder.setTitle("Ajout !");
			 alertDialogBuilder.setMessage("Votre randonnée a été correctement ajoutée.");
			  alertDialogBuilder.setCancelable(false);
			  alertDialogBuilder.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						dialog.cancel();
					}
				});
				AlertDialog alertDialog = alertDialogBuilder.create();
				alertDialog.show();
			Intent intent = new Intent(ajoutRandonnee, Accueil.class);	
			ajoutRandonnee.startActivity(intent);
		}
	}
}
