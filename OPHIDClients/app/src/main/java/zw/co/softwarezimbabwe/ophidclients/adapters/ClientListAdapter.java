package zw.co.softwarezimbabwe.ophidclients.adapters;

import android.content.Context;
import androidx.annotation.NonNull;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import zw.co.softwarezimbabwe.ophidclients.Client;
import zw.co.softwarezimbabwe.ophidclients.R;

import java.util.List;

public class ClientListAdapter extends ArrayAdapter<Client> {

    private  Context context;
    private List<Client> clients;

    public ClientListAdapter(Context context, List<Client> clients){
        super(context, R.layout.client_list, clients);
        this.context = context;
        this.clients = clients;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view  = layoutInflater.inflate(R.layout.client_list, parent, false);
        TextView tv_name = (TextView)view.findViewById(R.id.tv_name);
        TextView tv_address = (TextView)view.findViewById(R.id.tv_address);
        tv_name.setText("Name: " + clients.get(position).getFirstname() + " " + clients.get(position).getLastname());
        tv_address.setText("Date of Birth: " + clients.get(position).getAddress());

        return view;
    }
}