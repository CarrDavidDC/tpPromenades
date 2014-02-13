package ihm;

import java.util.ArrayList;
import java.util.List;

import reglage.ReglageSingleton;

import metier.Promenade;
import metier.PromenadeAdapter;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.ShareActionProvider;
import android.widget.Spinner;
import android.widget.Toast;
import bdd.DatabaseHandler;
import bdd.DownloadData;
import bdd.TablePromenade;

import com.example.tppromenades.R;

public class Reglage extends Activity implements OnClickListener {
	private RadioButton nomAZ;
	private RadioButton nomZA;
	private RadioButton distanceDecroissante;
	private RadioButton distanceCroissante;
	private RadioButton difficulteDecroissante;
	private RadioButton difficulteCroissante;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reglage);
		nomAZ = (RadioButton) findViewById(R.id.nomAZ);
		nomZA = (RadioButton) findViewById(R.id.nomZA);
		distanceDecroissante = (RadioButton) findViewById(R.id.distanceDecroissante);
		distanceCroissante = (RadioButton) findViewById(R.id.distanceCroissante);
		difficulteDecroissante = (RadioButton) findViewById(R.id.difficulteDecroissante);
		difficulteCroissante = (RadioButton) findViewById(R.id.difficulteCroissante);
		nomAZ.setOnClickListener(this);
		nomZA.setOnClickListener(this);
		distanceDecroissante.setOnClickListener(this);
		distanceCroissante.setOnClickListener(this);
		difficulteDecroissante.setOnClickListener(this);
		difficulteCroissante.setOnClickListener(this);
		final ImageButton btnRetour = (ImageButton) findViewById(R.id.retourAccueil);
		btnRetour.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		Intent intent = null;
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.distanceDecroissante:
			ReglageSingleton.getInstance().setRequetePourTriRandonnee("length DESC");
			break;
		case R.id.distanceCroissante:
			ReglageSingleton.getInstance().setRequetePourTriRandonnee("length ASC");
			break;
		case R.id.difficulteDecroissante:
			ReglageSingleton.getInstance().setRequetePourTriRandonnee("difficulty DESC");
			break;
		case R.id.difficulteCroissante:
			ReglageSingleton.getInstance().setRequetePourTriRandonnee("difficulty ASC");
			break;
		case R.id.nomAZ:
			ReglageSingleton.getInstance().setRequetePourTriRandonnee("name ASC");
			break;
		case R.id.nomZA:
			ReglageSingleton.getInstance().setRequetePourTriRandonnee("name DESC");
			break;
		case R.id.retourAccueil:
			intent = new Intent(Reglage.this, Accueil.class);
			break;
		default:
			break;
	}
		intent = new Intent(Reglage.this, Accueil.class);
		startActivity(intent);
	}
	
	

}
