package com.vsga.validasilogin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText etUsername,etPassword,etEmail,etNamaLengkap,etAsalSekolah,etAlamat;
    Button btnSave;
    TextView textViewPassword;

    public static final String FILENAME = "login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Halaman Depan");

        etUsername = findViewById(R.id.editUsername);
        textViewPassword = findViewById(R.id.tvPassword);
        etPassword = findViewById(R.id.editPassword);
        etEmail = findViewById(R.id.editEmail);
        etNamaLengkap = findViewById(R.id.editNamaLengkap);
        etAsalSekolah = findViewById(R.id.editAsalSekolah);
        etAlamat = findViewById(R.id.editAlamat);
        btnSave = findViewById(R.id.action_save);

        btnSave.setVisibility(View.GONE);
        etUsername.setEnabled(false);
        etPassword.setVisibility(View.GONE);
        textViewPassword.setVisibility(View.GONE);
        etEmail.setEnabled(false);
        etNamaLengkap.setEnabled(false);
        etAlamat.setEnabled(false);

        bacaFileLogin();
    }

    void bacaFileLogin(){
        File sdcard = getFilesDir();
        File file = new File(sdcard, FILENAME);
        if (file.exists()){
            StringBuilder text = new StringBuilder();
            try {
                BufferedReader br =
                        new BufferedReader(new FileReader(file));
                String line = br.readLine();
                    while (line != null){
                        text.append(line);
                        line = br.readLine();
                    }
                    br.close();
            }catch (IOException e){
                System.out.println("Error" + e.getMessage());
            }
            String data = text.toString();
            String[] dataUser = data.split(";");
            bacaDataUser(dataUser[0]);
        }
    }

    void bacaDataUser(String fileName){
        File sdcard = getFilesDir();
        File file = new File(sdcard,fileName);
        if (file.exists()){
            StringBuilder text = new StringBuilder();
            try {
                BufferedReader br =
                        new BufferedReader(new FileReader(file));
                String line = br.readLine();
                while (line != null){
                    text.append(line);
                    line = br.readLine();
                }
                br.close();
            }catch (IOException e){
                System.out.println("Error" + e.getMessage());
            }
            String data = text.toString();
            String[] dataUser = data.split(";");

            etUsername.setText(dataUser[0]);
            etEmail.setText(dataUser[2]);
            etNamaLengkap.setText(dataUser[3]);
            etAsalSekolah.setText(dataUser[4]);
            etAlamat.setText(dataUser[5]);
        }else{
            Toast.makeText(this, "User tidak ditemukan", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

        @Override
    public boolean onOptionsItemSelected (MenuItem item){
        switch (item.getItemId()){
            case R.id.action_logout:
            tampilkanDialogKonfirmasiLogout();
            break;
        }
        return super.onOptionsItemSelected(item);
    }
    void hapusFile(){
        File file = new File(getFilesDir(),FILENAME);
        if (file.exists()){
            file.delete();
        }
    }

    void tampilkanDialogKonfirmasiLogout(){
        new AlertDialog.Builder(this)
                .setTitle("Logout")
                .setMessage("Apakah Anda yakin ingin Logout?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        hapusFile();
                        Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .setNegativeButton(android.R.string.no,null).show();
    }
}