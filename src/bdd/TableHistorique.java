package bdd;

import java.util.ArrayList;

import reglage.ReglageSingleton;

import metier.Historique;
import metier.Promenade;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.Toast;

public class TableHistorique {
	private DatabaseHandler _db;
	
	//-- Opérations CRUD
	public static final String TABLE_NAME = "historique";
	public static final String ID = "id_historique";
	public static final String IDPROMENADE = "id_prom";
	public static final String ALTITUDE = "altitude";
	public static final String DURATIONHOUR = "durationheure";
	public static final String DURATIONMINUTE = "durationminute";
	public static final String LENGTH = "length";
	public static final String WAY = "way";
	public static final String[] COLUMNS = {ID,IDPROMENADE,ALTITUDE,DURATIONHOUR,DURATIONMINUTE,LENGTH,WAY};
	
	public TableHistorique(DatabaseHandler db, ArrayList<Historique> historiques) {
		_db = db;
		this.supprimerTout();
		for(int i=0 ; i<historiques.size() ; i++) {
			ajouter(historiques.get(i));
		}
	}
	
	public TableHistorique(DatabaseHandler db) {
		_db = db;
	}
	
	public void ajouter(Historique p) {
		Log.d("ajout", p.toString());
		String c = null;
		SQLiteDatabase db = _db.getWritableDatabase();
		//Création d'un ContentValues (fonctionne comme une HashMap)
		ContentValues values = new ContentValues();
		//on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
		values.put(ID, c);
		values.put(IDPROMENADE, p.get_idPromenade());
		values.put(ALTITUDE, p.get_altitude());
		values.put(DURATIONHOUR, p.get_durationHour());
		values.put(DURATIONMINUTE, p.get_durationMinute());
		values.put(LENGTH, p.get_length());
		values.put(WAY, p.get_way());
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
	
	public ArrayList<Historique> selectionnerSelonIdRandonnee(Context context, int idPromenade) {
		SQLiteDatabase db = _db.getReadableDatabase();
		Cursor c = db.query(TABLE_NAME, 				// nom de la table
				COLUMNS,  								// liste des colonnes
				IDPROMENADE +"=?", 									// clause WHERE
				new String[] { String.valueOf(idPromenade) }, 									// récupère le paramètre
				null, 									// clause GROUP BY
				null, 									// clause HAVING
				null 									// clause ORDER BY
				);	

		// limite
		ArrayList<Historique> listeHistorique = new ArrayList<Historique>();

    	Toast.makeText(context,"Dans le cursor : "+idPromenade,Toast.LENGTH_SHORT).show();
	    while (c.moveToNext()) {
			
	    	Historique p = new Historique();
	    	p.set_id(Integer.parseInt(c.getString(0)));
	    	p.set_idPromenade(c.getInt(1));
	    	p.set_altitude(c.getDouble(2));
	    	p.set_durationHour(c.getInt(3));
	    	p.set_durationMinute(c.getInt(4));
	    	p.set_length(c.getDouble(5));
	    	p.set_way(c.getString(6));
	    	listeHistorique.add(p);
	    }
	    return listeHistorique;
	   }
	
	public ArrayList<Historique> selectionner(Context context) {
		SQLiteDatabase db = _db.getReadableDatabase();
		Cursor c = db.query(TABLE_NAME, 				// nom de la table
				COLUMNS,  								// liste des colonnes
				null, 									// clause WHERE
				null, 									// récupère le paramètre
				null, 									// clause GROUP BY
				null, 									// clause HAVING
				null 									// clause ORDER BY
				);	
		// limite
		ArrayList<Historique> listeHistorique = new ArrayList<Historique>();
	    while (c.moveToNext()) {
	    	Historique p = new Historique();
	    	p.set_id(Integer.parseInt(c.getString(0)));
	    	p.set_idPromenade(c.getInt(1));
	    	p.set_altitude(c.getDouble(2));
	    	p.set_durationHour(c.getInt(3));
	    	p.set_durationMinute(c.getInt(4));
	    	p.set_length(c.getDouble(5));
	    	p.set_way(c.getString(6));
	    	listeHistorique.add(p);
	    }
	    return listeHistorique;
	   }
}
