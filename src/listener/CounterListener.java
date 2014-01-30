package listener;

import ihm.AjoutRandonnee;

import java.util.HashMap;

import metier.Promenade;

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
			double pas,AjoutRandonnee ajoutRando) {
		super();
		this.action = signe;
		this.distanceRandonnee = distanceRandonnee;
		this.pas = pas;
		this.ajoutRandonnee = ajoutRando;
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
		Promenade maPromenade = new Promenade(nomRandonnee, distance,heure+"H"+minute,difficulte);
		maPromenade.ajoutRandonneeBd();
	}
}
