package com.example.tppromenades;

import metier.Promenade;
import bdd.PromenadeDAO;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//Création d'une instance de ma classe LivresBDD
        PromenadeDAO bdd = new PromenadeDAO(this);
 
        //Création d'un livre
        Promenade p = new Promenade("Caluire", 4);
 
        //On ouvre la base de données pour écrire dedans
        bdd.open();
        //On insère le livre que l'on vient de créer
        bdd.ajouter(p);
 
        //Pour vérifier que l'on a bien créé notre livre dans la BDD
        //on extrait le livre de la BDD grâce au titre du livre que l'on a créé précédemment
        Promenade pbdd = bdd.selectionner(p.get_intitule());
        //Si un livre est retourné (donc si le livre à bien été ajouté à la BDD)
        if(pbdd != null){
        	//On affiche les infos du livre dans un Toast
        	Toast.makeText(this, pbdd.toString(), Toast.LENGTH_LONG).show();
        	//On modifie le titre du livre
        	pbdd.set_intitule("Caluire modifiée");
        	//Puis on met à jour la BDD
        	bdd.modifier(pbdd.get_id(), pbdd);
        }
 
        //On extrait le livre de la BDD grâce au nouveau titre
        pbdd = bdd.selectionner("Caluire modifiée");
        //S'il existe un livre possédant ce titre dans la BDD
        if(pbdd != null){
	        //On affiche les nouvelle info du livre pour vérifié que le titre du livre a bien été mis à jour
	        Toast.makeText(this, pbdd.toString(), Toast.LENGTH_LONG).show();
	        //on supprime le livre de la BDD grâce à son ID
	        bdd.supprimer(pbdd.get_id());
        }
 
        //On essait d'extraire de nouveau le livre de la BDD toujours grâce à son nouveau titre
        pbdd = bdd.selectionner("Caluire modifiée");
        //Si aucun livre n'est retourné
        if(pbdd == null){
        	//On affiche un message indiquant que le livre n'existe pas dans la BDD
        	Toast.makeText(this, "Ce livre n'existe pas dans la BDD", Toast.LENGTH_LONG).show();
        }
        //Si le livre existe (mais normalement il ne devrait pas)
        else{
        	//on affiche un message indiquant que le livre existe dans la BDD
        	Toast.makeText(this, "Ce livre existe dans la BDD", Toast.LENGTH_LONG).show();
        }

        bdd.close();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
