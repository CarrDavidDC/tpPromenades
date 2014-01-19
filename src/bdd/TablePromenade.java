package bdd;

import java.util.ArrayList;

import metier.Promenade;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class TablePromenade {
	private DatabaseHandler _db;
	
	//-- Opérations CRUD
	public static final String TABLE_NAME = "promenade";
	public static final String ID = "id_prom";
	public static final String NAME = "name";
	public static final String[] COLUMNS = {ID,NAME};
	
	public TablePromenade(DatabaseHandler db, ArrayList<Promenade> promenades) {
		_db = db;
		this.supprimerTout();
		for(int i=0 ; i<promenades.size() ; i++) {
			ajouter(promenades.get(i));
		}
	}
	
	public void ajouter(Promenade p) {
		Log.d("ajout", p.toString());
		String c = null;
		SQLiteDatabase db = _db.getWritableDatabase();
		//Création d'un ContentValues (fonctionne comme une HashMap)
		ContentValues values = new ContentValues();
		//on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
		values.put(ID, c);
		values.put(NAME, p.get_name());
		//on insère l'objet dans la BDD via le ContentValues
		db.insert(TABLE_NAME, null, values);
		db.close();
	}
	
	public void supprimerTout() {
		SQLiteDatabase db = _db.getWritableDatabase();
        db.delete(TABLE_NAME,
        		null,
                null);
        db.close();
        Log.d("supprimer","suppression des enregistrements");
	}
	
	public void selectionnerTout() {
		SQLiteDatabase db = _db.getReadableDatabase();
		Cursor c = db.query(TABLE_NAME, 				// nom de la table
				COLUMNS,  								// liste des colonnes
				null, 									// clause WHERE
				null, 									// récupère le paramètre
				null, 									// clause GROUP BY
				null, 									// clause HAVING
				null, 									// clause ORDER BY
				null);									// limite
	    while (c.moveToNext()) {
	    	Promenade p = new Promenade();
	    	p.set_gid(Integer.parseInt(c.getString(0)));
	    	p.set_name(c.getString(1));
	    	System.out.println("Promenade SQLITE : " + p.toString());
	    }
	}
	
	/*public Promenade selectionner(int id) {
		SQLiteDatabase db = _db.getReadableDatabase();
		Cursor c = db.query(TABLE_NAME, 				// nom de la table
				COLUMNS,  								// liste des colonnes
				ID + "= ?", 							// clause WHERE
				new String[] { String.valueOf(id) }, 	// récupère le paramètre
				null, 									// clause GROUP BY
				null, 									// clause HAVING
				null, 									// clause ORDER BY
				null);									// limite
	    if (c != null) {
	        c.moveToFirst();
	 	Promenade p = new Promenade();
	    p.set_id(Integer.parseInt(c.getString(0)));
	    p.set_intitule(c.getString(1));
	    p.set_difficulte(Integer.parseInt(c.getString(2)));
	    Log.d("selectionner("+id+")", p.toString());
	    return p;
	}*/

	/*public int modifier(int id, Promenade p) {
		SQLiteDatabase db = _db.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(ID, p.get_id());
		values.put(INTITULE, p.get_intitule());
		values.put(DIFFICULTE, p.get_difficulte());
		int i = db.update(TABLE_NAME, 
				values, 
				ID + " = ?", 
				new String[] { String.valueOf(p.get_id()) });
		db.close();
		return i;
	}
	
	public void supprimer(Promenade p) {
        SQLiteDatabase db = _db.getWritableDatabase();
        db.delete(TABLE_NAME,
        		ID + " = ?",
                new String[] { String.valueOf(p.get_id()) });
        db.close();
        Log.d("supprimer", p.toString());
 
    }*/
}
