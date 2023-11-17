package com.proyectoprogra3.proyectoprogra3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class LogicLayer {
    @Autowired
    private DataAccessLayer databaseService; //inyecta el DataAccessLayer.java
    @Autowired
    public LogicLayer(DataAccessLayer databaseService) {
        this.databaseService = databaseService;
    }

    //------------------------------------------------------------Usuarios------------------------------------------------------------
    public List<User> getListaUsuarios(){
        return databaseService.queryListAllUsers();
    }

    public User getUsuario(String searchEmail){
        return databaseService.querySpecificUser(searchEmail);
    }

    public boolean addUser(User theUser){
        return databaseService.addUser(theUser);
    }

    public boolean updateUser(User theUser){
        return databaseService.updateUser(theUser);
    }

    public boolean deleteUser(User theUser){
        return databaseService.deleteUser(theUser);
    }

    //------------------------------------------------------------Notas------------------------------------------------------------
    public boolean addNote(Notes theNote){
        return databaseService.addNote(theNote);
    }

    public List<Notes> getAllNotes(){
        return databaseService.queryListAllNotes();
    }

    public List<Notes> getNotesForUser(String userEmail){

        User theUser = this.getUsuario(userEmail);
        return databaseService.queryNotesForUser(theUser);
    }

    public Boolean updateNote(Notes theNote){
        return databaseService.updateNote(theNote);
    }

    public Boolean deleteNote(Notes theNote){
        return databaseService.deleteNote(theNote);
    }

    public Boolean setPasswordEnabled(Notes theNote){
        return databaseService.setPasswordEnabled(theNote);
    }

    public Boolean setNoteCategory(Notes theNote){
        return databaseService.setNoteCategory(theNote);
    }

    public Boolean setBackgroundColor(Notes theNote){
        return databaseService.setBackgroundColor(theNote);
    }

    public Boolean setNoteSharedEmails(String tipo, String emailToWork, Notes theNote){
        String dbEmails = databaseService.getEmailsCompatidos(theNote).toLowerCase();
        emailToWork = emailToWork.toLowerCase().strip();
        switch (tipo) {
            case "agregar":
                if (!dbEmails.toLowerCase().contains(emailToWork)){
                    dbEmails = dbEmails +","+emailToWork;
                }
                return databaseService.setNoteSharedEmails(theNote.getNoteID(), dbEmails);
            case "remover":
                if (dbEmails.toLowerCase().contains(emailToWork)){
                    dbEmails = dbEmails.replace(","+emailToWork, "");
                }
                return databaseService.setNoteSharedEmails(theNote.getNoteID(), dbEmails);
            default:
                return false;
        }
    }

    public List<User> buildUsersListFromEmailsString(String emails){
        List<User> listaUsers = new ArrayList<>();
        String cleanString = "";
        if (emails == null || emails.contentEquals(cleanString)){
            return listaUsers;
        }else{
            //Divide la lista de emails y los acomoda en un Array
            String[] usersTempArray = emails.toString().split(",");
            //Convierte el array en una lista iterable
            List<String> usersTempList = Arrays.asList(usersTempArray);
            //Itera la lista de emails
            for (String usersIterator : usersTempList) {
                //Busca la informacion de cada usuario y lo agrega a la lista de Users que es una variable tipo List de la clase Notes
                listaUsers.add(databaseService.querySpecificUser(usersIterator));
            }
        }
        return listaUsers;

    }
}
