package metier;

import android.graphics.Bitmap;

public class Historique {
	private int _id;
	private int _idPromenade;
    private double _altitude;
    private double _length;
    private int _durationHour;
    private int _durationMinute;
    private String _way;
    
    public Historique()
    {
       _way=null;
    }
    
    public String getDurationToString()
    {
    	String durationToString = "";
    	if(_durationHour == 0)
    	{
    		durationToString = _durationMinute+" min";
    	}else{
    		durationToString = _durationHour + " h ";
    		if(_durationMinute < 10)
    		{
    			durationToString += "0"+_durationMinute;
    		}else{
    			durationToString += String.valueOf(_durationMinute);
    		}
    	}
    	return durationToString;
    }
    
	public String get_way() {
		return _way;
	}



	public void set_way(String _way) {
		this._way = _way;
	}



	public int get_id() {
		return _id;
	}
	public int get_idPromenade() {
		return _idPromenade;
	}
	public double get_altitude() {
		return _altitude;
	}
	public double get_length() {
		return _length;
	}
	public int get_durationHour() {
		return _durationHour;
	}
	public int get_durationMinute() {
		return _durationMinute;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	public void set_idPromenade(int _idPromenade) {
		this._idPromenade = _idPromenade;
	}
	public void set_altitude(double _altitude) {
		this._altitude = _altitude;
	}
	public void set_length(double _length) {
		this._length = _length;
	}
	public void set_durationHour(int _durationHour) {
		this._durationHour = _durationHour;
	}
	public void set_durationMinute(int _durationMinute) {
		this._durationMinute = _durationMinute;
	}
    
    
}
