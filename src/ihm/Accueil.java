package ihm;

import java.util.ArrayList;
import java.util.List;

import reglage.ReglageSingleton;

import ihm.Reglage;
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
import android.widget.ShareActionProvider;
import android.widget.Toast;
import bdd.DatabaseHandler;
import bdd.DownloadData;
import bdd.TablePromenade;

import com.example.tppromenades.R;

public class Accueil extends Activity implements OnClickListener, OnItemClickListener {

	private List<Promenade> listePromenade;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_accueil);
		ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		DatabaseHandler db = new DatabaseHandler(this);
		if (mWifi.isConnected()) {
			DownloadData d = (DownloadData) new DownloadData(this,db, "https://download.data.grandlyon.com/ws/grandlyon/evg_esp_veg.evgsentiernature/all.json",getBaseContext()).execute();

			Toast.makeText(getBaseContext(),"Ajout du gd lyon",Toast.LENGTH_SHORT).show();
		}		
		ListView listeViewPromenade = (ListView)findViewById(R.id.listViewAccueil);
		listePromenade = new ArrayList<Promenade>();
		remplirPromenade();
		PromenadeAdapter adapter = new PromenadeAdapter(this, R.layout.activity_accueil_listview,listePromenade);
		listeViewPromenade.setAdapter(adapter);
		listeViewPromenade.setClickable(true);
		listeViewPromenade.setOnItemClickListener(this);
	}
	
	private void remplirPromenade()
	{
		// pour l'instant pas avec le fichier de bd
		TablePromenade tP = new TablePromenade(new DatabaseHandler(this));
		ArrayList<Promenade> listePromenadeBd = tP.selectionnerTout(this.getApplicationContext());
		listePromenade.clear();
		listePromenade = listePromenadeBd;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
        //Création d'un MenuInflater qui va permettre d'instancier un Menu XML en un objet Menu
        MenuInflater inflater = getMenuInflater();
        //Instanciation du menu XML spécifier en un objet Menu
        inflater.inflate(R.menu.menu_general, menu);
        return true;
	} 
	
	public void goAjoutRandonnee(MenuItem item){
		Intent intent = new Intent(Accueil.this, AjoutRandonnee.class);	
		startActivity(intent);
	}
	
	public void goReglage(MenuItem item){
		Intent intent = new Intent(Accueil.this, Reglage.class);	
		startActivity(intent);
	}
	
	public void onClick(View v) {
		Intent intent = null;
		
		switch (v.getId()) {
			default:
				break;
		}
		startActivity(intent);
	}

	@Override
	public void onItemClick(AdapterView<?> adapter, View arg1, int position, long arg3) {
		// TODO Auto-generated method stub
		Promenade value = (Promenade) adapter.getItemAtPosition(position); 
		Intent intent = new Intent(Accueil.this, DetailsRandonnee.class);
		intent.putExtra("Promenade", value);
		startActivity(intent);
	}

}
