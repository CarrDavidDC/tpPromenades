package com.example.tppromenades;

import bdd.DatabaseHandler;
import metier.Promenade;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		DatabaseHandler db = new DatabaseHandler(this);
		 
        Promenade p1 = new Promenade("Caluire", 4);
        Promenade p2 = new Promenade("Lyon", 3);
       
        db.ajouter(p1);   
        db.ajouter(p2);       

        db.supprimer(p1);
        
        System.out.println("c");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
