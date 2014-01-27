package ihm;

import java.util.ArrayList;
import java.util.List;

import metier.Promenade;
import metier.PromenadeAdapter;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.tppromenades.R;

public class Accueil extends Activity implements OnClickListener, OnItemClickListener {

	 
	 private List<Promenade> listePromenade;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_accueil);
		
		final ImageButton btnAjout = (ImageButton) findViewById(R.id.ibAddRandonneeAccueil);
		btnAjout.setOnClickListener(this);
		
		ListView listeViewPromenade = (ListView)findViewById(R.id.listViewAccueil);
		listePromenade = new ArrayList<Promenade>();
		remplirPromenade();
		PromenadeAdapter adapter = new PromenadeAdapter(this, R.layout.activity_accueil_listview,listePromenade);
		listeViewPromenade.setAdapter(adapter);
		//createMap();
		listeViewPromenade.setClickable(true);
		listeViewPromenade.setOnItemClickListener(this);
	}
	
	private void remplirPromenade()
	{
		// pour l'instant pas avec le fichier de bd
		listePromenade.clear();
		listePromenade.add(new Promenade("Premande grand Lyon", 45.6, "1H30"));
		listePromenade.add(new Promenade("Marennes city", 75.6, "4H42"));
		listePromenade.add(new Promenade("Caluire", 23, "2H30"));
		listePromenade.add(new Promenade("Ecully",12, "30min"));
		listePromenade.add(new Promenade("Tartimuche", 32, "3H"));
		listePromenade.add(new Promenade("Premande grand Lyon", 45.6, "1H30"));
		listePromenade.add(new Promenade("Marennes city", 75.6, "4H42"));
		listePromenade.add(new Promenade("Caluire", 23, "2H30"));
		listePromenade.add(new Promenade("Ecully",12, "30min"));
		listePromenade.add(new Promenade("Tartimuche", 32, "3H"));
		listePromenade.add(new Promenade("Premande grand Lyon", 45.6, "1H30"));
		listePromenade.add(new Promenade("Marennes city", 75.6, "4H42"));
		listePromenade.add(new Promenade("Caluire", 23, "2H30"));
		listePromenade.add(new Promenade("Ecully",12, "30min"));
		listePromenade.add(new Promenade("Tartimuche", 32, "3H"));
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.accueil, menu);
		return true;
	} 
	
	public void onClick(View v) {
		Intent intent = null;
		
		switch (v.getId()) {
			case R.id.ibAddRandonneeAccueil:
				intent = new Intent(Accueil.this, AjoutRandonnee.class);	
				break;
			default:
				break;
		}
		startActivity(intent);
	}

	@Override
	public void onItemClick(AdapterView<?> adapter, View arg1, int position, long arg3) {
		// TODO Auto-generated method stub
		Promenade value = (Promenade) adapter.getItemAtPosition(position); 
		Toast.makeText(getApplicationContext(), "Nom : " + value.get_name(), Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(Accueil.this, DetailsRandonnee.class);
		intent.putExtra("Promenade", value);
		startActivity(intent);
		finish();
	}

}
