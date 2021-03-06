package zw.co.softwarezimbabwe.ophidclients;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ClientDetail extends AppCompatActivity {
    private TextView tv_dname, tv_daddress;
    private Button btn_back, btn_edit, btn_delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_detail);

        final Client client = (Client) getIntent().getSerializableExtra("client");
        tv_dname = (TextView)findViewById(R.id.tv_dname);
        tv_daddress = (TextView)findViewById(R.id.tv_daddress);
        this.btn_back = (Button)findViewById(R.id.btn_uback);
        btn_edit = (Button)findViewById(R.id.btn_edit);
        btn_delete = (Button)findViewById(R.id.btn_delete);
        tv_dname.setText(client.getFirstname() + " " + client.getLastname());
        tv_daddress.setText(client.getAddress());

        this.btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClientDetail.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClientDetail.this, EditClient.class);
                intent.putExtra("client", client);
                startActivity(intent);
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Confirmation");
                builder.setMessage("Are you sure you want to delete this record?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Database database = new Database(getBaseContext());
                        if(database.Delete(client.getId())){
                            Intent intent = new Intent(ClientDetail.this, MainActivity.class);
                            startActivity(intent);
                        }else{
                            AlertDialog.Builder builder1 = new AlertDialog.Builder(getBaseContext());
                            builder1.setMessage("Fail");
                            builder1.setCancelable(false);
                            builder1.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                            builder1.create().show();
                        }
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.create().show();
            }
        });
    }
}