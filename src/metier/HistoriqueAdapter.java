package metier;

import java.util.ArrayList;
import java.util.List;

import com.example.tppromenades.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class HistoriqueAdapter extends  ArrayAdapter<Historique>{

	List<Historique> listHistorique;
	Context  context;
	int resource;
	
	public HistoriqueAdapter(Context context, int resource, List<Historique> listeHistorique) {
        super(context, resource, listeHistorique);
        this.context = context;
        this.listHistorique = listeHistorique;
        this.resource = resource;
    }
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listHistorique.size();
	}

	@Override
	public Historique getItem(int arg0) {
		// TODO Auto-generated method stub
		return listHistorique.get(arg0);
	}

	
	
	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	private class ViewHolder {
		TextView lengthDuration;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
        View view = convertView;
        Historique p = listHistorique.get(position);

        if (convertView == null) {
            LayoutInflater viewInflater;
            viewInflater = LayoutInflater.from(context);
            view = viewInflater.inflate(R.layout.activity_historique_listview, null);

            holder = new ViewHolder();
            holder.lengthDuration = (TextView) view
                    .findViewById(R.id.lengthDuration);
            view.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.lengthDuration.setText(p.get_length()+ " kms - " + p.getDurationToString()+ " - Dénivelé : " + p.get_altitude());

        return view;
	}

}
