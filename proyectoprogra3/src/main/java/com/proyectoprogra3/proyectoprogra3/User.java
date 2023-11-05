package com.proyectoprogra3.proyectoprogra3;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class User {
    //************************************************************Variables************************************************************
    private int usuarioID;
    private String nombre;
    private String apellido;
    private String password;
    private String email;
    private LocalDate createdDate;
    private LocalDate modifiedDate;
    private boolean activeStatus;

    private List<Notes> listaNotas = new ArrayList<Notes>();

    //************************************************************Contructors************************************************************
    public User() {
    }

    public User(int usuarioID, String nombre, String apellido, String password, String email, LocalDate createdDate, LocalDate modifiedDate, boolean activeStatus) {
        this.usuarioID = usuarioID;
        this.nombre = nombre;
        this.apellido = apellido;
        this.password = password;
        this.email = email;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.activeStatus = activeStatus;
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

    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public void setModifiedDate(LocalDate modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public void setActiveStatus(boolean activeStatus) {
        this.activeStatus = activeStatus;
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

    public String getEmail() {
        return email;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public LocalDate getModifiedDate() {
        return modifiedDate;
    }

    public boolean getActiveStatus() {
        return activeStatus;
    }

    public List<Notes> getListaNotas() {
        return listaNotas;
    }
    
    //************************************************************Class Funtions************************************************************

    // public void addNota(String noteText, String noteTitle){
    //     Notes laNote = new Notes(noteText, noteTitle);
    //     this.listaNotas.add(laNote);
    // }

    // public void updateNota(String noteText, String noteTitle, int index){
    //     Notes newNote = new Notes(noteText, noteTitle);
    //     this.listaNotas.set(index, newNote);
    // }

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
