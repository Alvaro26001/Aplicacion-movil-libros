package com.example.libros;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Inicio extends AppCompatActivity {

    private Button guardar, editar, eliminar,listar, salir;
    private EditText claveLibro, nomAutor, nomLibro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        guardar = findViewById(R.id.btnGuardar);
        editar = findViewById(R.id.btnEditar);
        eliminar = findViewById(R.id.btnEliminar);
        listar = findViewById(R.id.btnListar);
        salir = findViewById(R.id.btnSalir);
        nomAutor = findViewById(R.id.txtNomAutor);
        nomLibro = findViewById(R.id.txtNomLibro);
        claveLibro = findViewById(R.id.txtClaveLibro);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InicioSQLite inicioSQLite = new InicioSQLite(getApplicationContext(), "Biblioteca",null,1);
                SQLiteDatabase db = inicioSQLite.getWritableDatabase();

                if(claveLibro.getText().toString().trim().equals("")){
                    claveLibro.setError("Error: Debe rellenar este campo");
                } else if(nomAutor.getText().toString().trim().equals("")){
                    nomAutor.setError("Error: Debe rellenar este campo");
                }else if(nomLibro.getText().toString().trim().equals("")){
                    nomLibro.setError("Error: Debe rellenar este campo");
                }else{

                    int  clav =  Integer.parseInt(claveLibro.getText().toString());
                    int   clav2=0;
                    Cursor fila = db.rawQuery("select * from libro where claveLibro="+clav,null);
                    if(fila.moveToFirst()){
                        clav2 = fila.getInt(0);
                    }
                    if(clav2==clav){
                        Toast.makeText(Inicio.this,"El Libro ya Existe",Toast.LENGTH_SHORT).show();
                        db.close();
                    } else{
                        String nomA = nomAutor.getText().toString();
                        String nomL = nomLibro.getText().toString();
                        ContentValues datos = new ContentValues();
                        datos.put("claveLibro",clav);
                        datos.put("nombreLibro",nomL);
                        datos.put("nombreAutor",nomA);

                        db.insert("libro",null,datos);
                        db.close();
                        nomLibro.setText("");
                        nomAutor.setText("");
                        claveLibro.setText("");

                    }

                }





            }
        });

        listar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InicioSQLite inicioSQLite = new InicioSQLite(getApplicationContext(), "Biblioteca",null,1);
                SQLiteDatabase db = inicioSQLite.getWritableDatabase();

                if(claveLibro.getText().toString().trim().equals("")){
                    claveLibro.setError("Error: Debe rellenar este campo");
                }else{
                    int  clav =  Integer.parseInt(claveLibro.getText().toString());

                    Cursor fila = db.rawQuery("select * from libro where claveLibro="+clav,null);
                    if(fila.moveToFirst()){
                        nomLibro.setText(fila.getString(1));
                        nomAutor.setText(fila.getString(2));
                    }else{
                        Toast.makeText(Inicio.this,"El Libro no existe",Toast.LENGTH_SHORT).show();
                    }
                    db.close();
                }

            }
        });


        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InicioSQLite inicioSQLite = new InicioSQLite(getApplicationContext(), "Biblioteca",null,1);
                SQLiteDatabase db = inicioSQLite.getWritableDatabase();

                if(claveLibro.getText().toString().trim().equals("")){
                    claveLibro.setError("Error: Debe rellenar este campo");
                } else if(nomAutor.getText().toString().trim().equals("")){
                    nomAutor.setError("Error: Debe rellenar este campo");
                }else if(nomLibro.getText().toString().trim().equals("")){
                    nomLibro.setError("Error: Debe rellenar este campo");
                }else {
                    int clav = Integer.parseInt(claveLibro.getText().toString());

                    Cursor fila = db.rawQuery("select * from libro where claveLibro=" + clav, null);

                    String nomL = nomLibro.getText().toString();
                    String nomA = nomAutor.getText().toString();


                    ContentValues datos = new ContentValues();
                    datos.put("nombreLibro", nomL);
                    datos.put("nombreAutor", nomA);

                    if (fila.moveToFirst()) {
                        db.update("libro", datos, "claveLibro=" + clav, null);
                        Toast.makeText(Inicio.this, "Libro Actualizado", Toast.LENGTH_SHORT).show();
                        nomLibro.setText("");
                        nomAutor.setText("");
                        claveLibro.setText("");
                    } else {
                        Toast.makeText(Inicio.this, "El libro no existe", Toast.LENGTH_SHORT).show();
                    }
                    db.close();

                }
            }
        });

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InicioSQLite inicioSQLite = new InicioSQLite(getApplicationContext(), "Biblioteca",null,1);
                SQLiteDatabase db = inicioSQLite.getWritableDatabase();

                if(claveLibro.getText().toString().trim().equals("")){
                   claveLibro.setText("Error: Debe rellenar este campo");
                }else {

                    int clav = Integer.parseInt(claveLibro.getText().toString());

                    Cursor fila = db.rawQuery("select * from libro where claveLibro=" + clav, null);

                    if (fila.moveToFirst()) {

                        db.execSQL("DELETE FROM libro  WHERE claveLibro=" + clav);
                        Toast.makeText(Inicio.this, "Libro Eliminado", Toast.LENGTH_SHORT).show();
                        nomLibro.setText("");
                        nomAutor.setText("");
                        claveLibro.setText("");
                    } else {
                        Toast.makeText(Inicio.this, "El libro no existe", Toast.LENGTH_SHORT).show();
                    }
                    db.close();
                }
            }
        });

        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Inicio.this,Login.class);
                startActivity(intent);
            }
        });

    }
}