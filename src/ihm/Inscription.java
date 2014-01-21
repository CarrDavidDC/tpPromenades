package ihm;

import com.example.tppromenades.MainActivity;
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
import android.widget.Button;
import android.widget.ImageButton;

public class Inscription extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inscription);
		
		final Button btnInscriptionViaMail = (Button) findViewById(R.id.btnInscriptionMail);
		btnInscriptionViaMail.setOnClickListener(this);
		
		final ImageButton btnRetour = (ImageButton) findViewById(R.id.ibPrecedentInscription);
		btnRetour.setOnClickListener(this);
		 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.inscription, menu);
		return true;
	}
	
	public void onClick(View v) {
		Intent intent = null;
		
		switch (v.getId()) {
			case R.id.btnInscriptionMail:
				intent = new Intent(Inscription.this, InscriptionViaMail.class);
				break;
			case R.id.ibPrecedentInscription:
				intent = new Intent(Inscription.this, MainActivity.class);
				break;
			default:
				break;
		}
		startActivity(intent);
	}

}
