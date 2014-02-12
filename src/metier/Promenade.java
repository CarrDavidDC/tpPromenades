package metier;

import java.io.Serializable;
import java.sql.Blob;
import java.util.ArrayList;

import com.google.android.gms.maps.model.LatLng;

import android.graphics.Bitmap;
import bdd.TablePromenade;

public class Promenade implements Serializable {
    private int _gid;
    private String _name;
    private String _description;
    private double _altitude;
    private double _length;
    private int _durationHour;
    private int _durationMinute;
    private String _theme;
    private double _difficulty;
    private String _id;
    private String _project;
    private int nbCaractDescriptionMax = 100;
    private byte[] _image;
    private Bitmap _imageFin;
    private String _way;
    
    public String get_way() {
		return _way;
	}

	public void set_way(String _chemin) {
		this._way = _chemin;
	}

	public Promenade(String _name) {
		super();
		this._name = _name;
	}
    
    public Promenade() {
    	
    }
    
    
    
	public Promenade(String _name, double _length, int _durationHour, int _durationMinute,
			double _difficulty) {
		super();
		this._name = _name;
		this._length = _length;
		this._durationHour = _durationHour;
		this._durationMinute = _durationMinute;
		this._difficulty = _difficulty;
		this._way = null;
		this._image = null;
	}

	public Promenade(String _name, double _length, int _durationHour, int _durationMinute) {
		super();
		this._name = _name;
		this._length =  _length;
		this._durationHour = _durationHour;
		this._durationMinute = _durationMinute;
		this._way = null;
		this._image = null;
	}

	
	public Promenade(String _name, String description, double _length, int _durationHour, int _durationMinute, double _difficulty, byte[] data) {
		super();
		this._name = _name;
		this._description = description;
		this._length =  _length;
		this._durationHour = _durationHour;
		this._durationMinute = _durationMinute;
		this._difficulty = _difficulty;
		this._image = data;
		this._way = null;
		this._image = null;
	}
	
	public Promenade(String _name, String description, double _length, int _durationHour, int _durationMinute, double _difficulty) {
		super();
		this._name = _name;
		this._description = description;
		this._length =  _length;
		this._durationHour = _durationHour;
		this._durationMinute = _durationMinute;
		this._difficulty = _difficulty;
		this._image = null;
		this._way = null;
	}
	
	public Promenade(int _gid, String _name, double _length, int _durationHour, int _durationMinute,
			String _theme, double _difficulty, String _id, String _project) {
		super();
		this._gid = _gid;
		this._name = _name;
		this._length = _length;
		this._durationHour = _durationHour;
		this._durationMinute = _durationMinute;
		this._theme = _theme;
		this._difficulty = _difficulty;
		this._id = _id;
		this._project = _project;
		this._way = null;
		this._image = null;
	}
	
	
	
	/******************************************
	 * 
	 * 			GET & SET
	 * 	
	 * 
	 * ****************************************
	 */
	
	@Override
	public String toString() {
		return "Promenade [_gid=" + _gid + ", _name=" + _name + ", _length="
				+ _length + ", _duration=" + this._durationHour+"h"+this._durationMinute + ", _theme=" + _theme
				+ ", _difficulty=" + _difficulty + ", _id=" + _id
				+ ", _project=" + _project
				+ " _gps=" + _way + "]";
	}

	
	public String get_smallDescription()
	{
		if(_description.length() > nbCaractDescriptionMax)
			return _description.substring(0, nbCaractDescriptionMax);
		else
			return _description;
	}
	
	public String get_description() {
		return _description;
	}

	public void set_description(String _description) {
		this._description = _description;
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

	public double get_length() {
		return _length;
	}
	
	

	public double get_altitude() {
		return _altitude;
	}

	public void set_altitude(double altitude) {
		this._altitude = altitude;
	}

	public void set_length(double _length) {
		this._length = _length;
	}

	public int get_durationHour() {
		return _durationHour;
	}

	public void set_durationHour(int _duration) {
		this._durationHour = _duration;
	}
	
	public int get_durationMinute() {
		return _durationMinute;
	}

	public void set_durationMinute(int _duration) {
		this._durationMinute = _duration;
	}

	public String get_theme() {
		return _theme;
	}

	public void set_theme(String _theme) {
		this._theme = _theme;
	}

	public double get_difficulty() {
		return _difficulty;
	}

	public void set_difficulty(double _difficulty) {
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

	public byte[] get_image() {
		return _image;
	}

	public void set_image(byte[] _image) {
		this._image = _image;
	}

	public Bitmap get_imageFin() {
		return _imageFin;
	}

	public void set_imageFin(Bitmap _imageFin) {
		this._imageFin = _imageFin;
	}	
	
	
}