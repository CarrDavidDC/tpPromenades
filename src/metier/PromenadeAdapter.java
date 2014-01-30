package metier;

import java.util.List;

import com.example.tppromenades.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

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
		TextView length;
		TextView duration;
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
            holder.duration = (TextView) view.findViewById(R.id.duration);
            holder.length = (TextView) view
                    .findViewById(R.id.length);
            holder.name = (TextView) view
                    .findViewById(R.id.name);
            view.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.duration.setText("Dur�e : " + p.get_duration());
        holder.length.setText("Distance : " + p.get_length());
        holder.name.setText(p.get_name());
        return view;
	}

}
