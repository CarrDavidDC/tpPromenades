package bdd;

import android.app.Activity;
import android.os.AsyncTask;

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
	
	private Activity _parent;
	
	public DownloadData(Activity parent) {
		super();
		_parent = parent;
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
	
	protected void onPreExecute() {
		/*super.onPreExecute(); 
		Toast.makeText(_parent.getApplicationContext(), "Start downloading data.", Toast.LENGTH_LONG).show();*/
	}
		
	/*protected String[] readData() {
		String[] data = new String[2];
		StringBuilder builder = new StringBuilder();
		HttpClient client = new DefaultHttpClient();
		HttpGet walkAttributes = new HttpGet("https://download.data.grandlyon.com/ws/grandlyon/evg_esp_veg.evgsentiernature/all.json");
		HttpGet gpsCoordinates = new HttpGet("https://download.data.grandlyon.com/ws/grandlyon/evg_esp_veg.evgsentiernature/the_geom.json");
		try { 
			HttpResponse response = client.execute(walkAttributes);
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
				Log.e(_parent.toString(), "Failed to download file");
			}
			data[0] = builder.toString();
			builder = new StringBuilder();
			response = client.execute(gpsCoordinates);
			statusLine = response.getStatusLine();
			statusCode = statusLine.getStatusCode(); 
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
				Log.e(_parent.toString(), "Failed to download file");
			}
			data[1] = builder.toString();
		} 
		catch (ClientProtocolException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		return data; 
	}*/

	@Override
	protected Long doInBackground(Void... params) {
		// TODO Auto-generated method stub
		return null;
	}
}
