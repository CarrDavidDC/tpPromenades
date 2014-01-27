package ihm;

import java.util.Timer;
import java.util.TimerTask;

import listener.CounterClickListener;

import com.example.tppromenades.R;
import com.example.tppromenades.R.id;
import com.example.tppromenades.R.layout;
import com.example.tppromenades.R.menu;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class AjoutRandonnee extends Activity implements OnClickListener{

	private double pas = 0.1;
	private Button btnPlus;
	private Button btnMoins;
	private EditText distanceRandonnee;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ajout_randonnee);		

		final ImageButton btnRetour = (ImageButton) findViewById(R.id.ibPrecedentAjoutRandonnee);
		btnRetour.setOnClickListener(this);
		btnPlus = (Button) findViewById(R.id.btnPlus);
		distanceRandonnee = (EditText) findViewById(R.id.distanceRandonnee);
		
		CounterClickListener listenerIncrement = new CounterClickListener("+", distanceRandonnee,pas);
		CounterClickListener listenerDecrement = new CounterClickListener("-", distanceRandonnee,pas);
		
		btnPlus.setOnClickListener(listenerIncrement);
		btnPlus.setOnTouchListener(listenerIncrement);
		
		btnMoins = (Button) findViewById(R.id.btnMoins);
		btnMoins.setOnClickListener(listenerDecrement);
		btnMoins.setOnTouchListener(listenerDecrement);
	
		distanceRandonnee.setText("0");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ajout_randonnee, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		Intent intent = null;
		
		switch (v.getId()) {
			case R.id.ibPrecedentAjoutRandonnee:
				intent = new Intent(AjoutRandonnee.this, Accueil.class);
				break;
			case R.id.btnPlus:
				 int a=Integer.parseInt(distanceRandonnee.getText().toString());

	                int b=a-1;

	                distanceRandonnee.setText(new Integer(b).toString());
				break;
			case R.id.btnMoins:
				changerDistance("-");				
				break;
			default:
				break;
		}
		startActivity(intent);
	}
 
	
	private void changerDistance(String signe)
	{
		Toast.makeText(getApplicationContext(), "Signe : " + signe, Toast.LENGTH_LONG).show();
		String distRando = "";
		Double laDistance = 0.0;
		distRando = distanceRandonnee.getText().toString();
		Toast.makeText(getApplicationContext(), "distRando : " + distRando, Toast.LENGTH_LONG).show();
		
		laDistance = Double.valueOf(distRando);
		Toast.makeText(getApplicationContext(), "laDistance : " + laDistance, Toast.LENGTH_LONG).show();
		Toast.makeText(getApplicationContext(), "pas : " + pas, Toast.LENGTH_LONG).show();
		
		if(signe == "+"){
			laDistance += pas;
		}else if(signe == "-"){
			laDistance -= pas;
		}
		Toast.makeText(getApplicationContext(), "laDistanceF : " + laDistance, Toast.LENGTH_LONG).show();
		
		distanceRandonnee.setText(String.valueOf(laDistance));
	}
}
