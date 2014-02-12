package bdd;

import java.util.ArrayList;

import reglage.ReglageSingleton;

import metier.Promenade;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class TablePromenade {
	private DatabaseHandler _db;
	
	//-- Opérations CRUD
	public static final String TABLE_NAME = "promenade";
	public static final String ID = "id_prom";
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String ALTITUDE = "altitude";
	public static final String DURATIONHOUR = "durationheure";
	public static final String DURATIONMINUTE = "durationminute";
	public static final String LENGTH = "length";
	public static final String DIFFICULTY = "difficulty";
	public static final String IMAGE = "image";
	public static final String WAY = "way";
	public static final String[] COLUMNS = {ID,NAME,DESCRIPTION,ALTITUDE,DURATIONHOUR,DURATIONMINUTE,LENGTH,DIFFICULTY,IMAGE,WAY};
	
	public TablePromenade(DatabaseHandler db, ArrayList<Promenade> promenades) {
		_db = db;
		this.supprimerTout();
		for(int i=0 ; i<promenades.size() ; i++) {
			ajouter(promenades.get(i));
		}
	}
	
	public void sauvegarderPromenades(ArrayList<Promenade> promenades) {
		for(int i=0 ; i<promenades.size() ; i++) {
			ajouter(promenades.get(i));
		}
	}
	
	public TablePromenade(DatabaseHandler db) {
		_db = db;
		this.supprimerTout();
	}
	
	public void ajouter(Promenade p) {
		System.out.println("AJOUT PROMENADE BD");
		//String c = null;
		//Boolean existeDeja = select(p);
		SQLiteDatabase db = _db.getWritableDatabase();
		//Création d'un ContentValues (fonctionne comme une HashMap)
		ContentValues values = new ContentValues();
		//on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
		values.put(ID, p.get_gid());
		values.put(NAME, p.get_name());
		values.put(DESCRIPTION, p.get_description());
		values.put(ALTITUDE, p.get_altitude());
		values.put(DURATIONHOUR, p.get_durationHour());
		values.put(DURATIONMINUTE, p.get_durationMinute());
		values.put(LENGTH, p.get_length());
		values.put(DIFFICULTY, p.get_difficulty());
		values.put(IMAGE, p.get_image());
		values.put(WAY, p.get_way());
		//on insère l'objet dans la BDD via le ContentValues
		db.insert(TABLE_NAME, null, values);
		db.close();
	}
	
	/*private Boolean select(Promenade p) {
		SQLiteDatabase db = _db.getReadableDatabase();
		Cursor c = db.query(TABLE_NAME, 				// nom de la table
				COLUMNS,  								// liste des colonnes
				null, 									// clause WHERE
				null, 									// récupère le paramètre
				null, 									// clause GROUP BY
				null, 									// clause HAVING
				ReglageSingleton.getInstance().getRequetePourTriRandonnee() // clause ORDER BY
				);	
		return true;
		return false;
	}*/

	public void supprimerTout() {
		SQLiteDatabase db = _db.getWritableDatabase();
        db.delete(TABLE_NAME,
        		null,
                null);
        db.close();
        Log.d("supprimer","suppression des enregistrements");
	}
	
	public ArrayList<Promenade> selectionnerTout(Context context) {
		SQLiteDatabase db = _db.getReadableDatabase();
		Cursor c = db.query(TABLE_NAME, 				// nom de la table
				COLUMNS,  								// liste des colonnes
				null, 									// clause WHERE
				null, 									// récupère le paramètre
				null, 									// clause GROUP BY
				null, 									// clause HAVING
				ReglageSingleton.getInstance().getRequetePourTriRandonnee() 									// clause ORDER BY
				);	
		// limite
		System.out.println("NOMBRE DE PROMENADES TROUVEES EN BASE : " + c.getCount());
		ArrayList<Promenade> listePromenade = new ArrayList<Promenade>();
	    while (c.moveToNext()) {
	    	Promenade p = new Promenade();
	    	p.set_gid(Integer.parseInt(c.getString(0)));
	    	p.set_name(c.getString(1));
	    	p.set_description(c.getString(2));
	    	p.set_altitude(c.getDouble(3));
	    	p.set_durationHour(c.getInt(4));
	    	p.set_durationMinute(c.getInt(5));
	    	p.set_length(c.getDouble(6));
	    	p.set_difficulty(c.getDouble(7));
	    	p.set_image(c.getBlob(8));
	    	p.set_way(c.getString(9));
	    /*	if(p.get_image() !=null)
	    		p.set_imageFin(BitmapFactory.decodeByteArray(p.get_image(), 0, p.get_image().length));
	    	else*/
	    		p.set_imageFin(null);
	    	System.out.println("Promenade SQLITE : " + p.toString());
	    	listePromenade.add(p);
	    }
	    return listePromenade;
    }
}
