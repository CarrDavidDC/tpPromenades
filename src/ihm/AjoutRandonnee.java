package ihm;

import java.util.ArrayList;
import java.util.List;

import listener.CounterListener;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tppromenades.R;

public class AjoutRandonnee extends Activity implements OnClickListener{

	private double pas = 0.1;
	private Button btnPlus;
	private Button btnMoins;
	private Button btnEnregistrerAjoutRandonnee;
	private EditText distanceRandonnee;
	private EditText etNomRandonnee;
	private RatingBar difficulteRandonnee;
	private Spinner listeboxMinute;
	private Spinner listeboxHeure;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ajout_randonnee);		

		final ImageButton btnRetour = (ImageButton) findViewById(R.id.ibPrecedentAjoutRandonnee);
		btnRetour.setOnClickListener(this);
		btnPlus = (Button) findViewById(R.id.btnPlus);
		distanceRandonnee = (EditText) findViewById(R.id.distanceRandonnee);
		etNomRandonnee = (EditText) findViewById(R.id.etNomRandonnee);
		difficulteRandonnee = (RatingBar)findViewById(R.id.difficulteRandonnee);
		listeboxMinute= (Spinner)findViewById(R.id.listeboxMinute);
		listeboxHeure= (Spinner)findViewById(R.id.listboxHeure);
		CounterListener listenerIncrement = new CounterListener("+", distanceRandonnee,pas,this);
		CounterListener listenerDecrement = new CounterListener("-", distanceRandonnee,pas,this);
		CounterListener listenerValidation = new CounterListener("validation", distanceRandonnee,pas,this);
		
		btnPlus.setOnClickListener(listenerIncrement);
		btnPlus.setOnTouchListener(listenerIncrement);
		
		btnMoins = (Button) findViewById(R.id.btnMoins);
		btnMoins.setOnClickListener(listenerDecrement);
		btnMoins.setOnTouchListener(listenerDecrement);
	
		distanceRandonnee.setText("0");
		
		btnEnregistrerAjoutRandonnee = (Button) findViewById(R.id.btnEnregistrerAjoutRandonnee);
		btnEnregistrerAjoutRandonnee.setOnClickListener(listenerValidation);
		addItemsOnSpinnerHeures();
		addItemsOnSpinnerMinutes();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
        //Création d'un MenuInflater qui va permettre d'instancier un Menu XML en un objet Menu
        MenuInflater inflater = getMenuInflater();
        //Instanciation du menu XML spécifier en un objet Menu
        inflater.inflate(R.menu.menu_recherche, menu);
        return true;
	} 

	@Override
	public void onClick(View v) {
		Intent intent = null;
		
		switch (v.getId()) {
			case R.id.ibPrecedentAjoutRandonnee:
				intent = new Intent(AjoutRandonnee.this, Accueil.class);
				break;
			default:
				break;
		}
		startActivity(intent);
	}
 
	
	private void addItemsOnSpinnerHeures() {
		 
		Spinner listeboxMinute = (Spinner) findViewById(R.id.listeboxMinute);
		List<String> list = new ArrayList<String>();
		for(int i = 0; i < 60; i++)
		{
			list.add(String.valueOf(i));
		}
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, list);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		listeboxMinute.setAdapter(dataAdapter);
	  }
	
	private void addItemsOnSpinnerMinutes() {
		 
		Spinner listboxHeure = (Spinner) findViewById(R.id.listboxHeure);
		List<String> list = new ArrayList<String>();
		for(int i = 0; i < 13; i++)
		{
			list.add(String.valueOf(i));
		}
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, list);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		listboxHeure.setAdapter(dataAdapter);
	  }

	public EditText getDistanceRandonnee() {
		return distanceRandonnee;
	}


	public EditText getEtNomRandonnee() {
		return etNomRandonnee;
	}


	public RatingBar getDifficulteRandonnee() {
		return difficulteRandonnee;
	}


	public Spinner getListeboxMinute() {
		return listeboxMinute;
	}


	public Spinner getListeboxHeure() {
		return listeboxHeure;
	}
	
	
	
}
