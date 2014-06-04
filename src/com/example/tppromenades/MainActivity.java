package com.example.tppromenades;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import ihm.Accueil;
import ihm.EnregistrerPromenade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import bdd.DatabaseHandler;
import bdd.DownloadData;
import bdd.TablePromenade;
import com.example.tppromenades.R;

public class MainActivity extends Activity{
	private NetworkInfo mWifi;
	private Context _context;
	private Activity _activity;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _context = this;
        _activity = this;
		

		ProgressBar pb = (ProgressBar) findViewById(R.id.pbHeaderProgress);
		pb.setIndeterminate(true);
	//	HttpGetter g = (HttpGetter) new HttpGetter(_context);

	//		g.execute(strURL);
        
       /* ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		if (mWifi.isConnected()) {
			System.out.println("WIFI");
			DatabaseHandler db = new DatabaseHandler(_context);
			DownloadData d = (DownloadData) new DownloadData(_activity, db).execute();
			try {
				while(d.get()!=null) {
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("DOWNLOAD TERMINE");
		//	d.get_tp().selectionnerTout(this.getApplicationContext());
			
		}
		else {
			Toast.makeText(this.getApplicationContext(), "Unable to download data without Wifi connection", Toast.LENGTH_LONG).show();
		}*/
		// Toast.makeText(_context, "Serveur : " + getServerData(strURL), Toast.LENGTH_LONG).show();
   	  
		/*Thread toRun = new Thread()

        {
               public void run()
               {
                   try {
                	   
                    sleep(15000);

                    Intent intent = new Intent(MainActivity.this, Accueil.class);
                     startActivity(intent);

                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
               }
        };
        toRun.start();*/

		//postData();
		
		HttpGetter g = (HttpGetter) new HttpGetter(this).execute("quelquechose");	
		
		Toast.makeText(this.getApplicationContext(), "Dl : " + g.getString(), Toast.LENGTH_LONG).show();
	}
	public String response = "";
	public void postData() {
	    // Create a new HttpClient and Post Header
	    HttpClient httpclient = new DefaultHttpClient();
	    HttpPost httppost = new HttpPost("http://192.168.0.167:80/index.php");

	    try {
	        // Add your data
	        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
	        nameValuePairs.add(new BasicNameValuePair("ville", "L"));
	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

	        // Execute HTTP Post Request
	        httpclient.execute(httppost);
	        
	    } catch (ClientProtocolException e) {
	        // TODO Auto-generated catch block
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	    }
	} 
	public static final String strURL = "http://192.168.0.167/index.php";
	
	public void onStart() {
		super.onStart();
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
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
}
