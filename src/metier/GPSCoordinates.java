package metier;

public class GPSCoordinates {
	private float _longitude;
	private float _latitude;
	
	public GPSCoordinates(float longitude, float latitude) {
		_longitude = longitude;
		_latitude = latitude;
	}

	public float get_longitude() {
		return _longitude;
	}

	public void set_longitude(float _longitude) {
		this._longitude = _longitude;
	}

	public float get_latitude() {
		return _latitude;
	}

	public void set_latitude(float _latitude) {
		this._latitude = _latitude;
	}
}
