package metier;

import java.text.DecimalFormat;
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

public class PromenadeAdapter extends  ArrayAdapter<Promenade>{

	List<Promenade> promenade;
	Context  context;
	int resource;
	
	public PromenadeAdapter(Context context, int resource, List<Promenade> promenades) {
        super(context, resource, promenades);
        this.context = context;
        this.promenade = promenades;
        this.resource = resource;
    }
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return promenade.size();
	}

	@Override
	public Promenade getItem(int arg0) {
		// TODO Auto-generated method stub
		return promenade.get(arg0);
	}

	
	
	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	private class ViewHolder {
		TextView name;
		TextView description;
		TextView length;
		TextView duration;
		TextView denivele;
		ImageView img;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
        View view = convertView;
        Promenade p = promenade.get(position);

        if (convertView == null) {
            LayoutInflater viewInflater;
            viewInflater = LayoutInflater.from(context);
            view = viewInflater.inflate(R.layout.activity_accueil_listview, null);

            holder = new ViewHolder();
            holder.description = (TextView) view.findViewById(R.id.description);
            holder.length = (TextView) view
                    .findViewById(R.id.tvDistance);
            holder.duration = (TextView) view
                    .findViewById(R.id.tvDuree);
            holder.denivele = (TextView) view
                    .findViewById(R.id.tvDenivele);
            holder.name = (TextView) view
                    .findViewById(R.id.name);
            holder.img = (ImageView)view.findViewById(R.id.imgPromenade);
            view.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(1);
        df.setDecimalSeparatorAlwaysShown(true);
        holder.length.setText(p.get_length_arrondi()+ " kms");
        holder.duration.setText(p.getDurationToString());
        holder.denivele.setText(String.valueOf(p.get_altitude()) + "m");
        
        holder.name.setText(p.get_name().toUpperCase());
        holder.description.setText(p.get_description());
      /*  if(p.get_image() != null)
        {
        	Bitmap img = BitmapFactory.decodeByteArray(p.get_image(), 0, p.get_image().length);
			holder.img.setImageBitmap(img);
			holder.img.setI
        }*/
        if(p.get_length_arrondi() > 10)
        {
        	holder.img.setImageResource(R.drawable.img_difficile);
        }else if(p.get_length_arrondi() > 5)
        {
        	holder.img.setImageResource(R.drawable.img_moyen);
        }else{
        	holder.img.setImageResource(R.drawable.img_facile);
        }
        return view;
	}

	private int getImageId(Context context, String imageName) {
	    return context.getResources().getIdentifier("drawable/" + imageName, null, context.getPackageName());
	}	
}
