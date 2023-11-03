package com.proyectoprogra3.proyectoprogra3;

import java.util.ArrayList;
import java.util.List;

public class User {
    //************************************************************Variables************************************************************
    private int usuarioID;
    private String nombre;
    private String apellido;
    private String password;
    private List<Notes> listaNotas = new ArrayList<Notes>();

    //************************************************************Contructors************************************************************
    public User() {
    }

    public User(int usuarioID, String nombre, String apellido, String password) {
        this.usuarioID = usuarioID;
        this.nombre = nombre;
        this.apellido = apellido;
        this.password = password;
    }

    //************************************************************Setters and Getters************************************************************
    //##################################################################Setters##################################################################
    public void setUsuarioID(int usuarioID) {
        this.usuarioID = usuarioID;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setListaNotas(List<Notes> listaNotas) {
        this.listaNotas = listaNotas;
    }

    //##################################################################Getters##################################################################
    public int getUsuarioID() {
        return usuarioID;
    }
    
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getPassword() {
        return password;
    }
    
    public List<Notes> getListaNotas() {
        return listaNotas;
    }
    //************************************************************Class Funtions************************************************************
    public void genNotas(int numUser){
        Notes miNota5 = new Notes("Texto de nota 0 Usuario"+numUser, "Titulo de nota 0 Usuario"+numUser);
        Notes miNota0 = new Notes("Texto de nota 1 Usuario"+numUser, "Titulo de nota 1 Usuario"+numUser);
        Notes miNota1 = new Notes("Texto de nota 2 Usuario"+numUser, "Titulo de nota 2 Usuario"+numUser);
        Notes miNota2 = new Notes("Texto de nota 3 Usuario"+numUser, "Titulo de nota 3 Usuario"+numUser);
        Notes miNota3 = new Notes("Texto de nota 4 Usuario"+numUser, "Titulo de nota 4 Usuario"+numUser);
        Notes miNota4 = new Notes("Texto de nota 5 Usuario"+numUser, "Titulo de nota 5 Usuario"+numUser);

        this.listaNotas.add(miNota0);
        this.listaNotas.add(miNota1);
        this.listaNotas.add(miNota2);
        this.listaNotas.add(miNota3);
        this.listaNotas.add(miNota4);
        this.listaNotas.add(miNota5);
    }

    public void addNota(String noteText, String noteTitle){
        Notes laNote = new Notes(noteText, noteTitle);
        this.listaNotas.add(laNote);
    }

    public void updateNota(String noteText, String noteTitle, int index){
        Notes newNote = new Notes(noteText, noteTitle);
        this.listaNotas.set(index, newNote);
    }

    public void deleteNota(int index){
        this.listaNotas.remove(index);
    }

    public List<Notes> searchNotas(String textToSearch){
        String texto;
        String titulo;
        List<Notes> notasEncontradas = new ArrayList<Notes>();

        for(Notes NotasIterador : listaNotas){
            texto = NotasIterador.getNoteText().toLowerCase();
            titulo = NotasIterador.getNoteTitle().toLowerCase();
            
            if(texto.contains(textToSearch) || titulo.contains(textToSearch)){
                notasEncontradas.add(NotasIterador);
            }
        }

        return notasEncontradas;
    }
}
