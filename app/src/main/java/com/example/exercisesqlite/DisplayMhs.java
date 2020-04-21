package com.example.exercisesqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class DisplayMhs extends AppCompatActivity {
    int from_Where_I_Am_Coming = 0;
    private com.example.exercisesqlite.DBHelper mydb ;
    EditText nama ;
    EditText phone;
    EditText email;
    EditText alamat;
    int id_To_Update = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_mhs);

        mydb = new com.example.exercisesqlite.DBHelper(this);


        nama = (EditText) findViewById(R.id.editUN);
        email = (EditText) findViewById(R.id.editEmail);
        phone = (EditText) findViewById(R.id.editPass);
        alamat = (EditText) findViewById(R.id.editAlamat);

        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            int Value = extras.getInt("id");
            if(Value>0){
                //means this is the view part not the add contact part.
                Cursor rs = mydb.getData(Value);
                id_To_Update = Value;
                rs.moveToFirst();
                String nam = rs.getString(rs.getColumnIndex(DBHelper.MHS_COLUMN_NAMA));
                String phon = rs.getString(rs.getColumnIndex(DBHelper.MHS_COLUMN_PHONE));
                String mail = rs.getString(rs.getColumnIndex(DBHelper.MHS_COLUMN_EMAIL));
                String almt = rs.getString(rs.getColumnIndex(DBHelper.MHS_COLUMN_ALAMAT));
                if (!rs.isClosed()){
                    rs.close();
                }
                Button b = (Button)findViewById(R.id.btnok);
                b.setVisibility(View.INVISIBLE);
                email.setText((CharSequence)mail);
                email.setFocusable(false);
                email.setClickable(false);
                nama.setText((CharSequence)nam);
                nama.setFocusable(false);
                nama.setClickable(false);
                phone.setText((CharSequence)phon);
                phone.setFocusable(false);
                phone.setClickable(false);
                alamat.setText((CharSequence)almt);
                alamat.setFocusable(false);
                alamat.setClickable(false);
            }
        }
    }


    public void run(View view)
    {
        if (nama.getText().toString().equals("")||
                phone.getText().toString().equals("")||
                email.getText().toString().equals("")||
                alamat.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(),
                    "Data Harus Diisi Semua !", Toast.LENGTH_LONG).show();
        }else {
            mydb.insertContact(nama.getText().toString(), phone.getText().toString(), email.getText().toString(),alamat.getText().toString());
            Toast.makeText(getApplicationContext(),
                    "Insert data Berhasil !", Toast.LENGTH_LONG).show();

            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
        }

    }
}