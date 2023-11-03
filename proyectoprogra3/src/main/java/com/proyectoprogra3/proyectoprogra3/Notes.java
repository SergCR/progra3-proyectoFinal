package com.proyectoprogra3.proyectoprogra3;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.comparator.BooleanComparator;
import org.springframework.web.servlet.FlashMap;

public class Notes {
    //************************************************************Variables************************************************************
    private String noteText; //El texto en si que lleva la nota
    private String noteBackgroundColor; //Variable para almacenar algun color para usar como fondo en la nota
    private String noteTitle; //Titulo de la nota
    private Boolean notePasswordEnabled; //Variable para almacenar si la nota requiere contraseña para ser abierta
    private String notePassword; //contraseña de la nota
    private String noteCategory; //Categoria customizable por el usuario para las notas
    private List<String> noteSharedWithListOfUsers; //Lista de usuarios con acceso a la nota especifica

    //************************************************************Contructors************************************************************
    public Notes(){
        this.noteSharedWithListOfUsers = new ArrayList<>();
        this.noteText = "";
        this.noteBackgroundColor = "#FFFFFF";
        this.noteTitle = "";
        this.notePasswordEnabled = false;
        this.notePassword = "";
        this.noteCategory = "";
    }

    public Notes(String noteText, String noteTitle){

        //Build list of Shared Users (List must be made of user objects)
        this.noteTitle = noteTitle;
        this.noteText = noteText;
        this.noteSharedWithListOfUsers = null;
        this.noteBackgroundColor = "#FFFFFF";
        this.notePasswordEnabled = false;
        this.notePassword = "";
        this.noteCategory = "";
    }

    public Notes(String noteText, String noteBackgroundColor, String noteTitle, Boolean notePasswordEnabled, String notePassword, String noteCategory, List<String> noteSharedWithListOfUsers){

        //Build list of Shared Users (List must be made of user objects)

        this.noteSharedWithListOfUsers = noteSharedWithListOfUsers;
        this.noteText = noteText;
        this.noteBackgroundColor = noteBackgroundColor;
        this.noteTitle = noteTitle;
        this.notePasswordEnabled = notePasswordEnabled;
        this.notePassword = notePassword;
        this.noteCategory = noteCategory;
    }

    //************************************************************Setters and Getters************************************************************
    //##################################################################Setters##################################################################
    public void setNoteText(String noteText){
        this.noteText = noteText;
    }

    public void setNoteBackgroundColor(String noteBackgroundColor){
        this.noteBackgroundColor = noteBackgroundColor;
    }
    
    public void setNoteTitle(String noteTitle){
        this.noteTitle = noteTitle;
    }

    public void setNotePasswordEnabled(Boolean notePasswordEnabled){
        this.notePasswordEnabled = notePasswordEnabled;
    }

    public void setNotePassword(String notePassword){
        this.notePassword = notePassword;
    }

    public void setNoteCategory(String noteCategory){
        this.noteCategory = noteCategory;
    }

    public void setNoteSharedWithListOfUsers(List<String> noteSharedWithListOfUsers){
        this.noteSharedWithListOfUsers = noteSharedWithListOfUsers;
    }
    //##################################################################Getters##################################################################
    public String getNoteText(){
        return noteText;
    }

    public String getNoteBackgroundColor(){
        return noteBackgroundColor;
    }
    
    public String getNoteTitle(){
        return noteTitle;
    }

    public Boolean getNotePasswordEnabled(){
        return notePasswordEnabled;
    }

    public String getNotePassword(){
        return notePassword;
    }

    public String getNoteCategory(){
        return noteCategory;
    }

    public List<String> getNoteSharedWithListOfUsers(){
        return noteSharedWithListOfUsers;
    }

    //************************************************************Class Funtions************************************************************

}
