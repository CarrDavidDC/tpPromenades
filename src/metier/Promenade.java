package metier;

import java.io.Serializable;

public class Promenade implements Serializable {
    private int _gid;
    private String _name;
    private float _length;
    private String _duration;
    private String _theme;
    private String _difficulty;
    private String _id;
    private String _project;
    
    public Promenade(String _name) {
		super();
		this._name = _name;
	}
    
    public Promenade() {
    	
    }
    	
    
    
	public Promenade(String _name, double _length, String _duration) {
		super();
		this._name = _name;
		this._length = (float) _length;
		this._duration = _duration;
	}

	public Promenade(int _gid, String _name, float _length, String _duration,
			String _theme, String _difficulty, String _id, String _project) {
		super();
		this._gid = _gid;
		this._name = _name;
		this._length = _length;
		this._duration = _duration;
		this._theme = _theme;
		this._difficulty = _difficulty;
		this._id = _id;
		this._project = _project;
	}
	
	@Override
	public String toString() {
		return "Promenade [_gid=" + _gid + ", _name=" + _name + ", _length="
				+ _length + ", _duration=" + _duration + ", _theme=" + _theme
				+ ", _difficulty=" + _difficulty + ", _id=" + _id
				+ ", _project=" + _project + "]";
	}

	public int get_gid() {
		return _gid;
	}
	
	public void set_gid(int _gid) {
		this._gid = _gid;
	}

	public String get_name() {
		return _name;
	}

	public void set_name(String _name) {
		this._name = _name;
	}

	public float get_length() {
		return _length;
	}

	public void set_length(float _length) {
		this._length = _length;
	}

	public String get_duration() {
		return _duration;
	}

	public void set_duration(String _duration) {
		this._duration = _duration;
	}

	public String get_theme() {
		return _theme;
	}

	public void set_theme(String _theme) {
		this._theme = _theme;
	}

	public String get_difficulty() {
		return _difficulty;
	}

	public void set_difficulty(String _difficulty) {
		this._difficulty = _difficulty;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String get_project() {
		return _project;
	}

	public void set_project(String _project) {
		this._project = _project;
	}	
}