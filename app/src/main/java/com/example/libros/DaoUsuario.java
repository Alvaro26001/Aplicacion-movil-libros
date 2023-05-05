package com.example.libros;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import java.util.ArrayList;

public class DaoUsuario {
    private Context c;
    private Usuario u;
    private ArrayList<Usuario> lista;
    private SQLiteDatabase sql;
    private String db = "BDUsuarios";
    private String tabla =
            "create table if not exists usuario(id integer primary key autoincrement," +
                    " nombre text," +
                    " apellido text," +
                    " usuario text," +
                    " pin text )";

    public DaoUsuario(Context c){
        this.c=c;
        sql = c.openOrCreateDatabase(db,c.MODE_PRIVATE,null);
        sql.execSQL(tabla);
        u = new Usuario();
    }

    public boolean insertUsuario(Usuario u){
        if(buscar(u.getUsuario())==0){
            ContentValues cv = new ContentValues();
            cv.put("nombre",u.getNombre());
            cv.put("apellido",u.getApellido());
            cv.put("usuario",u.getUsuario());
            cv.put("pin",u.getPin());
            return (sql.insert("usuario",null,cv)>0);
        }else{
            return false;
        }
    }

    public int buscar(String u){
        int x=0;
        lista = selectUsuraios();
        for (Usuario us:lista){
            if(us.getUsuario().equals(u)){
                x++;
            }
        }

        return x;
    }

    public ArrayList<Usuario> selectUsuraios(){
        ArrayList<Usuario> lista = new ArrayList<Usuario>();
        lista.clear();
        Cursor cr = sql.rawQuery("select * from usuario",null);

        if(cr!=null && cr.moveToFirst()){
            do {
                Usuario u = new Usuario();
                u.setId(cr.getInt(0));
                u.setNombre(cr.getString(1));
                u.setApellido(cr.getString(2));
                u.setUsuario(cr.getString(3));
                u.setPin(cr.getString(4));
                lista.add(u);
            }while(cr.moveToNext());
        }
        return lista;


    }

    public  int login(String u, String b){

        int a = 0;
        Cursor cr = sql.rawQuery("select * from usuario",null);
        if(cr!=null && cr.moveToFirst()){
            do {
               if(cr.getString(3).equals(u) && cr.getString(4).equals(b)){
                    a++;
               }
            }while(cr.moveToNext());
        }
        return a;
    }

    public Usuario getUsuario(String u, String p){
        lista = selectUsuraios();
        for(Usuario us:lista){
            if(us.getUsuario().equals(u) && us.getPin().equals(p)){
                return us;
            }
        }

        return null;
    }

    public Usuario getUsuarioById(int id){
        lista = selectUsuraios();
        for(Usuario us:lista){
            if(us.getId()==id){
                return us;
            }
        }

        return null;
    }


}
