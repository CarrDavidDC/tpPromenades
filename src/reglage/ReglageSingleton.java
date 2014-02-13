package reglage;

public class ReglageSingleton {
	
	private String requetePourTriRandonnee;

/** Constructeur privé */
private ReglageSingleton()
{requetePourTriRandonnee = null;}

/** Instance unique pré-initialisée */
private static ReglageSingleton INSTANCE = new ReglageSingleton();

/** Point d'accès pour l'instance unique du singleton */
public static ReglageSingleton getInstance()
{	return INSTANCE;
}

public String getRequetePourTriRandonnee() {
return requetePourTriRandonnee;
}

public void setRequetePourTriRandonnee(String requetePourTriRandonnee) {
this.requetePourTriRandonnee = requetePourTriRandonnee;
}
	
}
