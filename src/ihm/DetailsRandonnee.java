package ihm;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import map.CreateCourse;
import metier.Historique;
import metier.Promenade;

import bdd.DatabaseHandler;
import bdd.TableHistorique;
import bdd.TablePromenade;

import com.google.android.gms.maps.MapFragment;
import com.example.tppromenades.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polygon;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import Parser.Parser;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.ShareActionProvider;
import android.widget.TextView;
import android.widget.Toast;

public class DetailsRandonnee extends Activity implements OnClickListener {

	private ShareActionProvider myShareActionProvider;
	private GoogleMap map;
	private Promenade maPromenade;
	private ImageView ivLogo;
	private Button voirCarte;
	private Button votreHistorique;

	private String[] mMonth = new String[] {
		       "Jan", "Feb" , "Mar", "Apr", "May", "Jun",

		       "Jul", "Aug" };
private GraphicalView mChart;
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//int j = 0;
		setContentView(R.layout.activity_details_randonnee);

		
	//	final ImageButton btnRetour = (ImageButton) findViewById(R.id.ibPrecedentDetailsRandonnee);
		//btnRetour.setOnClickListener(this);
		TextView tvName = (TextView) findViewById(R.id.name);
		TextView tvDuration = (TextView) findViewById(R.id.duration);
		TextView denivele = (TextView) findViewById(R.id.denivele);
		TextView tvLength = (TextView) findViewById(R.id.length);
		TextView description = (TextView) findViewById(R.id.description);
		RatingBar difficulteRandonnee = (RatingBar)findViewById(R.id.difficulte);
		difficulteRandonnee.setEnabled(false);

		Button voirCarte = (Button) findViewById(R.id.apercuMap);
		Button votreHistorique = (Button) findViewById(R.id.voirHistorique);
		voirCarte.setOnClickListener(this);
		votreHistorique.setOnClickListener(this);
		Intent i = getIntent();
		maPromenade = (Promenade)i.getSerializableExtra("Promenade");		
	//	ivLogo= (ImageView)findViewById(R.id.ivLogo);
		difficulteRandonnee.setRating((float) maPromenade.get_difficulty());
		tvName.setText(maPromenade.get_name().toUpperCase());
		description.setText(maPromenade.get_description());
		tvDuration.setText(maPromenade.getDurationToString());
		tvLength.setText(maPromenade.get_length_arrondi() +" kms");
		denivele.setText(maPromenade.get_altitude() + " m");
		Bitmap img = null;
		
	/*	
	 * 		POUR APRES QUAND ON METTRA LA MAP SUR LE DETAILS DE LA RANDO
	 * 
	 * 
	 * 
	 * ArrayList<LatLng> listLatLng  = new ArrayList<LatLng>();
	 
		ArrayList<Double> listOfDistance  = new ArrayList<Double>();
		ArrayList<Integer> listOfAltitude  = new ArrayList<Integer>();
        listLatLng = Parser.stringToArrayListLatLng(maPromenade.get_way());
        
        for(int z = 0; z < listLatLng.size()-1;z++)
		{
			LatLng currentPoint = listLatLng.get(z);
			LatLng nextPoint = listLatLng.get(z+1);
			listOfDistance.add(getDistance(currentPoint,nextPoint));
			java.util.Random rand = new java.util.Random();
			int leNombreAleatoire = rand.nextInt(200);
			listOfAltitude.add(leNombreAleatoire);
		}
        
	       
	       XYSeries xSeries=new XYSeries("X Series");
	       
	       
	       for(int w=0;w<listOfDistance.size();w++)
	       {
	    	   xSeries.add(listOfDistance.get(w),listOfAltitude.get(w));
	      
	       }
	       Toast.makeText(getBaseContext(),"Nb listOfDistance : " + listOfDistance.size(),Toast.LENGTH_SHORT).show();
	       
	       XYMultipleSeriesDataset dataset=new XYMultipleSeriesDataset();
	       
	       // Add X series to the Dataset.  
	 dataset.addSeries(xSeries);
	    
	    
	       // Create XYSeriesRenderer to customize XSeries

	     XYSeriesRenderer Xrenderer=new XYSeriesRenderer();
	     Xrenderer.setColor(Color.GREEN);
	     Xrenderer.setPointStyle(PointStyle.DIAMOND);
	     Xrenderer.setDisplayChartValues(true);
	     Xrenderer.setLineWidth(2);
	     Xrenderer.setFillPoints(true);
	    
	      // Create XYMultipleSeriesRenderer to customize the whole chart

	     XYMultipleSeriesRenderer mRenderer=new XYMultipleSeriesRenderer();
	    
	   /*  mRenderer.setChartTitle("X Vs Y Chart");
	     mRenderer.setXTitle("kms");
	     mRenderer.setYTitle("Altitude");
	     mRenderer.setZoomButtonsVisible(false);
	     mRenderer.setXLabels(0);
	     mRenderer.setPanEnabled(false);
	    
	     mRenderer.setShowGrid(true);
	  
	     mRenderer.setClickEnabled(true);
	    
	     for(int k=0;k<listLatLng.size()-1;k++)
	     {
	    	 mRenderer.addXTextLabel(k, Integer.toString(listOfAltitude.get(k)));
	     }
	     
	     mRenderer.addSeriesRenderer(Xrenderer);*/
	 /*   LinearLayout chart_container=(LinearLayout)findViewById(R.id.Chart_layout);
	/     mChart=(GraphicalView)ChartFactory.getLineChartView(getBaseContext(), dataset, mRenderer);
	     chart_container.addView(mChart);
	/*	if(maPromenade.get_image() != null)
		{
			Toast.makeText(getBaseContext(),"J'ai une image",Toast.LENGTH_SHORT).show();
			img = BitmapFactory.decodeByteArray(maPromenade.get_image(), 0, maPromenade.get_image().length);
			ivLogo.setImageBitmap(img);
		}*/
		TableHistorique tb = new TableHistorique(new DatabaseHandler(this));
		if(!tb.getHistoriqueSelonRandonnee(this.getApplicationContext(), maPromenade.get_gid()))
		{
			votreHistorique.setVisibility(View.INVISIBLE);
		}
		
	}
	private double getDistance(LatLng LatLng1, LatLng LatLng2) {
	    double distance = 0;
	    Location locationA = new Location("A");
	    locationA.setLatitude(LatLng1.latitude);
	    locationA.setLongitude(LatLng1.longitude);
	    Location locationB = new Location("B");
	    locationB.setLatitude(LatLng2.latitude);
	    locationB.setLongitude(LatLng2.longitude);
	    distance = locationA.distanceTo(locationB);
	    return distance;
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
        //Création d'un MenuInflater qui va permettre d'instancier un Menu XML en un objet Menu
        MenuInflater inflater = getMenuInflater();
        //Instanciation du menu XML spécifier en un objet Menu
        inflater.inflate(R.menu.menu_partage, menu);
        MenuItem itemProvider = menu.findItem(R.id.menu_item_share);
        myShareActionProvider = (ShareActionProvider)itemProvider.getActionProvider();
        myShareActionProvider.setShareHistoryFileName(
          ShareActionProvider.DEFAULT_SHARE_HISTORY_FILE_NAME);
        myShareActionProvider.setShareIntent(createShareIntent());
        return true;
	} 
	private Intent createShareIntent() {
		String partageRando = "";
		partageRando = "Je souhaite vous faire partager la randonnée " + maPromenade.get_name()+" que j'ai trouvé sur l'application tpPromenades\n";
		partageRando+= "Voici le détails de cette randonnée :\n";
		partageRando+="Nom : " + maPromenade.get_name()+"\n";
		partageRando+="Description : " + maPromenade.get_description()+"\n";
		partageRando+="Distance : " + maPromenade.get_length()+"\n";
		partageRando+="Durée : " + maPromenade.get_durationHour() + "h"+maPromenade.get_durationMinute()+"\n";
		partageRando+="Difficulté : " + maPromenade.get_difficulty()+"\n";
		
		  Intent shareIntent = new Intent(Intent.ACTION_SEND);
		        shareIntent.setType("text/plain");
		        shareIntent.putExtra(Intent.EXTRA_TEXT, partageRando);
		        return shareIntent;
		    }
 
	

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = null;
		switch (v.getId()) {
			/*case R.id.ibPrecedentDetailsRandonnee:
				intent = new Intent(DetailsRandonnee.this, Accueil.class);	
				break;*/
			case R.id.voirHistorique:
				intent = new Intent(DetailsRandonnee.this, HistoriqueRandonnee.class);	
				intent.putExtra("idRandonnee", maPromenade.get_gid());
				break;
			case R.id.apercuMap:
					intent = new Intent(DetailsRandonnee.this, DetailsRandonneeApercuMap.class);
					intent.putExtra("Promenade", maPromenade);
				break;
			default:
				break;
		}
		startActivity(intent);
	}
}
