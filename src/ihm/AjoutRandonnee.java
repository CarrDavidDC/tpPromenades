package ihm;

import com.example.tppromenades.R;
import com.example.tppromenades.R.id;
import com.example.tppromenades.R.layout;
import com.example.tppromenades.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class AjoutRandonnee extends Activity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ajout_randonnee);		

		final ImageButton btnRetour = (ImageButton) findViewById(R.id.ibPrecedentAjoutRandonnee);
		btnRetour.setOnClickListener(this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ajout_randonnee, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		Intent intent = null;
		switch (v.getId()) {
			case R.id.ibPrecedentAjoutRandonnee:
				intent = new Intent(AjoutRandonnee.this, Accueil.class);
				break;
			default:
				break;
		}
		startActivity(intent);
	}
 
}
