package metier;

public class Promenade {
    private int _id;
    private String _intitule;
    private int _difficulte;
    
    public Promenade(String intitule, int difficulte) {
    	_intitule = intitule;
    	_difficulte = difficulte;
    }

	public Promenade() {
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String get_intitule() {
		return _intitule;
	}

	public void set_intitule(String _intitule) {
		this._intitule = _intitule;
	}

	public int get_difficulte() {
		return _difficulte;
	}

	public void set_difficulte(int _difficulte) {
		this._difficulte = _difficulte;
	}
	
	public String toString() {
		return "Promenade :  [ id = " + _id + " | intitulé = " + _intitule + " | difficulté = " + _difficulte + "]";
	}
}