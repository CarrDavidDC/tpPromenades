package ihm;

import java.util.ArrayList;
import java.util.List;

import metier.Promenade;
import metier.PromenadeAdapter;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ShareActionProvider;
import android.widget.Toast;

import com.example.tppromenades.R;

public class Accueil extends Activity implements OnClickListener, OnItemClickListener {

	private List<Promenade> listePromenade;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_accueil);
				
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
		listePromenade.clear();
		listePromenade.add(new Promenade("Premande grand Lyon", "Magnifique sur Lyon qui va faire l'application android et puis par la suite ca va être trop génial car il faut que....", 45.6, "1H30",1));
		listePromenade.add(new Promenade("Marennes city","De la campagne encore et encore....", 75.6, "4H42",3));
		listePromenade.add(new Promenade("Caluire","La ville autrement", 23, "2h30",1.5));
		listePromenade.add(new Promenade("Ecully","Chemin de l'INPS", 12, "30min",2));
		listePromenade.add(new Promenade("Tartimuche","Raclette party", 32, "3h",3));
		listePromenade.add(new Promenade("Premande grand Lyon", 45.6, "1h30",4));
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
