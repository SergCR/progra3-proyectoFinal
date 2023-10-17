package com.proyectoprogra3.proyectoprogra3;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.comparator.BooleanComparator;

public class Notes {
    //************************************************************Variables************************************************************
    private String noteText;
    private String noteBackgroundColor;
    private String noteTitle;
    private Boolean notePasswordEnabled;
    private String notePassword;
    private String noteCategory;
    private List<String> noteSharedWithListOfUsers;

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
