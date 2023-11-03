package com.proyectoprogra3.proyectoprogra3;
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

    public void genUsers(){
        User usuario0 = new User(0, "Pedro", "Perez", "contrasena0");
        User usuario1 = new User(1,"Pablo", "Ramirez", "contrasena1");
        User usuario2 = new User(2,"Amanda", "Rodriguez", "contrasena2");
        User usuario3 = new User(3,"Jimena", "Gonzalez", "contrasena3");
        User usuario4 = new User(4,"Ramon", "Parks", "contrasena4");
        User usuario5 = new User(5,"Andrea", "Drummond", "contrasena5");

        listaUsuarios.add(usuario0);
        listaUsuarios.add(usuario1);
        listaUsuarios.add(usuario2);
        listaUsuarios.add(usuario3);
        listaUsuarios.add(usuario4);
        listaUsuarios.add(usuario5);
    }

    @GetMapping("/genData")
    public void genData(){
        this.genUsers();
        int index = 0;

        for(User usersIterador : listaUsuarios){
            usersIterador.genNotas(index);
            index++;
        }
        
    }

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

    @GetMapping("/getListaUsuarios")
    public List<User> getListaUsuarios(){
        return listaUsuarios;
    }
}
