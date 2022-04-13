package zw.co.softwarezimbabwe.ophidclients;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddClient extends AppCompatActivity {

    Database database;
    EditText et_regfirstname, et_reglastname, et_regaddress;
    Button btn_regsave, btn_regback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_client);

        database = new Database(this);

        et_regfirstname = (EditText)findViewById(R.id.et_firstname);
        et_reglastname = (EditText)findViewById(R.id.et_lastname);
        et_regaddress = (EditText)findViewById(R.id.et_address);
        btn_regsave = (Button)findViewById(R.id.btn_regsave);
        btn_regback = (Button)findViewById(R.id.btn_regback);

        btn_regsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstname = et_regfirstname.getText().toString();
                String lastname = et_reglastname.getText().toString();
                String address = et_regaddress.getText().toString();

                if(!firstname.equals("") || !lastname.equals("") || !address.equals("")){

                    Client client = new Client();
                    client.setFirstname(firstname);
                    client.setLastname(lastname);
                    client.setAddress(address);

                    if (database.InsertData(client)) {
                        Toast.makeText(AddClient.this, "Successfully Inserted Data", Toast.LENGTH_SHORT).show();
                        et_regfirstname.setText("");
                        et_reglastname.setText("");
                        et_regaddress.setText("");
                    }
                }else{
                    Toast.makeText(AddClient.this, "Please complete the required field!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_regback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddClient.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}