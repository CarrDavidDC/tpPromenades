package ihm;

import java.util.ArrayList;
import java.util.List;

import ihm.Reglage;
import metier.Historique;
import metier.HistoriqueAdapter;
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
import bdd.TableHistorique;
import bdd.TablePromenade;

import com.example.tppromenades.R;

public class HistoriqueRandonnee extends Activity implements OnClickListener, OnItemClickListener {

	private List<Promenade> listePromenade;
	private ArrayList<Historique>listHistorique;
	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_historique);
		
		Intent i = getIntent();
		listHistorique = new ArrayList<Historique>();
		int idRandonnee = (Integer)i.getSerializableExtra("idRandonnee");
		TableHistorique tb = new TableHistorique(new DatabaseHandler(this));
		listHistorique = tb.selectionnerSelonIdRandonnee(getApplicationContext(), idRandonnee);
		ListView listeViewPromenade = (ListView)findViewById(R.id.listViewHistorique);
		HistoriqueAdapter adapter = new HistoriqueAdapter(this, R.layout.activity_historique_listview,listHistorique);
		listeViewPromenade.setAdapter(adapter);
		listeViewPromenade.setClickable(false);
	//	listeViewPromenade.setOnItemClickListener(this);
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
                return true;
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
		/*Promenade value = (Promenade) adapter.getItemAtPosition(position); 
		Intent intent = new Intent(HistoriqueRandonnee.this, DetailsRandonnee.class);
		intent.putExtra("Promenade", value);
		startActivity(intent);*/
	}

}
