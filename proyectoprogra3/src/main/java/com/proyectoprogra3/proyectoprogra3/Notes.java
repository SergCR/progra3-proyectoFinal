package com.proyectoprogra3.proyectoprogra3;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Notes {
    // Variables
    private String noteText;
    private String noteBackgroundColor;
    private String noteTitle;
    private Boolean notePasswordEnabled;
    private String notePassword;
    private String noteCategory;
    private List<String> noteSharedWithListOfUsers;

    //Contructors
    public Notes(){
        this.noteSharedWithListOfUsers = new ArrayList<>();
        this.noteText = "";
        this.noteBackgroundColor = "#FFFFFF";
        this.noteTitle = "";
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

    //Setters and Getters

    
}
