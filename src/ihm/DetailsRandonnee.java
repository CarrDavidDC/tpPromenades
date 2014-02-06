package ihm;

import reglage.ReglageSingleton;
import map.CreateCourse;
import metier.Promenade;

import com.google.android.gms.maps.MapFragment;
import com.example.tppromenades.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Polygon;

import android.os.Build;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
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
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//int j = 0;
		setContentView(R.layout.activity_details_randonnee);

		
		final ImageButton btnRetour = (ImageButton) findViewById(R.id.ibPrecedentDetailsRandonnee);
		btnRetour.setOnClickListener(this);
		TextView tvName = (TextView) findViewById(R.id.name);
		TextView tvDuration = (TextView) findViewById(R.id.duration);
		TextView tvLength = (TextView) findViewById(R.id.length);
		TextView description = (TextView) findViewById(R.id.description);
		RatingBar difficulteRandonnee = (RatingBar)findViewById(R.id.difficulte);
		difficulteRandonnee.setEnabled(false);
		Intent i = getIntent();
		maPromenade = (Promenade)i.getSerializableExtra("Promenade");		
		ivLogo= (ImageView)findViewById(R.id.ivLogo);
		difficulteRandonnee.setRating((float) maPromenade.get_difficulty());
		tvName.setText("Nom : " + maPromenade.get_name());
		description.setText(maPromenade.get_description());
		tvDuration.setText("Durée : " + maPromenade.get_durationHour() + "h"+maPromenade.get_durationMinute());
		tvLength.setText("Distance : " + maPromenade.get_length() +" kms");
		Bitmap img = null;
		if(maPromenade.get_image() != null)
		{
			img = BitmapFactory.decodeByteArray(maPromenade.get_image(), 0, maPromenade.get_image().length);
			ivLogo.setImageBitmap(img);
		}
		
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
			case R.id.ibPrecedentDetailsRandonnee:
				intent = new Intent(DetailsRandonnee.this, Accueil.class);	
				break;
			default:
				break;
		}
		startActivity(intent);
	}
}
