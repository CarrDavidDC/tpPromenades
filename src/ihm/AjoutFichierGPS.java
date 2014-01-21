package ihm;

import com.example.tppromenades.R;
import com.example.tppromenades.R.layout;
import com.example.tppromenades.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class AjoutFichierGPS extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ajout_fichier_gps);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ajout_fichier_g, menu);
		return true;
	}
 
}
