package bdd;

public class SingletonConnection {

	private static SingletonConnection mInstance = null;
	private DatabaseHandler db;
	
	private SingletonConnection(){
		
	}
	
	 public static SingletonConnection getInstance(){
		 if(mInstance == null)
		 {
			 mInstance = new SingletonConnection();
		 }
		 return mInstance;
	 }
	  
	 public DatabaseHandler getDatabaseHandler(){
		 return this.db;
	 }
	  
	 public void setDatabaseHandler(DatabaseHandler value){
		 db = value;
	 }
}
