package com.example.tppromenades;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;

import java.util.ArrayList;

public class CreateCourse {
	private String title;
	private String description;
	private ArrayList<Marker> marker;
	private ArrayList<LatLng> latLng;
	private PolygonOptions rectOptions;
	private GoogleMap map;
	
	public CreateCourse(String title, String description, GoogleMap laMap) {
		this.title = title;
		this.description = description;
		this.marker = new ArrayList<Marker>();
		this.latLng = new ArrayList<LatLng>();
		this.map = laMap;
		this.rectOptions = new PolygonOptions();
	}
	
	
	public void createCourse(){
		LatLng p1 = new LatLng(37.35, -122.0);
		LatLng p2 = new LatLng(37.45, -122.0);
		LatLng p3 = new LatLng(37.45, -122.2);
		LatLng p4 = new LatLng(37.35, -122.2);
		LatLng p5 = new LatLng(37.35, -122.0);
		latLng.add(p1);
		latLng.add(p2);
		latLng.add(p3);
		latLng.add(p4);
		latLng.add(p5);
		
		for(int i = 0; i < latLng.size(); i++)
		{
			Marker m = map.addMarker(new MarkerOptions()
							.position(latLng.get(i))
							.title("Etape n°"+i)
							.snippet("Description de l'étape n°"+i));
			marker.add(m);			
			rectOptions.add(latLng.get(i));
		}
	}
	
	
	
	
	
	
	public ArrayList<LatLng> getLatLng() {
		return latLng;
	}


	public void setLatLng(ArrayList<LatLng> latLng) {
		this.latLng = latLng;
	}


	public GoogleMap getMap() {
		return map;
	}


	public void setMap(GoogleMap map) {
		this.map = map;
	}


	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public ArrayList<Marker> getMarker() {
		return marker;
	}
	public void setMarker(ArrayList<Marker> marker) {
		this.marker = marker;
	}
	public PolygonOptions getRectOptions() {
		return rectOptions;
	}
	public void setRectOptions(PolygonOptions rectOptions) {
		this.rectOptions = rectOptions;
	}
	
	
	

}
