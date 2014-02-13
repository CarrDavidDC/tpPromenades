package listener;

import ihm.Accueil;
import ihm.AjoutRandonnee;
import ihm.DetailsRandonneeApercuMap;
import ihm.EnregistrerPromenade;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.android.gms.maps.model.LatLng;

import bdd.DatabaseHandler;
import bdd.TableHistorique;
import bdd.TablePromenade;

import metier.Historique;
import metier.Promenade;

import Parser.Parser;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.location.Location;
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
	private EnregistrerPromenade enregistrerPromenade;
	private DetailsRandonneeApercuMap detailsRandonneeApercuMap;
	private Context context;
	private Promenade maPromenade;
	private ArrayList<LatLng> listLatLng;
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
	
	public CounterListener(String signe, Promenade p, EnregistrerPromenade e, Context c)
	{
		super();
		this.action = signe;
		this.enregistrerPromenade= e;
		this.context  = c;
		this.maPromenade = p;
	}
	public CounterListener(String signe, Promenade p, DetailsRandonneeApercuMap e, Context c)
	{
		super();
		this.action = signe;
		this.detailsRandonneeApercuMap= e;
		this.context  = c;
		this.maPromenade = p;
	}
	public CounterListener(String signe, Promenade p, DetailsRandonneeApercuMap e, Context c,ArrayList<LatLng> list)
	{
		super();
		this.action = signe;
		this.detailsRandonneeApercuMap= e;
		this.context  = c;
		this.maPromenade = p;
		this.listLatLng = list;
	}
	
	@Override
	public void onClick(View v) {
		if(action.equals("validation"))
		{
			traitementAjoutRandonnee();
		}else{
			if(action.equals("enregistrerPromenade"))
			{
				traitementEnregistrerRandonnee();
			}else{
				if(action.equals("departPromenade"))
				{
					traitementDepartRandonnee();
				}else{
					if(action.equals("enregistrerPromenadeHistorique"))
					{
						traitementEnregistrerPromenadeHistorique();
					}else{
						if(action.equals("departPromenadeHistorique"))
						{
							traitementDepartPromenadeHistorique();
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
				}
			}
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
	
	private void traitementEnregistrerPromenadeHistorique()
	{
		ArrayList<LatLng> listOfLatLng = 	detailsRandonneeApercuMap.getArrayListPosition();
		double distanceTotal = 0;
		for(int i = 0; i < listOfLatLng.size()-1;i++)
		{
			LatLng currentPoint = listOfLatLng.get(i);
			LatLng nextPoint = listOfLatLng.get(i+1);
			distanceTotal+= getDistance(currentPoint,nextPoint);
		}
		Historique h = new Historique();
		h.set_length(distanceTotal);
		
		h.set_altitude(0);
		h.set_durationHour(maPromenade.get_durationHour());
		h.set_durationMinute(maPromenade.get_durationMinute());
		h.set_idPromenade(maPromenade.get_gid());
		h.set_way(Parser.arrayListLatLngToString(listOfLatLng));

		TableHistorique tableHist = new TableHistorique(new DatabaseHandler(context));
		tableHist.ajouter(h);
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
		alertDialogBuilder.setTitle("Ajout !");
		 alertDialogBuilder.setMessage("Votre randonnée a été historisée.");
		  alertDialogBuilder.setCancelable(false);
		  alertDialogBuilder.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog,int id) {
					dialog.cancel();
					Intent intent = new Intent(detailsRandonneeApercuMap, Accueil.class);	
					detailsRandonneeApercuMap.startActivity(intent);
				}
			});
			AlertDialog alertDialog = alertDialogBuilder.create();
			alertDialog.show();
	}
		
	public boolean calculDistancePositionParcours()
	{
		LatLng myPosition = detailsRandonneeApercuMap.getLatLngMyPosition();
		double distanceMaxEcart = detailsRandonneeApercuMap.getDistanceMaxAvecParcours();
		double ecartMin = 1000000;
		for(int i = 0; i < listLatLng.size();i++)
		{
			// on récupère la distance min
			double distance = getDistance(myPosition,listLatLng.get(i));
			if(getDistance(myPosition,listLatLng.get(i)) < ecartMin)
			{
				ecartMin = distance;
			}
		}
		Toast.makeText(context,"La distance est de : "+ecartMin,5000).show();
		if(ecartMin < distanceMaxEcart)
			return true;
		else{
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
			alertDialogBuilder.setTitle("Bientôt le départ !");
			 alertDialogBuilder.setMessage("Vous êtes trop éloigné(e) du parcours, merci de vous repositionner.");
			  alertDialogBuilder.setCancelable(false);
			  alertDialogBuilder.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						dialog.cancel();
						
					}
				});
				AlertDialog alertDialog = alertDialogBuilder.create();
				alertDialog.show();
			
				return false;
		}
	}
	
	private void traitementDepartPromenadeHistorique(){
		
		if(detailsRandonneeApercuMap.getEnCoursEnregistrement())
		{
			detailsRandonneeApercuMap.setEnCoursEnregistrement(false);
			detailsRandonneeApercuMap.getButtonIdEnregistrer().setEnabled(true);
			detailsRandonneeApercuMap.getButtonIdEnregistrement().setEnabled(false);
		}else{
			if(detailsRandonneeApercuMap.getLatitude() != 0 && detailsRandonneeApercuMap.getLongitude() != 0)
			{				
				if(calculDistancePositionParcours())
				{
					AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
					alertDialogBuilder.setTitle("Bientôt le départ !");
					 alertDialogBuilder.setMessage("Etes vous prêt(e) pour commencer ?");
					  alertDialogBuilder.setCancelable(true);
					  alertDialogBuilder.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,int id) {
								dialog.cancel();
								Toast.makeText(context,"A vos marques...",5000).show();
								Toast.makeText(context,"Prêts...",5000).show();
								Toast.makeText(context,"Partez...",5000).show();
								detailsRandonneeApercuMap.getButtonIdEnregistrement().setText("Stop");
								detailsRandonneeApercuMap.setEnCoursEnregistrement(true);
								detailsRandonneeApercuMap.updatePosition();
							}
						});
					  alertDialogBuilder.setNegativeButton("Non", new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,int id) {
								dialog.cancel();
							}
						});
						AlertDialog alertDialog = alertDialogBuilder.create();
						alertDialog.show();
				}
			}else{
				Toast.makeText(context,"Géolocalisation en cours...",Toast.LENGTH_SHORT).show();
			}
			
		}
	}
	
	private void traitementDepartRandonnee()
	{
		if(enregistrerPromenade.getEnCoursEnregistrement())
		{
			enregistrerPromenade.setEnCoursEnregistrement(false);
			enregistrerPromenade.getButtonIdEnregistrer().setEnabled(true);
			enregistrerPromenade.getButtonIdEnregistrement().setEnabled(false);
		}else{
			if(enregistrerPromenade.getLatitude() != 0 && enregistrerPromenade.getLongitude() != 0)
			{
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
				alertDialogBuilder.setTitle("Bientôt le départ !");
				 alertDialogBuilder.setMessage("Etes vous prêt(e) pour commencer ?");
				  alertDialogBuilder.setCancelable(true);
				  alertDialogBuilder.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,int id) {
							dialog.cancel();
							Toast.makeText(context,"A vos marques...",5000).show();
							Toast.makeText(context,"Prêts...",5000).show();
							Toast.makeText(context,"Partez...",5000).show();
							enregistrerPromenade.getButtonIdEnregistrement().setText("Stop");
							enregistrerPromenade.setEnCoursEnregistrement(true);
							enregistrerPromenade.updatePosition();
						}
					});
				  alertDialogBuilder.setNegativeButton("Non", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,int id) {
							dialog.cancel();
						}
					});
					AlertDialog alertDialog = alertDialogBuilder.create();
					alertDialog.show();
			}else{
				Toast.makeText(context,"Géolocalisation en cours...",Toast.LENGTH_SHORT).show();
			}
			
		}
	}
	
	private void traitementEnregistrerRandonnee()
	{
		ArrayList<LatLng> listOfLatLng = 	enregistrerPromenade.getArrayListPosition();
		double distanceTotal = 0;
		for(int i = 0; i < listOfLatLng.size()-1;i++)
		{
			LatLng currentPoint = listOfLatLng.get(i);
			LatLng nextPoint = listOfLatLng.get(i+1);
			distanceTotal+= getDistance(currentPoint,nextPoint);
		}
		Toast.makeText(context,"La distance totale est..."+distanceTotal,Toast.LENGTH_SHORT).show();
		Toast.makeText(context,"Array list : "+enregistrerPromenade.getArrayListPosition().toString(),Toast.LENGTH_SHORT).show();
	
		maPromenade.set_length(distanceTotal);
		maPromenade.set_way(Parser.arrayListLatLngToString(enregistrerPromenade.getArrayListPosition()));
		TablePromenade tableProme = new TablePromenade(new DatabaseHandler(context));
		tableProme.ajouter(maPromenade);
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
		alertDialogBuilder.setTitle("Ajout !");
		 alertDialogBuilder.setMessage("Votre randonnée a été correctement ajoutée.");
		  alertDialogBuilder.setCancelable(false);
		  alertDialogBuilder.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog,int id) {
					dialog.cancel();
					Intent intent = new Intent(enregistrerPromenade, Accueil.class);	
					enregistrerPromenade.startActivity(intent);
				}
			});
			AlertDialog alertDialog = alertDialogBuilder.create();
			alertDialog.show();
			
			
		
	}
	
	private double getDistance(LatLng LatLng1, LatLng LatLng2) {
	    double distance = 0;
	    Location locationA = new Location("A");
	    locationA.setLatitude(LatLng1.latitude);
	    locationA.setLongitude(LatLng1.longitude);
	    Location locationB = new Location("B");
	    locationB.setLatitude(LatLng2.latitude);
	    locationB.setLongitude(LatLng2.longitude);
	    distance = locationA.distanceTo(locationB);
	    return distance;
	}
	
	
	private void traitementAjoutRandonnee(){
		String nomRandonnee = ajoutRandonnee.getEtNomRandonnee().getText().toString();
		float difficulte = ajoutRandonnee.getDifficulteRandonnee().getRating();
		String description = ajoutRandonnee.getEtDescription().getText().toString();
		
		if(nomRandonnee.equals("") || difficulte == 0 || description.equals(""))
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
			
			if(ajoutRandonnee.getBitmap() != null)
			{
				byte[] data = getBitmapAsByteArray(ajoutRandonnee.getBitmap());
				this.maPromenade = new Promenade(nomRandonnee, description,difficulte,data);
			}
			else
			{
				this.maPromenade = new Promenade(nomRandonnee, description, difficulte);
			}
			Intent intent = new Intent(ajoutRandonnee, EnregistrerPromenade.class);	
			intent.putExtra("Promenade", this.maPromenade);
			ajoutRandonnee.startActivity(intent);
		}
	}
	private byte[] getBitmapAsByteArray(Bitmap bitmap) {
	    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	    bitmap.compress(CompressFormat.PNG, 0, outputStream);       
	    return outputStream.toByteArray();
	}
}
