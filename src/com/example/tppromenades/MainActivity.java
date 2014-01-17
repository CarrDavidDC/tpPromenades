package com.example.tppromenades;

import java.util.ArrayList;

import bdd.DatabaseHandler;
import bdd.TablePromenade;
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
        ArrayList<Promenade> promenades = new ArrayList<Promenade>();
        Promenade p1 = new Promenade("Caluire");
        Promenade p2 = new Promenade("Lyon");
        promenades.add(p1);
        promenades.add(p2);
        
        TablePromenade tp = new TablePromenade(db,promenades);
        tp.selectionnerTout();
        tp.supprimerTout();
        tp.selectionnerTout();
        System.out.println("AAAAAA");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
