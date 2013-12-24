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
		//Cr�ation d'une instance de ma classe LivresBDD
        PromenadeDAO bdd = new PromenadeDAO(this);
 
        //Cr�ation d'un livre
        Promenade p = new Promenade("Caluire", 4);
 
        //On ouvre la base de donn�es pour �crire dedans
        bdd.open();
        //On ins�re le livre que l'on vient de cr�er
        bdd.ajouter(p);
 
        //Pour v�rifier que l'on a bien cr�� notre livre dans la BDD
        //on extrait le livre de la BDD gr�ce au titre du livre que l'on a cr�� pr�c�demment
        Promenade pbdd = bdd.selectionner(p.get_intitule());
        //Si un livre est retourn� (donc si le livre � bien �t� ajout� � la BDD)
        if(pbdd != null){
        	//On affiche les infos du livre dans un Toast
        	Toast.makeText(this, pbdd.toString(), Toast.LENGTH_LONG).show();
        	//On modifie le titre du livre
        	pbdd.set_intitule("Caluire modifi�e");
        	//Puis on met � jour la BDD
        	bdd.modifier(pbdd.get_id(), pbdd);
        }
 
        //On extrait le livre de la BDD gr�ce au nouveau titre
        pbdd = bdd.selectionner("Caluire modifi�e");
        //S'il existe un livre poss�dant ce titre dans la BDD
        if(pbdd != null){
	        //On affiche les nouvelle info du livre pour v�rifi� que le titre du livre a bien �t� mis � jour
	        Toast.makeText(this, pbdd.toString(), Toast.LENGTH_LONG).show();
	        //on supprime le livre de la BDD gr�ce � son ID
	        bdd.supprimer(pbdd.get_id());
        }
 
        //On essait d'extraire de nouveau le livre de la BDD toujours gr�ce � son nouveau titre
        pbdd = bdd.selectionner("Caluire modifi�e");
        //Si aucun livre n'est retourn�
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
