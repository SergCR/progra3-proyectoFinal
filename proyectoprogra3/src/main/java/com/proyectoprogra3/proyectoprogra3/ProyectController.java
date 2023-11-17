package com.proyectoprogra3.proyectoprogra3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class ProyectController {

    @Autowired
    private LogicLayer logicService;

    //------------------------------------------------------------Usuarios------------------------------------------------------------
    @GetMapping("/getListaUsuarios") //check
    public List<User> getListaUsuarios(){
        return logicService.getListaUsuarios();
    }

    @GetMapping("/getUsuario") //check
    public User getListaUsuarios(String email){
        return logicService.getUsuario(email);
    }

    @PostMapping("/addUsuario") //check
    public String addUsuario(String nombre, String apellido, String email, String password){
        User miUser = new User(0, nombre, apellido, password, email, null, null, true);
        if (logicService.addUser(miUser)){
            return "Usuario creado con exito! : Nombre: "+ nombre +" | Apellido: "+ apellido +" | Email: "+ email;
        }else{
            return "Creacion de usuario fallida!";
        }
    }

    @PutMapping("/updateUsuario") //check
    public String updateUsuario(Integer userid, String nombre, String apellido, String email, String password){
        User theUser = new User(userid,nombre, apellido,password,email);
        if (logicService.updateUser(theUser)){
            return "Usuario actualizado con exito! : Nombre: "+ nombre +" | Apellido: "+ apellido +" | Email: "+ email;
        }else{
            return "Actualizacion de usuario fallida!";
        }
    }

    @DeleteMapping("/deleteUsuario") //check
    public String deleteUsuario(String email){
        User theUser = new User(email);
        if (logicService.deleteUser(theUser)){
            return "Usuario eliminado con exito! : Email: "+ email;
        }else{
            return "Error al eliminar usuario!";
        }
    }

    //------------------------------------------------------------Notas------------------------------------------------------------

    @PostMapping("/addNota") //check
    public String addNota(int userID, String textoNota, String tituloNota){
        Notes theNote = new Notes(userID, textoNota, tituloNota);
        if (logicService.addNote(theNote)){
            return "La nota fue agregada con exito";
        }else{
            return "Error al agregar nota!";
        }
    }

    @GetMapping("/getAllNotas") //check
    public List<Notes> getallNotes(){
        return logicService.getAllNotes();
    }

    @GetMapping("/getNotasDeUsuario") //check
    public List<Notes> getNotesForUser(String userEmail){
        return logicService.getNotesForUser(userEmail);
    }
    
    @PutMapping("/updateNota") //check
    public String updateNote(Integer noteID, String texto, String titulo){
        Notes theNote = new Notes();
        theNote.setNoteID(noteID);
        theNote.setNoteText(texto);
        theNote.setNoteTitle(titulo);
        if (logicService.updateNote(theNote)){
            return "Nota actualizada con exito!";
        }else{
            return "Actualizacion de Nota fallida!";
        }
    }

    @DeleteMapping("/deleteNota") //check
    public String deleteNote(Integer noteID){
        Notes theNote = new Notes();
        theNote.setNoteID(noteID);
        if (logicService.deleteNote(theNote)){
            return "Nota eliminada con exito!";
        }else{
            return "Error al eliminar la nota!";
        }
    }
    //------------------------------------------------------------Progra web------------------------------------------------------------
    @PutMapping("/setPasswordEnabled") //check
    public String setPasswordEnabled(Boolean enabled, Integer noteID, String password){
        Notes theNote = new Notes();
        theNote.setNotePasswordEnabled(enabled);
        theNote.setNoteID(noteID);
        theNote.setNotePassword(password);
        if (logicService.setPasswordEnabled(theNote)){
            if (enabled){
                return "Password habilitado con exito!";
            }else{
                return "Password removido con exito!";
            }
        }else{
            return "Error al habilitar password";
        }
    }

    @PutMapping("/setCategoriaNota") //check
    public String setNoteCategory(Integer noteID, String categoryName){
        Notes theNote = new Notes();
        theNote.setNoteID(noteID);
        theNote.setNoteCategory(categoryName);
        if (logicService.setNoteCategory(theNote)){
            return "Categoria actualizada con exito!";
        }else{
            return "Error al actualizar la categoria";
        }
    }

    @PutMapping("/setColorBackground")
    public String setBackgroundColor(Integer noteID, String hexCodigoColor){
        Notes theNote = new Notes();
        theNote.setNoteID(noteID);
        theNote.setNoteBackgroundColor("#"+hexCodigoColor);
        if (logicService.setBackgroundColor(theNote)){
            return "Color de background actualizado con exito!";
        }else{
            return "Error al actualizar el color de background";
        }
    }

    @PutMapping("/setEmailsParaNotaCompartida")
    public String setNoteSharedEmails(Integer noteID, String tipo, String email){
        Notes theNote = new Notes();
        theNote.setNoteID(noteID);
        if (logicService.setNoteSharedEmails(tipo, email, theNote)){
            return "Emails compartidos actualizados con exito!";
        }else{
            return "Error al actualizar emails compartidos";
        }
    }
}
