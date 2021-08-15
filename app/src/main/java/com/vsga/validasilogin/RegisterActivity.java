package com.vsga.validasilogin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

public class RegisterActivity extends AppCompatActivity {

    EditText etUsername,etPassword,etEmail,etNamaLengkap,etAsalSekolah,etAlamat;
    Button btnSimpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getSupportActionBar().setTitle("Register");

        etUsername = findViewById(R.id.editUsername);
        etPassword = findViewById(R.id.editPassword);
        etEmail = findViewById(R.id.editEmail);
        etNamaLengkap = findViewById(R.id.editNamaLengkap);
        etAsalSekolah = findViewById(R.id.editAsalSekolah);
        etAlamat = findViewById(R.id.editAlamat);
        btnSimpan = findViewById(R.id.action_save);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValidation()){
                    simpanFileData();
                }else{
                    Toast.makeText(RegisterActivity.this, "Mohon lengkapi seluruh data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    boolean isValidation(){
        if (
                etUsername.getText().toString().equals("") ||
                etPassword.getText().toString().equals("") ||
                etEmail.getText().toString().equals("") ||
                etNamaLengkap.getText().toString().equals("") ||
                etAsalSekolah.getText().toString().equals("") ||
                etAlamat.getText().toString().equals("")) {
        return false;
    }else{
        return true;
        }
    }

    void simpanFileData(){
        String isiFile =
                etUsername.getText().toString() + ";" +
                etPassword.getText().toString() + ";" +
                etEmail.getText().toString() + ";" +
                etNamaLengkap.getText().toString() + ";" +
                etAsalSekolah.getText().toString() + ";" +
                etAlamat.getText().toString() ;
        File file = new File(getFilesDir(),etUsername.getText().toString());

        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file,false);
            outputStream.write(isiFile.getBytes());
            outputStream.flush();
            outputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        Toast.makeText(this, "Register Berhasil", Toast.LENGTH_SHORT).show();
        onBackPressed();
    }

}