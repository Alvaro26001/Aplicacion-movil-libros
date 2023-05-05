package com.example.libros;

public class Usuario {

    private int id;
    private String nombre, apellido, usuario,pin;

    public Usuario() {
    }

    public Usuario( String nombre, String apellido, String usuario, String pin) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.usuario = usuario;
        this.pin = pin;

    }

    public boolean isNull(){

        if(nombre.equals("") && apellido.equals("") && usuario.equals("") && String.valueOf(pin).equals("")){
            return false;
        }else{
            return true;
        }
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", pin=" + pin +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", usuario='" + usuario + '\'' +
                '}';
    }
}
