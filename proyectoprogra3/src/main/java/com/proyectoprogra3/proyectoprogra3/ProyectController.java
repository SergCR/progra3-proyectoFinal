package com.proyectoprogra3.proyectoprogra3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
public class ProyectController {
    private List<User> listaUsuarios = new ArrayList<>();
    //private LogicLayer miLogicLayer = new LogicLayer();

    @Autowired
    private LogicLayer logicService;

    // public void genUsers(){
    //     User usuario0 = new User(0, "Pedro", "Perez", "contrasena0");
    //     User usuario1 = new User(1,"Pablo", "Ramirez", "contrasena1");
    //     User usuario2 = new User(2,"Amanda", "Rodriguez", "contrasena2");
    //     User usuario3 = new User(3,"Jimena", "Gonzalez", "contrasena3");
    //     User usuario4 = new User(4,"Ramon", "Parks", "contrasena4");
    //     User usuario5 = new User(5,"Andrea", "Drummond", "contrasena5");

    //     listaUsuarios.add(usuario0);
    //     listaUsuarios.add(usuario1);
    //     listaUsuarios.add(usuario2);
    //     listaUsuarios.add(usuario3);
    //     listaUsuarios.add(usuario4);
    //     listaUsuarios.add(usuario5);
    // }

    // @GetMapping("/genData")
    // public void genData(){
    //     //this.genUsers();
    //     int index = 0;

    //     for(User usersIterador : listaUsuarios){
    //         usersIterador.genNotas(index);
    //         index++;
    //     }
        
    // }

    @GetMapping("/printAllUsersData")
    public void printData(){
        for(User usersIterador : listaUsuarios){
            System.out.println("--------------------------------------------------------------------------");
            System.out.println("Nombre: "+usersIterador.getNombre()+" "+ usersIterador.getApellido() + " Contraseña: "+usersIterador.getPassword());
            for(Notes notesIteradorPerUser : usersIterador.getListaNotas()){
                System.out.println("--Titulo: "+notesIteradorPerUser.getNoteTitle()+" Texto: "+notesIteradorPerUser.getNoteText());
            }
        }
    }


    //------------------------------------------------------Listos para proyecto------------------------------------------------------
    //------------------------------------------------------------Usuarios------------------------------------------------------------
    @GetMapping("/getListaUsuarios")
    public List<User> getListaUsuarios(){
        return logicService.getListaUsuarios();
    }

    @GetMapping("/getUsuario")
    public User getListaUsuarios(String email){
        return logicService.getUsuario(email);
    }

    @PostMapping("/addUsuario")
    public String addUsuario(String nombre, String apellido, String email, String password){
        if (logicService.addUser(nombre, apellido, email, password)){
            return "Usuario creado con exito! : Nombre: "+ nombre +" | Apellido: "+ apellido +" | Email: "+ email;
        }else{
            return "Creacion de usuario fallida!";
        }
    }

    @PutMapping("/updateUsuario")
    public String updateUsuario(Integer userid, String nombre, String apellido, String email, String password){
        if (logicService.updateUser(userid, nombre, apellido, email, password)){
            return "Usuario actualizado con exito! : Nombre: "+ nombre +" | Apellido: "+ apellido +" | Email: "+ email;
        }else{
            return "Actualizacion de usuario fallida!";
        }
    }

    @DeleteMapping("/deleteUsuario")
    public String deleteUsuario(String email){
        if (logicService.deleteUser(email)){
            return "Usuario eliminado con exito! : Email: "+ email;
        }else{
            return "Error al eliminar usuario!";
        }
    }

    //------------------------------------------------------------Notas------------------------------------------------------------

    @PostMapping("/addNota")
    public String addNota(int userID, String textoNota, String tituloNota){
        if (logicService.addNote(userID,textoNota,tituloNota)){
            return "La nota fue agregada con exito";
        }else{
            return "Error al agregar nota!";
        }
    }

    @GetMapping("/getAllNotas")
    public List<Notes> getallNotes(){
        return logicService.getAllNotes();
    }

    @GetMapping("/getNotasDeUsuario")
    public List<Notes> getNotesForUser(String userEmail){
        return logicService.getNotesForUser(userEmail);
    }
    
    @PutMapping("/updateNota")
    public String updateNote(Integer noteID, String texto, String titulo){
        if (logicService.updateNote(noteID, texto, titulo)){
            return "Nota actualizada con exito!";
        }else{
            return "Actualizacion de Nota fallida!";
        }
    }

    @DeleteMapping("/deleteNota")
    public String deleteNote(Integer noteID){
        if (logicService.deleteNote(noteID)){
            return "Nota eliminada con exito!";
        }else{
            return "Error al eliminar la nota!";
        }
    }

    @PutMapping("/setPasswordEnabled")
    public String setPasswordEnabled(Boolean enabled, Integer noteID, String password){
        if (logicService.setPasswordEnabled(enabled, noteID, password)){
            return "Password habilitado con exito!";
        }else{
            return "Error al habilitar password";
        }
    }

    @PutMapping("/setCategoriaNota")
    public String setNoteCategory(Integer noteID, String categoryName){
        if (logicService.setNoteCategory(noteID, categoryName)){
            return "Categoria actualizada con exito!";
        }else{
            return "Error al actualizar la categoria";
        }
    }

    @PutMapping("/setColorBackgroung")
    public String setBackgroundColor(Integer noteID, String hexCodigoColor){
        if (logicService.setBackgroundColor(noteID, hexCodigoColor)){
            return "Color de background actualizado con exito!";
        }else{
            return "Error al actualizar el color de background";
        }
    }

    @PutMapping("/setEmailsParaNotaCompartida")
    public String setNoteSharedEmails(Integer noteID, String tipo, String email){
        if (logicService.setNoteSharedEmails(noteID, tipo, email)){
            return "Emails compartidos actualizados con exito!";
        }else{
            return "Error al actualizar emails compartidos";
        }
    }
}
