package Parser;

import java.util.ArrayList;

import com.google.android.gms.maps.model.LatLng;

public class Parser {
	
	
	public static ArrayList<LatLng> stringToArrayListLatLng(String chaine)
	{
		ArrayList<LatLng> listeLatLng = new ArrayList<LatLng>();
		/*
		 * 
		 * Structure : lat long,
		 * 
		 */
		String[] chaineWithoutComa = chaine.split(",");
		
		for(int i = 0; i < chaineWithoutComa.length;i++)
		{
			String[] chaineLatLng = chaineWithoutComa[i].split(" ");
			LatLng latLng = new LatLng(Double.valueOf(chaineLatLng[0]), Double.valueOf(chaineLatLng[1]));
			listeLatLng.add(latLng);
		}
		
		return listeLatLng;
	}
	
	public static String arrayListLatLngToString(ArrayList<LatLng> listeLatLng)
	{
		String chaineBdd = "";
		for(int i=0; i < listeLatLng.size();i++)
		{
			LatLng l = listeLatLng.get(i);
			chaineBdd+= l.latitude + " " +l.longitude+",";
		}
		chaineBdd += "";
		return chaineBdd;	
		
	}

}
