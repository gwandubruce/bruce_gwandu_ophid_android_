package zw.co.softwarezimbabwe.ophidclients;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditClient extends AppCompatActivity {
    private EditText et_ufirstname, et_ulastname, et_uaddress;
    private Button btn_back, btn_update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_client);
        final Database db = new Database(getBaseContext());
        final Client client = (Client)getIntent().getSerializableExtra("client");
        et_ufirstname = (EditText)findViewById(R.id.et_ufirstname);
        et_ulastname = (EditText)findViewById(R.id.et_ulastname);
        et_uaddress = (EditText)findViewById(R.id.et_uaddress);
        this.btn_back = (Button)findViewById(R.id.btn_back);
        btn_update = (Button)findViewById(R.id.btn_update);

        et_ufirstname.setText(client.getFirstname());
        et_ulastname.setText(client.getLastname());
        et_uaddress.setText(client.getAddress());
        et_ufirstname.setSelection(et_ufirstname.getText().length());
        et_ulastname.setSelection(et_ulastname.getText().length());
        et_uaddress.setSelection(et_uaddress.getText().length());

        this.btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditClient.this, ClientDetail.class);
                intent.putExtra("client", client);
                startActivity(intent);
            }
        });

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstname = et_ufirstname.getText().toString();
                String lastname = et_ulastname.getText().toString();
                String address = et_uaddress.getText().toString();

                client.setFirstname(firstname);
                client.setLastname(lastname);
                client.setAddress(address);


                if(db.Update(client)){

                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setTitle("System");
                    builder.setMessage("SUCCESSFULLY UPDATED");
                    builder.setCancelable(false);
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(EditClient.this, MainActivity.class);
                            startActivity(intent);
                        }
                    });
                    builder.create().show();
                }else{
                    Toast.makeText(EditClient.this, "FAILED!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}