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
import android.util.Log;
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
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
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
		/*InputStream is = null;
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("ville","L"));
		// Envoie de la commande http
		try{
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(strURL);
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();

		}catch(Exception e){
			Toast.makeText(getBaseContext(),"Error : "+e.toString(),Toast.LENGTH_SHORT).show();
			Log.e("log_tag", "Error in http connection " + e.toString());
		}*/
		//Toast.makeText(getBaseContext(),"Dwl ! "+getServerData(),Toast.LENGTH_SHORT).show();
	}
	
	public static final String strURL = "http://192.168.0.167:80/src/index.php";
	
	private String getServerData() {
		InputStream is = null;
		String result = "";
		String returnString = "";
		// Envoyer la requête au script PHP.
		// Script PHP : $sql=mysql_query("select * from tblVille where Nom_ville like '".$_REQUEST['ville']."%'");
		// $_REQUEST['ville'] sera remplacé par L dans notre exemple.
		// Ce qui veut dire que la requête enverra les villes commençant par la lettre L
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("ville","L"));

		// Envoie de la commande http
		try{
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(strURL);
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();

		}catch(Exception e){
			Toast.makeText(getBaseContext(),"Error : "+e.toString(),Toast.LENGTH_SHORT).show();
			Log.e("log_tag", "Error in http connection " + e.toString());
		}

		// Convertion de la requête en string
		try{
			BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			result=sb.toString();
		}catch(Exception e){
			Log.e("log_tag", "Error converting result " + e.toString());
		}
		// Parse les données JSON
		try{
			JSONArray jArray = new JSONArray(result);
			for(int i=0;i<jArray.length();i++){
				JSONObject json_data = jArray.getJSONObject(i);
				// Affichage ID_ville et Nom_ville dans le LogCat
				Log.i("log_tag","ID_ville: "+json_data.getInt("ID_ville")+
						", Nom_ville: "+json_data.getString("Nom_ville")
				);
				// Résultats de la requête
				returnString += "\n\t" + jArray.getJSONObject(i); 
			}
		}catch(JSONException e){
			Log.e("log_tag", "Error parsing data " + e.toString());
		}
		return returnString; 
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
