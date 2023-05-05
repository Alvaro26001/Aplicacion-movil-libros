package com.example.libros;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    private Button registro, ingresar;
    private EditText usuario, pin;
    private DaoUsuario dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        registro = findViewById(R.id.btnRegistrar);
        ingresar = findViewById(R.id.btnIngresar);
        usuario = findViewById(R.id.textUsuario);
        pin = findViewById(R.id.textPin);
        dao = new DaoUsuario(this);

        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this,Register.class);
                startActivity(intent);
            }
        });

        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String u = usuario.getText().toString();
                String p = pin.getText().toString();

                if(u.equals("") && p.equals("")){
                    Toast.makeText(Login.this,"Error: campos vacios",Toast.LENGTH_SHORT).show();
                }else if(dao.login(u,p)==1){

                    Usuario us = dao.getUsuario(u,p);
                    Toast.makeText(Login.this,"Datos correctos",Toast.LENGTH_SHORT).show();
                    Intent intent =new Intent(Login.this, Inicio.class);
                    intent.putExtra("id", us.getId());
                    startActivity(intent);
                }else{
                    Toast.makeText(Login.this,"Error: usuario y/o pin incorrectos",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}