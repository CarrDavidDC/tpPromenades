package bdd;

import metier.Promenade;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper {
	//-- Base de données
	private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "PromenadeDB";
	
	//-- Table promenade
	public static final String PROMENADE_TABLE_CREATE =
	    "CREATE TABLE promenade (" +
	    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
	    "intitule TEXT NOT NULL, " +
	    "difficulte INTEGER);";
	public static final String PROMENADE_TABLE_DROP = "DROP TABLE IF EXISTS promenade;";

	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(PROMENADE_TABLE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(PROMENADE_TABLE_DROP);
		onCreate(db);
	}
		
		
	//-- Opérations CRUD
	public static final String TABLE_NAME = "promenade";
	public static final String ID = "id";
	public static final String INTITULE = "intitule";
	public static final String DIFFICULTE = "difficulte";
	public static final String[] COLUMNS = {ID,INTITULE,DIFFICULTE};
	
	public void ajouter(Promenade p) {
		Log.d("addBook", p.toString());
		SQLiteDatabase db = this.getWritableDatabase();
		//Création d'un ContentValues (fonctionne comme une HashMap)
		ContentValues values = new ContentValues();
		//on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
		values.put(ID, p.get_id());
		values.put(INTITULE, p.get_intitule());
		values.put(DIFFICULTE, p.get_difficulte());
		//on insère l'objet dans la BDD via le ContentValues
		db.insert(TABLE_NAME, null, values);
		db.close();
	}
	
	public Promenade selectionner(int id) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.query(TABLE_NAME, 				// nom de la table
				COLUMNS,  								// liste des colonnes
				ID + "= ?", 							// clause WHERE
				new String[] { String.valueOf(id) }, 	// récupère le paramètre
				null, 									// clause GROUP BY
				null, 									// clause HAVING
				null, 									// clause ORDER BY
				null);									// limite
	    if (c != null)
	        c.moveToFirst();
	 	    Promenade p = new Promenade();
	    p.set_id(Integer.parseInt(c.getString(0)));
	    p.set_intitule(c.getString(1));
	    p.set_difficulte(Integer.parseInt(c.getString(2)));
	    Log.d("selectionner("+id+")", p.toString());
	    return p;
	}

	public int modifier(int id, Promenade p) {
		SQLiteDatabase db = this.getWritableDatabase();
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
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,
        		ID + " = ?",
                new String[] { String.valueOf(p.get_id()) });
        db.close();
        Log.d("supprimer", p.toString());
 
    }
}
