package bdd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {
	//-- Base de données
	private static final int DATABASE_VERSION = 12;
    private static final String DATABASE_NAME = "PromenadeDB.db";
    private Context context;
	//-- Table promenade
	public static final String PROMENADE_TABLE_CREATE =
	    "CREATE TABLE promenade (" +
	    "id_prom INTEGER PRIMARY KEY, " +
	    "name TEXT NOT NULL," +
	    "description TEXT NULL," +
	    "altitude double null," +
	    "durationheure integer null," +
	    "durationminute integer null," +
	    "length double null," +
	    "difficulty double null," +
	    "image mediumblob null," + 
	    "way TEXT null);";
	
	public static final String PROMENADE_TABLE_DROP = "DROP TABLE IF EXISTS promenade;";

	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.context = context;
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
	
}
