package com.example.tppromenades;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutionException;

import ihm.Accueil;
import ihm.EnregistrerPromenade;

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

import bdd.DatabaseHandler;
import bdd.DownloadData;
import bdd.TablePromenade;
import metier.Promenade;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask.Status;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.widget.Toast;
import bdd.DatabaseHandler;
import bdd.DownloadData;
import bdd.TablePromenade;
import com.example.tppromenades.R;

public class MainActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
		ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

		if (mWifi.isConnected()) {
			System.out.println("WIFI");
			Toast.makeText(getBaseContext(),"Chargement des données",Toast.LENGTH_SHORT).show();
			DatabaseHandler db = new DatabaseHandler(this);
			DownloadData d = (DownloadData) new DownloadData(this, db).execute();
			Toast.makeText(getBaseContext(),"Download",Toast.LENGTH_SHORT).show();
			try {
				while(d.get()!=null) {
					Toast.makeText(getBaseContext(),"On tourne",Toast.LENGTH_SHORT).show();
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("DOWNLOAD TERMINE");
			Toast.makeText(getBaseContext(),"Download OK",Toast.LENGTH_SHORT).show();
		//	d.get_tp().selectionnerTout(this.getApplicationContext());
			
		}
		else {
			Toast.makeText(this.getApplicationContext(), "Unable to download data without Wifi connection", Toast.LENGTH_LONG).show();
		}
		
		Thread toRun = new Thread()

        {
               public void run()
               {
                   try {                      
                    sleep(3000);

                    Intent intent = new Intent(MainActivity.this, Accueil.class);
                     startActivity(intent);

                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
               }
        };
        toRun.start();
		
	}
	public static final String strURL = "http://192.168.0.167/index.php";
	
	private String getServerData(String returnString) {
		InputStream is = null;
		String result = "";
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
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent evt)
	{
		// PLUTÔT FAIRE CA DANS POSTEXECUTE DE DOWNLOADDATA
	    if(evt.getAction() == MotionEvent.ACTION_DOWN)
	    {
	    	Intent intent = new Intent(MainActivity.this, Accueil.class);
			startActivity(intent);
	    }
	    return true;
	    
	}  
}
