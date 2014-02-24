package bdd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import metier.Promenade;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

/**
 * The AsyncTask class must be loaded on the UI thread. This is done automatically as of JELLY_BEAN.
 * The task instance must be created on the UI thread.
 * execute(Params...) must be invoked on the UI thread.
 * Do not call onPreExecute(), onPostExecute(Result), doInBackground(Params...), onProgressUpdate(Progress...) manually.
 * The task can be executed only once (an exception will be thrown if a second execution is attempted.)
 * @author Hugo
 *
 */
public class DownloadData extends AsyncTask<Void, Integer, Long> {
	private static final String _URLSENTIERS = "https://download.data.grandlyon.com/ws/grandlyon/evg_esp_veg.evgsentiernature/all.json";
	private static final String _URLGPSCOORDINATES = "https://download.data.grandlyon.com/ws/grandlyon/evg_esp_veg.evgsentiernature/the_geom.json";
	private static final String _MULTILINESTRING = "MULTILINESTRING";
	private static final String _LEFTPARENTHESIS = "(";
	private static final String _RIGHTPARENTHESIS = ")";
	private static final String _HOURS = "h";
	private static final String _MINUTES = "min";
	private static final String _SECONDES = "s";
	private static final String _SPACE = " ";
	private static final String _COMMA = ",";
	private Activity _parent;
	private String _url;
	private ArrayList<Promenade> _promenadelist;
	private DatabaseHandler _db;
	private Context _context;
	private TablePromenade _tp;
	
	public TablePromenade get_tp() {
		return _tp;
	}

	public void set_tp(TablePromenade _tp) {
		this._tp = _tp;
	}

	public String get_url() {
		return _url;
	}

	public void set_url(String _url) {
		this._url = _url;
	}

	public ArrayList<Promenade> get_promenadelist() {
		return _promenadelist;
	}

	public DownloadData(Activity parent, ArrayList<Promenade> p, String url) {
		super();
		_parent = parent;
		_promenadelist = p;
		_url = url;
	}
	
	public DownloadData(Activity parent, DatabaseHandler db, String url) {
		super();
		_parent = parent;
		_db = db;
		_tp = new TablePromenade(_db);
		_promenadelist = new ArrayList<Promenade>();
		_url = url;
		_context = db.get_context();
	}
	
	public DownloadData(Activity parent, DatabaseHandler db) {
		super();
		_parent = parent;
		_db = db;
		_tp = new TablePromenade(_db);
		_promenadelist = new ArrayList<Promenade>();
		_context = db.get_context();
	}
	
	protected void onPreExecute() {
		super.onPreExecute(); 
	}
	
	@Override
	protected Long doInBackground(Void... params) {
		try {
			_url = _URLSENTIERS;
			JSONObject object = new JSONObject(readData());
			JSONArray values = object.getJSONArray("values");
			System.out.println("VALUES.LENGTH = " + values.length());
			for (int i = 0; i < values.length(); i++) {
				JSONArray prom = values.getJSONArray(i);
				String name = prom.getString(0);
				double length = Float.parseFloat(prom.getString(1));
				String durationTemp = prom.getString(2);
				String duration = durationTemp.replace(_HOURS, "");
				duration = duration.replace(_MINUTES, "");
				duration = duration.replace(_SECONDES, "");
				StringTokenizer st = new StringTokenizer(duration," ");
				int heures, minutes;
				if(st.countTokens()==1) {
					if(durationTemp.contains("s")) {
						heures = 0;
						minutes = 1;
					}
					else {
						heures = 0;
						minutes = Integer.parseInt(st.nextToken());
					}
				}
				else if (st.countTokens()==2){
					heures = Integer.parseInt(st.nextToken());
					minutes = Integer.parseInt(st.nextToken());
				}
				else {
					heures = Integer.parseInt(st.nextToken());
					minutes = Integer.parseInt(st.nextToken()) + 1;
				}
				//System.out.println("DURATIONTEMP = " + durationTemp + " HEURES = " + heures + " MINUTES = " + minutes);
				String theme = prom.getString(3);
				String d = prom.getString(4);
				float difficulty;
				if(d.length()>4) {
					d = d.substring(7, 8);
					difficulty = Float.parseFloat(d);
					difficulty = (difficulty * 5) / 3;
					difficulty = (float) (Math.ceil(difficulty * 2) / 2);
				}
				else {
					difficulty = 0;
				}
				String id = prom.getString(5);
				String project = prom.getString(6);
				int gid = Integer.parseInt(prom.getString(7));
				Promenade p = new Promenade(gid, name, length, heures, minutes, theme, difficulty, id, project);
				_promenadelist.add(p);
			}						
			_url = _URLGPSCOORDINATES;
			object = new JSONObject(readData());
			values = object.getJSONArray("values");
			for (int i = 0; i < values.length(); i++) {
				String prom = values.getString(i);
				prom = prom.replace(_MULTILINESTRING, "");
				prom = prom.replace(_LEFTPARENTHESIS, "");
				prom = prom.replace(_RIGHTPARENTHESIS, "");
				StringTokenizer st = new StringTokenizer(prom,_COMMA);
				System.out.println("NB AVANT = " + st.countTokens());
				String chaine = "";
				//System.out.println("NOUVELLE CHAINE");
				while(st.hasMoreTokens()) {
					String pt = st.nextToken();
					//System.out.println("AVANT = " + pt);
					String[] point = pt.split(_SPACE);
					pt = point[1] + _SPACE + point[0];	
					if(st.hasMoreTokens())
						chaine += pt + _COMMA;
					else
						chaine += pt;
				}
				//StringTokenizer st22 = new StringTokenizer(chaine,_COMMA);
				//System.out.println("NB APRES = " + st22.countTokens());
				//System.out.println("CHAINE = " + points.toString());
				//System.out.println("CHAINE = " + prom);
				_promenadelist.get(i).set_way(chaine);
			}
			_tp.sauvegarderPromenades(_promenadelist);
		} 
		catch (Exception e) { 
			e.printStackTrace(); 
		} 
		return null;
	}
	
	/*************************************************************************************
	ArrayList<LatLng> chemin = new ArrayList<LatLng>();
	for (int i = 0; i < values.length(); i++) {
		String prom = values.getString(i);
		prom = prom.replace(_MULTILINESTRING, "");
		prom = prom.replace(_LEFTPARENTHESIS, "");
		prom = prom.replace(_RIGHTPARENTHESIS, "");
		StringTokenizer stv = new StringTokenizer(prom, _COMMA); 
		while(stv.hasMoreTokens()) {
			StringTokenizer sts = new StringTokenizer(stv.nextToken(), _SPACE);
			LatLng point = new LatLng(Double.parseDouble(sts.nextToken()),Double.parseDouble(sts.nextToken()));
			chemin.add(point);
			//System.out.println("Nouveau point : " + point.toString());
		}
		_promenadelist.get(i).set_way(chemin);
	}
	 *************************************************************************************/
		
	protected String readData() {
		StringBuilder builder = new StringBuilder();
		HttpClient client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(_url);
		try { 
			HttpResponse response = client.execute(httpGet);
			StatusLine statusLine = response.getStatusLine();
			int statusCode = statusLine.getStatusCode(); 
			if (statusCode == 200) { 
				HttpEntity entity = response.getEntity();
				InputStream content = entity.getContent();
				BufferedReader reader = new BufferedReader(new InputStreamReader(content));
				String line;
				while ((line = reader.readLine()) != null) {
					builder.append(line);
				}
			} 
			else {
				Log.e(_parent.getClass().toString(), "Failed to download file");
			}
		} 
		catch (ClientProtocolException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		return builder.toString();  
	}

	protected void onPostExecute(Long result) {
		/*Toast.makeText(_parent.getApplicationContext(), "Finished downloading data", Toast.LENGTH_LONG).show(); 
		newsList = (ListView) findViewById(R.id.list); 
		newsList.setAdapter(new NewsAdapter(getBaseContext(), newsArray)); 
		newsList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,int arg2, long arg3) {
				String url = newsArray.get(arg2).getUrl();
				Bundle b = new Bundle();
				b.putString("URL","http://"+url);
				
				Intent i = new Intent(MainActivity.this, WebActivity.class);
				i.putExtras(b);
				startActivity(i);	
			}
		});*/
	}
}
