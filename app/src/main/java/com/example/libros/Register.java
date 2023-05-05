package com.example.libros;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    private Button registro, login;
    private EditText nombre, apellido, usuario, pin;
    private DaoUsuario dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registro = findViewById(R.id.btnRegistrarRegister);
        login = findViewById(R.id.btnLogin);
        nombre = findViewById(R.id.textNombre);
        apellido = findViewById(R.id.txtApellido);
        usuario = findViewById(R.id.txtUsuarioRegistro);
        pin = findViewById(R.id.txtPinRegistro);
        dao = new DaoUsuario(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register.this,Login.class);
                startActivity(intent);
            }
        });

        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Usuario u = new Usuario();
                u.setNombre(nombre.getText().toString());
                u.setApellido(apellido.getText().toString());
                u.setUsuario(usuario.getText().toString());
                u.setPin(pin.getText().toString());
                if(!u.isNull()){
                    Toast.makeText(Register.this,"Error: campos vacios",Toast.LENGTH_SHORT).show();
                }else if(dao.insertUsuario(u)){
                    Toast.makeText(Register.this,"Registro exitoso",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Register.this,Login.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(Register.this,"Usuario ya registrado",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}