package com.proyectoprogra3.proyectoprogra3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.time.LocalDate;

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

    public boolean updateUser(Integer userid, String nombre, String apellido, String email, String password){
        return databaseService.updateUser(userid, nombre, apellido, email, password);
    }

    public boolean deleteUser(String email){
        return databaseService.deleteUser(email);
    }

    //------------------------------------------------------------Notas------------------------------------------------------------
    public boolean addNote(int userID, String textoNota, String tituloNota){
        return databaseService.addNote(userID,textoNota,tituloNota);
    }

    public List<Notes> getAllNotes(){
        List<Map<String, Object>> myList = databaseService.queryListAllNotes();
        List<Notes> listOfNotes = new ArrayList<>();
        Notes thisNote = null;

        if (myList != null){
            for (Map<String, Object> map : myList) {
                thisNote = new Notes();
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    String key = entry.getKey();
                    Object value = entry.getValue();
                    
                    switch (key) {
                        case "note_id":
                            thisNote.setNoteID((Integer) value);
                            break;
                        case "user_id":
                            thisNote.setUserID((Integer) value);
                            break;
                        case "text":
                            thisNote.setNoteText((String) value);
                            break;
                        case "title":
                            thisNote.setNoteTitle((String) value);
                            break;
                        case "bg_color":
                            thisNote.setNoteBackgroundColor((String) value);
                            break;
                        case "pw_enabled":
                            thisNote.setNotePasswordEnabled((Boolean) value);
                            break;
                        case "note_password":
                            thisNote.setNotePassword((String) value);
                            break;
                        case "note_category":
                            thisNote.setNoteCategory((String) value);
                            break;
                        case "note_shared_with_emails":
                            //Divide la lista de emails y los acomoda en un Array
                            String[] usersTempArray = value.toString().split(",");
                            //Convierte el array en una lista iterable
                            List<String> usersTempList = Arrays.asList(usersTempArray);
                            //Itera la lista de emails
                            for (String usersIterator : usersTempList) {
                                //Busca la informacion de cada usuario y lo agrega a la lista de Users que es una variable tipo List de la clase Notes
                                thisNote.addSharedUser(this.getUsuario(usersIterator));
                            }
                            break;
                        case "created_date":
                            thisNote.setCreatedDate((LocalDate) LocalDate.parse(value.toString()));
                            break;
                        case "modified_date":
                            thisNote.setModifiedDate((LocalDate) LocalDate.parse(value.toString()));
                            break;
                        default:
                            System.out.println(key + " - Not found in switch!");
                            break;
                    }
                }
                listOfNotes.add(thisNote);
            }
        }else{
            System.out.println("My list is empty!");
        }
        return listOfNotes;
    }

    public List<Notes> getNotesForUser(String userEmail){
        System.out.println(userEmail);
        Integer userID = this.getUsuario(userEmail).getUsuarioID();
        List<Map<String, Object>> myList = databaseService.queryNotesForUser(userID, userEmail);
        List<Notes> listOfNotes = new ArrayList<>();
        Notes thisNote = null;

        if (myList != null){
            for (Map<String, Object> map : myList) {
                thisNote = new Notes();
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    String key = entry.getKey();
                    Object value = entry.getValue();
                    
                    switch (key) {
                        case "note_id":
                            thisNote.setNoteID((Integer) value);
                            break;
                        case "user_id":
                            thisNote.setUserID((Integer) value);
                            break;
                        case "text":
                            thisNote.setNoteText((String) value);
                            break;
                        case "title":
                            thisNote.setNoteTitle((String) value);
                            break;
                        case "bg_color":
                            thisNote.setNoteBackgroundColor((String) value);
                            break;
                        case "pw_enabled":
                            thisNote.setNotePasswordEnabled((Boolean) value);
                            break;
                        case "note_password":
                            thisNote.setNotePassword((String) value);
                            break;
                        case "note_category":
                            thisNote.setNoteCategory((String) value);
                            break;
                        case "note_shared_with_emails":
                            //Divide la lista de emails y los acomoda en un Array
                            String[] usersTempArray = value.toString().split(",");
                            //Convierte el array en una lista iterable
                            List<String> usersTempList = Arrays.asList(usersTempArray);
                            //Itera la lista de emails
                            for (String usersIterator : usersTempList) {
                                //Busca la informacion de cada usuario y lo agrega a la lista de Users que es una variable tipo List de la clase Notes
                                thisNote.addSharedUser(this.getUsuario(usersIterator));
                            }
                            break;
                        case "created_date":
                            thisNote.setCreatedDate((LocalDate) LocalDate.parse(value.toString()));
                            break;
                        case "modified_date":
                            thisNote.setModifiedDate((LocalDate) LocalDate.parse(value.toString()));
                            break;
                        default:
                            System.out.println(key + " - Not found in switch!");
                            break;
                    }
                }
                listOfNotes.add(thisNote);
            }
        }else{
            System.out.println("My list is empty!");
        }
        return listOfNotes;
    }

    public Boolean updateNote(Integer noteID, String texto, String titulo){
        return databaseService.updateNote(noteID, texto, titulo);
    }

    public Boolean deleteNote(Integer noteID){
        return databaseService.deleteNote(noteID);
    }

    public Boolean setPasswordEnabled(Boolean enabled, Integer noteID, String password){
        return databaseService.setPasswordEnabled(enabled, noteID, password);
    }

    public Boolean setNoteCategory(Integer noteID, String categoryName){
        return databaseService.setNoteCategory(noteID, categoryName);
    }

    public Boolean setBackgroundColor(Integer noteID, String hexColorCode){
        return databaseService.setBackgroundColor(noteID, hexColorCode);
    }

    public Boolean setNoteSharedEmails(Integer noteID, String tipo, String email){
        String dbEmails = databaseService.getEmailsCompatidos(noteID).toLowerCase();
        email = email.toLowerCase().strip();
        switch (tipo) {
            case "agregar":
                if (dbEmails.toLowerCase().contains(email)){
                    
                }else{
                    dbEmails = dbEmails +","+email;
                }
                return databaseService.setNoteSharedEmails(noteID, dbEmails);
            case "remover":
                if (dbEmails.toLowerCase().contains(email)){
                    dbEmails = dbEmails.replace(","+email, "");
                }
                return databaseService.setNoteSharedEmails(noteID, dbEmails);
            default:
                return false;
        }
    }
}
