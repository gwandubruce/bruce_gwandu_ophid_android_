package zw.co.softwarezimbabwe.ophidclients;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;


import android.content.Intent;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import zw.co.softwarezimbabwe.ophidclients.adapters.ClientListAdapter;

import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    Database database;
//    EditText et_firstname, et_lastname, et_address;
    Button btn_add, btn_view;
    ListView lv_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = new Database(this);

        btn_add = (Button)findViewById(R.id.btn_add);
        lv_list = (ListView)findViewById(R.id.lv_list);

        ClientListAdapter clientListAdapter = new ClientListAdapter(this, database.DisplayAll());
        clientListAdapter.sort(new Comparator<Client>() {
            @Override
            public int compare(Client lhs, Client rhs) {
                return lhs.getLastname().compareTo(rhs.getLastname());
            }
        });
        lv_list.setAdapter(clientListAdapter);
        lv_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Client client = database.DisplayAll().get(position);
                Intent intent = new Intent(MainActivity.this, ClientDetail.class);
                intent.putExtra("client", client);
                startActivity(intent);
            }
        });
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddClient.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}