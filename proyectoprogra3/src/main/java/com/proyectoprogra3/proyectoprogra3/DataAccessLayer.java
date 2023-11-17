package com.proyectoprogra3.proyectoprogra3;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class DataAccessLayer {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean isDatabaseConnected() {
        try {
            jdbcTemplate.queryForObject("SELECT 1 FROM DUAL", Integer.class);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    // public boolean queryData() {
    //     try {
    //         List<String> myList = jdbcTemplate.queryForList("SELECT nombre FROM inotes.users", String.class);
    //         System.out.println(myList);
    //         return true;
    //     } catch (Exception e) {
    //         System.out.println(e);
    //         return false;
    //     }
    // }
    // public List<String> queryListUsers1() {
    //     List<String> myList = null;
    //     try {
    //         myList = jdbcTemplate.queryForList("SELECT nombre FROM inotes.users", String.class);
    //         System.out.println(myList);
    //         return myList;
    //     } catch (Exception e) {
    //         System.out.println(e);
    //         return myList;
    //     }
    // }

    public List<User> queryListAllUsers() {
        
        try {
            String query = "SELECT * FROM inotes.users";
            List<Map<String, Object>> resultList = jdbcTemplate.queryForList(query);
            List<User> listaUsers = new ArrayList<>();

            for (Map<String, Object> row : resultList){
                int userID = (int) row.get("user_id");
                String nombreUser = (String) row.get("nombre");
                String apellidoUser = (String) row.get("apellido");
                String passwordUser = (String) row.get("password");
                String emailUser = (String) row.get("email");
                LocalDate createDate = (LocalDate) LocalDate.parse(row.get("created_date").toString());
                LocalDate modifiedDate = (LocalDate) LocalDate.parse(row.get("modified_date").toString());
                Boolean activeUser = (Boolean) row.get("active");

                User elUser = new User(userID, nombreUser, apellidoUser, passwordUser, emailUser, createDate, modifiedDate, activeUser);
                listaUsers.add(elUser);
                }
            //System.out.println(myList);
            return listaUsers;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public User querySpecificUser(String email) {
        try {
            String query = "SELECT * FROM inotes.users WHERE email = ?";
            return jdbcTemplate.queryForObject(query, (rs, rowNum) -> {
                int userID = (int) rs.getInt("user_id");
                String nombreUser = rs.getString("nombre");
                String apellidoUser = rs.getString("apellido");
                String passwordUser = rs.getString("password");
                String emailUser = rs.getString("email");
                LocalDate createDate = LocalDate.parse(rs.getDate("created_date").toString());
                LocalDate modifiedDate = LocalDate.parse(rs.getDate("modified_date").toString());
                Boolean activeUser = rs.getBoolean("active");

                return new User(userID, nombreUser, apellidoUser, passwordUser, emailUser,createDate, modifiedDate, activeUser);
            }, email);
            //System.out.println(myList);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public boolean addUser(User theUser) {
        try {
            String todayDate = LocalDate.now().toString();
            String query = "INSERT INTO inotes.users (user_id,nombre, apellido, password, email, created_date, modified_date, active) VALUES ('?','?','?','?','?','?', '?','?')";
            jdbcTemplate.update(query,15, theUser.getNombre(),theUser.getApellido(), theUser.getEmail(), todayDate, todayDate, 1);
            //System.out.println(myList);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean updateUser(Integer userid, String nombre, String apellido, String email, String password) {
        try {
            String todayDate = LocalDate.now().toString();
            jdbcTemplate.execute("UPDATE inotes.users SET nombre = '"+nombre+"', apellido = '"+apellido+"', email = '"+email+"', password = '"+password+"', modified_date = '"+todayDate+"', active = 1 WHERE user_id = "+userid);
            //System.out.println(myList);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean deleteUser(String email){
        try {
            jdbcTemplate.execute("DELETE FROM inotes.users WHERE email = '"+email+"'");
            //System.out.println(myList);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean addNote(int userID, String textoNota, String tituloNota) {
        try {
            String todayDate = LocalDate.now().toString();
            jdbcTemplate.execute("INSERT INTO inotes.notes (user_id, text, title, bg_color, pw_enabled, note_password, note_category, note_shared_with_userIDs, created_date, modified_date) VALUES ('"+ userID +"', '"+ textoNota+"', '"+tituloNota+"', '#FFFFFF', 0, '', '', '', '"+todayDate+"', '"+todayDate+"')");
            //System.out.println(myList);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }


    public List<Map<String, Object>> queryListAllNotes(){
        List<Map<String, Object>> myList = null;
        try {
            myList = jdbcTemplate.queryForList("SELECT * FROM inotes.notes");
            //System.out.println(myList);
            return myList;
        } catch (Exception e) {
            System.out.println(e);
            return myList;
        }
    }

    public List<Map<String, Object>> queryNotesForUser(Integer userID, String userEmail){
        List<Map<String, Object>> myList = null;
        try {
            myList = jdbcTemplate.queryForList("SELECT * FROM inotes.notes WHERE user_id = "+userID+" OR note_shared_with_emails like '%"+userEmail+"%'");
            //System.out.println(myList);
            return myList;
        } catch (Exception e) {
            System.out.println(e);
            return myList;
        }
    }

    public Boolean updateNote(Integer noteID, String texto, String titulo){
        try {
            String todayDate = LocalDate.now().toString();
            jdbcTemplate.execute("UPDATE inotes.notes SET text = '"+texto+"', title = '"+titulo+"', modified_date = '"+todayDate+"' WHERE note_id = "+noteID);
            //System.out.println(myList);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public Boolean deleteNote(Integer noteID){
        try {
            jdbcTemplate.execute("DELETE FROM inotes.notes WHERE note_id = "+noteID);
            //System.out.println(myList);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public Boolean setPasswordEnabled(Boolean enabled, Integer noteID, String password){
        try {
            String todayDate = LocalDate.now().toString();
            if (enabled){
                jdbcTemplate.execute("UPDATE inotes.notes SET pw_enabled = 1, note_password = '"+password+"', modified_date = '"+todayDate+"' WHERE note_id = "+noteID);
            }else{
                jdbcTemplate.execute("UPDATE inotes.notes SET pw_enabled = 0, note_password = '', modified_date = '"+todayDate+"' WHERE note_id = "+noteID);
            }
            //System.out.println(myList);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public Boolean setNoteCategory(Integer noteID, String categoryName){
        try {
            String todayDate = LocalDate.now().toString();
            jdbcTemplate.execute("UPDATE inotes.notes SET note_category = '"+categoryName+"', modified_date = '"+todayDate+"' WHERE note_id = "+noteID);
            //System.out.println(myList);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public Boolean setBackgroundColor(Integer noteID, String hexColorCode){
        try {
            String todayDate = LocalDate.now().toString();
            hexColorCode = "#"+hexColorCode;
            jdbcTemplate.execute("UPDATE inotes.notes SET bg_color = '"+hexColorCode+"', modified_date = '"+todayDate+"' WHERE note_id = "+noteID);
            //System.out.println(myList);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public String getEmailsCompatidos(Integer noteID){
        List<Map<String, Object>> myList = null;
        try {
            myList = jdbcTemplate.queryForList("SELECT note_shared_with_emails FROM inotes.notes WHERE note_id = "+noteID);
            String emails = myList.get(0).get("note_shared_with_emails").toString();
            return emails;
        } catch (Exception e) {
            System.out.println(e);
            return "transaccion invalida";
        }
    }

    public Boolean setNoteSharedEmails(Integer noteID, String emails){
        try {
            jdbcTemplate.execute("UPDATE inotes.notes SET note_shared_with_emails = '"+emails+"' WHERE note_id = "+noteID);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
}
