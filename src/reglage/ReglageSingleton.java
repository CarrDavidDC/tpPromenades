package reglage;

public class ReglageSingleton {
	
	private String requetePourTriRandonnee;

/** Constructeur priv� */
private ReglageSingleton()
{requetePourTriRandonnee = null;}

/** Instance unique pr�-initialis�e */
private static ReglageSingleton INSTANCE = new ReglageSingleton();

/** Point d'acc�s pour l'instance unique du singleton */
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
