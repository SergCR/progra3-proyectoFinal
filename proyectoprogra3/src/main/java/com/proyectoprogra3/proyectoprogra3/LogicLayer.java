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
        List<Map<String, Object>> myList = databaseService.queryListAllUsers();
        List<User> usersList = new ArrayList<>();
        Integer userID=0;
        String nombre="", apellido="", password ="", email = "";
        LocalDate createDate= LocalDate.parse("2020-01-01"), updatedDate = LocalDate.parse("2020-01-01");
        Boolean activeStatus = false;

        if (myList != null){
            for (Map<String, Object> map : myList) {
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    String key = entry.getKey();
                    Object value = entry.getValue();
                    
                    switch (key) {
                        case "user_id":
                            userID = (Integer) value;
                            break;
                        case "nombre":
                            nombre = (String) value;
                            break;
                        case "apellido":
                            apellido = (String) value;
                            break;
                        case "password":
                            password = (String) value;
                            break;
                        case "email":
                            email = (String) value;
                            break;
                        case "created_date":
                            createDate = (LocalDate) LocalDate.parse(value.toString());
                            break;
                        case "modified_date":
                            updatedDate = (LocalDate) LocalDate.parse(value.toString());
                            break;
                        case "active":
                            activeStatus = (Boolean) value;
                            break;
                        default:
                            System.out.println(key + " - Not found in switch!");
                            break;
                    }
                }
                User thisUser = new User(userID, nombre, apellido, password, email, createDate, updatedDate, activeStatus);
                usersList.add(thisUser);
                //System.out.println("UserID: "+ userID +" Nombre: "+ nombre + "Apellido: "+apellido+" Created: "+createDate+" Modified: "+updatedDate+" Active: "+activeStatus);
            }
        }else{
            System.out.println("My list is empty!");
        }
        return usersList;
    }

    public User getUsuario(String searchEmail){
        List<Map<String, Object>> myList = databaseService.querySpecificUser(searchEmail);
        User thisUser = null;
        Integer userID=0;
        String nombre="", apellido="", password ="", email = "";
        LocalDate createDate= LocalDate.parse("2020-01-01"), updatedDate = LocalDate.parse("2020-01-01");
        Boolean activeStatus = false;

        if (myList != null){
            for (Map<String, Object> map : myList) {
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    String key = entry.getKey();
                    Object value = entry.getValue();
                    
                    switch (key) {
                        case "user_id":
                            userID = (Integer) value;
                            break;
                        case "nombre":
                            nombre = (String) value;
                            break;
                        case "apellido":
                            apellido = (String) value;
                            break;
                        case "password":
                            password = (String) value;
                            break;
                        case "email":
                            email = (String) value;
                            break;
                        case "created_date":
                            createDate = (LocalDate) LocalDate.parse(value.toString());
                            break;
                        case "modified_date":
                            updatedDate = (LocalDate) LocalDate.parse(value.toString());
                            break;
                        case "active":
                            activeStatus = (Boolean) value;
                            break;
                        default:
                            System.out.println(key + " - Not found in switch!");
                            break;
                    }
                }
                thisUser = new User(userID, nombre, apellido, password, email, createDate, updatedDate, activeStatus);
                //System.out.println("UserID: "+ userID +" Nombre: "+ nombre + "Apellido: "+apellido+" Created: "+createDate+" Modified: "+updatedDate+" Active: "+activeStatus);
            }
        }else{
            System.out.println("My list is empty!");
        }

        return thisUser;
    }

    public boolean addUser(String nombre, String apellido, String email, String password){
        return databaseService.addUser(nombre, apellido, email, password);
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
}
