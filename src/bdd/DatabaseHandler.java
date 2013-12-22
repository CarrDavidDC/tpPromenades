package bdd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {
	
	public static final String PROMENADE_KEY = "id";
	public static final String PROMENADE_INTITULE = "intitule";
	public static final String PROMENADE_DIFFICULTE = "difficulte";
	public static final String PROMENADE_TABLE_NAME = "promenade";
	public static final String PROMENADE_TABLE_CREATE =
	    "CREATE TABLE " + PROMENADE_TABLE_NAME + " (" +
	    		PROMENADE_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
	    		PROMENADE_INTITULE + " TEXT, " +
	    		PROMENADE_DIFFICULTE + " INTEGER);";
	public static final String PROMENADE_TABLE_DROP = "DROP TABLE IF EXISTS " + PROMENADE_TABLE_NAME + ";";

	public DatabaseHandler(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(PROMENADE_TABLE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL(PROMENADE_TABLE_DROP);
		onCreate(db);
	}

}
