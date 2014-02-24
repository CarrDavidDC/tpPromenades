package ihm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import ihm.Reglage;
import metier.Promenade;
import metier.PromenadeAdapter;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
import android.widget.SearchView;
import android.widget.ShareActionProvider;
import android.widget.Toast;
import bdd.DatabaseHandler;
import bdd.DownloadData;
import bdd.TablePromenade;

import com.example.tppromenades.R;

@SuppressLint("NewApi")
public class Accueil extends Activity implements OnClickListener, OnItemClickListener,SearchView.OnQueryTextListener, SearchView.OnCloseListener {

	private List<Promenade> listePromenade;
	private SearchView mSearchView;
	private PromenadeAdapter adapter;
	private ListView listeViewPromenade;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_accueil);
		listeViewPromenade = (ListView)findViewById(R.id.listViewAccueil);
		
		
		listePromenade = new ArrayList<Promenade>();
		remplirPromenade();
		
		
	}
	
	private void miseAJourListView()
	{
		adapter = new PromenadeAdapter(this, R.layout.activity_accueil_listview,listePromenade);
		adapter.notifyDataSetChanged();
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
		miseAJourListView();
	}
	
	@SuppressLint("NewApi")
	public boolean onCreateOptionsMenu(Menu menu) {
        //Création d'un MenuInflater qui va permettre d'instancier un Menu XML en un objet Menu
        MenuInflater inflater = getMenuInflater();
        //Instanciation du menu XML spécifier en un objet Menu
        inflater.inflate(R.menu.menu_general, menu);
        MenuItem searchItem = menu.findItem(R.id.menu_search);
        mSearchView = (SearchView) searchItem.getActionView();
        mSearchView.setOnQueryTextListener(this);
        mSearchView.setOnCloseListener(this);
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

	@Override
	public boolean onQueryTextChange(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean onClose() {
		remplirPromenade();
        return false;
    }
	
	
	@Override
	public boolean onQueryTextSubmit(String arg0) {
		// TODO Auto-generated method stub
		TablePromenade tP = new TablePromenade(new DatabaseHandler(this));		
		String recherche = arg0.replace("'", "\\'");
		Toast.makeText(getBaseContext(),recherche+" élement(s) recherché(s).",Toast.LENGTH_SHORT).show();
		ArrayList<Promenade> listePromenadeBd = tP.selectionnerSelonRecherche(this.getApplicationContext(),recherche);
		Toast.makeText(getBaseContext(),listePromenadeBd.size()+" élement(s) recherché(s).",Toast.LENGTH_SHORT).show();
		listePromenade.clear();
		listePromenade = listePromenadeBd;
		miseAJourListView();
		return false;
	}

}
