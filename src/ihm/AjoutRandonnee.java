package ihm;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import reglage.ReglageSingleton;

import listener.CounterListener;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;
import bdd.DatabaseHandler;

import com.example.tppromenades.R;

public class AjoutRandonnee extends Activity implements OnClickListener{

	private double pas = 0.1;
	private Button btnPlus;
	private Button btnMoins;
	private Button btnEnregistrerAjoutRandonnee;
	private EditText distanceRandonnee;
	private EditText etNomRandonnee;
	private EditText etDescription;
	private RatingBar difficulteRandonnee;
	private Spinner listeboxMinute;
	private Spinner listeboxHeure;
	private Button button_selectpic;
	private String imagepath=null;
	private ImageView imageview;
	private Bitmap bitmap;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ajout_randonnee);		

		final ImageButton btnRetour = (ImageButton) findViewById(R.id.ibPrecedentAjoutRandonnee);
		btnRetour.setOnClickListener(this);
		btnPlus = (Button) findViewById(R.id.btnPlus);
		distanceRandonnee = (EditText) findViewById(R.id.distanceRandonnee);
		etDescription = (EditText) findViewById(R.id.etDescription);
		etNomRandonnee = (EditText) findViewById(R.id.etNomRandonnee);
		difficulteRandonnee = (RatingBar)findViewById(R.id.difficulteRandonnee);
		listeboxMinute= (Spinner)findViewById(R.id.listeboxMinute);
		listeboxHeure= (Spinner)findViewById(R.id.listboxHeure);
		button_selectpic = (Button)findViewById(R.id.button_selectpic);
		DatabaseHandler db = new DatabaseHandler(this);
		CounterListener listenerIncrement = new CounterListener("+", distanceRandonnee,pas,this,this);
		CounterListener listenerDecrement = new CounterListener("-", distanceRandonnee,pas,this,this);
		CounterListener listenerValidation = new CounterListener("validation", distanceRandonnee,pas,this,this);
		button_selectpic.setOnClickListener(this);
		btnPlus.setOnClickListener(listenerIncrement);
		btnPlus.setOnTouchListener(listenerIncrement);
		btnMoins = (Button) findViewById(R.id.btnMoins);
		btnMoins.setOnClickListener(listenerDecrement);
		btnMoins.setOnTouchListener(listenerDecrement);
	
		distanceRandonnee.setText("0");
		
		btnEnregistrerAjoutRandonnee = (Button) findViewById(R.id.btnEnregistrerAjoutRandonnee);
		btnEnregistrerAjoutRandonnee.setOnClickListener(listenerValidation);
		addItemsOnSpinnerHeures();
		addItemsOnSpinnerMinutes();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
        //Création d'un MenuInflater qui va permettre d'instancier un Menu XML en un objet Menu
        MenuInflater inflater = getMenuInflater();
        //Instanciation du menu XML spécifier en un objet Menu
        inflater.inflate(R.menu.menu_recherche, menu);
        return true;
	} 

	 @Override
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	         
	        if (requestCode == 1 && resultCode == RESULT_OK) {
	            //Bitmap photo = (Bitmap) data.getData().getPath();
	           
	            Uri selectedImageUri = data.getData();
	            imagepath = getPath(selectedImageUri);
	            try {
					bitmap =          BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImageUri));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Toast.makeText(getBaseContext(),"Error Upload",Toast.LENGTH_SHORT).show();
				}
                
	            
	             
	        }
	    }
	 
	 public String getPath(Uri uri) {
         String[] projection = { MediaStore.Images.Media.DATA };
         Cursor cursor = managedQuery(uri, projection, null, null, null);
         int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
         cursor.moveToFirst();
         return cursor.getString(column_index);
     }
  
	 
	@Override
	public void onClick(View v) {
		Intent intent = null;
		
		switch (v.getId()) {
			case R.id.ibPrecedentAjoutRandonnee:
				intent = new Intent(AjoutRandonnee.this, Accueil.class);
				startActivity(intent);
				break;
			case R.id.button_selectpic:
				Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
				photoPickerIntent.setType("image/*");
				startActivityForResult(photoPickerIntent, 1);
				break;
			default:
				break;
		}
		
	}
 
	
	private void addItemsOnSpinnerHeures() {
		 
		Spinner listeboxMinute = (Spinner) findViewById(R.id.listeboxMinute);
		List<String> list = new ArrayList<String>();
		for(int i = 0; i < 60; i++)
		{
			list.add(String.valueOf(i));
		}
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, list);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		listeboxMinute.setAdapter(dataAdapter);
	  }
	
	private void addItemsOnSpinnerMinutes() {
		 
		Spinner listboxHeure = (Spinner) findViewById(R.id.listboxHeure);
		List<String> list = new ArrayList<String>();
		for(int i = 0; i < 13; i++)
		{
			list.add(String.valueOf(i));
		}
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, list);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		listboxHeure.setAdapter(dataAdapter);
	  }

	public EditText getDistanceRandonnee() {
		return distanceRandonnee;
	}


	public EditText getEtNomRandonnee() {
		return etNomRandonnee;
	}


	public RatingBar getDifficulteRandonnee() {
		return difficulteRandonnee;
	}


	public Spinner getListeboxMinute() {
		return listeboxMinute;
	}


	public Spinner getListeboxHeure() {
		return listeboxHeure;
	}
	public EditText getEtDescription() {
		return etDescription;
	}

	public Button getButton_selectpic() {
		return button_selectpic;
	}

	public String getImagepath() {
		return imagepath;
	}

	public ImageView getImageview() {
		return imageview;
	}

	public Bitmap getBitmap() {
		return bitmap;
	}
	
	
	
	
	
}
