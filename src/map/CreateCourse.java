package map;


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
		LatLng p1 = new LatLng( 45.7519428536153 , 4.72537563578831);
		latLng.add(p1);
		 p1 = new LatLng(45.7519808061218 , 4.72512017176793 );
		latLng.add(p1);
		 p1 = new LatLng(45.7519203541077 , 4.72472591636721 );
		latLng.add(p1);
		 p1 = new LatLng( 45.7518833405931 ,4.72448453068098 );
		latLng.add(p1);
		 p1 = new LatLng(45.7519237986165 , 4.72435144090819 );
		latLng.add(p1);
		 p1 = new LatLng(45.7520177176648 ,4.72418757956624 );
		latLng.add(p1);
		 p1 = new LatLng( 45.7521028450188 , 4.7240390556947);
		latLng.add(p1);
		 p1 = new LatLng(45.7522635950553, 4.72389467106685 );
		latLng.add(p1);
		 p1 = new LatLng(45.7523542985933 , 4.7238132004961 );
		latLng.add(p1);
		 p1 = new LatLng( 45.7524418361362 , 4.72380551638891);
		latLng.add(p1);
		 p1 = new LatLng(45.752537938314 , 4.72375155422695 );
		latLng.add(p1);
		 p1 = new LatLng( 45.7526864657938 ,4.72378383355602);
		latLng.add(p1);
		 p1 = new LatLng( 45.7527601565658 , 4.72379984942586);
		 latLng.add(p1);
		 p1 = new LatLng(45.7529289124187 , 4.72365224941611 );
		latLng.add(p1);
		 p1 = new LatLng( 45.7530466518335 , 4.72343128533353);
		latLng.add(p1);
		 p1 = new LatLng(45.7531134805987 , 4.72330586750944 );
		latLng.add(p1);
		 p1 = new LatLng( 45.7531316818291 , 4.72304742258064);
		latLng.add(p1);
		 p1 = new LatLng(45.753103635858 , 4.72266511384098 );
		latLng.add(p1);
		 p1 = new LatLng(45.7530886649356 , 4.72246104934153 );
		latLng.add(p1);
		 p1 = new LatLng(45.7531117031239 , 4.72230174319497 );
		latLng.add(p1);
		 p1 = new LatLng(45.7531202235586 , 4.72209742428795 );
		latLng.add(p1);
		 p1 = new LatLng(45.7531808951582 , 4.72179516511732 );
		latLng.add(p1);
		 p1 = new LatLng(45.7533150739774 , 4.72112669548178 );
		latLng.add(p1);
		 p1 = new LatLng(45.753385244106 , 4.72086283002039 );
		latLng.add(p1);
		 p1 = new LatLng(45.7535167101275 , 4.72064530879641 );
		latLng.add(p1);
		 p1 = new LatLng(45.7535095964404 , 4.7202011896738 );
		latLng.add(p1);
		 p1 = new LatLng(45.7534272335698 , 4.71975118488826);
		latLng.add(p1);
		 p1 = new LatLng(45.7535092107231 ,4.71970119966104 );
		latLng.add(p1);
		 p1 = new LatLng(45.7535810280769 ,4.71970924221593);
		latLng.add(p1);
		 p1 = new LatLng(45.7540180201023,4.71971646874377 );
		latLng.add(p1);
		p1 = new LatLng( 45.7541409517317,4.71970743321436);
		latLng.add(p1);
		p1 = new LatLng(45.7542564817686,4.71970650799941);
		
		
		for(int i = 0; i < latLng.size(); i++)
		{
			if(i%10==0)
			{
				Marker m = map.addMarker(new MarkerOptions()
								.position(latLng.get(i))
								.title("Etape n°"+(i+1))
								.snippet("Description de l'étape n°"+(i+1)));
				marker.add(m);			
			}
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
