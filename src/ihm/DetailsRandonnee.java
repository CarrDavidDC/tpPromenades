package ihm;

import com.example.tppromenades.R;
import com.example.tppromenades.R.layout;
import com.example.tppromenades.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class DetailsRandonnee extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		int j = 0;
		setContentView(R.layout.activity_details_randonnee);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.details_randonnee, menu);
		return true;
	}
 
}
