package ihm;

import map.CreateCourse;
import metier.Promenade;

import com.google.android.gms.maps.MapFragment;
import com.example.tppromenades.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Polygon;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class DetailsRandonnee extends Activity implements OnClickListener {

	private GoogleMap map;
	private Promenade maPromenade;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details_randonnee);
		createMap();
		
		final ImageButton btnRetour = (ImageButton) findViewById(R.id.ibPrecedentDetailsRandonnee);
		btnRetour.setOnClickListener(this);
		TextView tvName = (TextView) findViewById(R.id.name);
		TextView tvDuration = (TextView) findViewById(R.id.duration);
		TextView tvLength = (TextView) findViewById(R.id.length);
		
		Intent i = getIntent();
		maPromenade = (Promenade)i.getSerializableExtra("Promenade");		

		tvName.setText("Nom : " + maPromenade.get_name());
		tvDuration.setText("Durée : " + maPromenade.get_duration());
		tvLength.setText("Distance : " + Float.toString(maPromenade.get_length()));
		Toast.makeText(getApplicationContext(), "Nom : " + maPromenade.get_name(), Toast.LENGTH_SHORT).show();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.details_randonnee, menu);
		return true;
	}
 
	public void createMap()
	{
		if (map == null) {
        	map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
            // Check if we were successful in obtaining the map.
            if (map != null) {
                // The Map is verified. It is now safe to manipulate the map.
            	Toast toast = Toast.makeText(getApplicationContext(), "LA MAP N EST PAS NULL", Toast.LENGTH_SHORT);
                toast.show();
                map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                
                CreateCourse createCourse = new CreateCourse("Ma première course","Description de la course",map);
                createCourse.createCourse();
                Polygon polygon = map.addPolygon(createCourse.getRectOptions());
               
                map.setMyLocationEnabled(true);
                map.animateCamera(CameraUpdateFactory.zoomIn());
                toast = Toast.makeText(getApplicationContext(), "Builder OK", Toast.LENGTH_SHORT);
                toast.show();
                
                CameraPosition cameraPosition = new CameraPosition.Builder().target(createCourse.getLatLng().get(0)).zoom(16.0f).build();
                CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
                map.moveCamera(cameraUpdate);             
                
                
            }else{
            	Toast toast = Toast.makeText(getApplicationContext(), "LA MAP EST NULL", Toast.LENGTH_SHORT);
                toast.show();
                
            }
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = null;
		
		switch (v.getId()) {
			case R.id.ibPrecedentDetailsRandonnee:
				intent = new Intent(DetailsRandonnee.this, Accueil.class);	
				break;
			default:
				break;
		}
		startActivity(intent);
	}
}
