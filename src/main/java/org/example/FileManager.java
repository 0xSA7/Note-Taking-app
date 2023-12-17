package org.example;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;

public class FileManager {
    private final File UsersDir = new File("Users") ;
    private static HashMap<String, String> users ;
    private static HashMap<String , User> allUsers ;
    private static HashMap<Note, String> securedNotes ;
    public FileManager(){
        loadUsers() ;
        loadSecuredNotesFile();
    }
    private  static void loadUsers(){
        File file  = new File("IDandPass.ser") ;
        try{
            if(file.length() == 0){
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file)) ;
                HashMap<String , String> temp = new HashMap<>() ;
                temp.put("admin" , "12345678") ;
                oos.writeObject(temp);
                oos.flush();
                oos.close();
            }
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file)) ;
            users = (HashMap<String , String>) ois.readObject() ;
            ois.close();
        }catch (ClassNotFoundException c){
            c.printStackTrace();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static void loadSecuredNotesFile(){
        File file = new File("secured_notes.ser") ;
        try{
            if(file.length() == 0){
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file)) ;
                HashMap<Note , String> temp = new HashMap<>() ;
                oos.writeObject(temp);
                oos.flush();
                oos.close();
            }
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file)) ;
            securedNotes = (HashMap<Note , String>) ois.readObject() ;
            ois.close();
        }catch (ClassNotFoundException c){
            c.printStackTrace();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    private static HashMap<String , String> getUsers(){
        return users ;
    }
    public static void createNewUser(User user) throws IOException {
        if(users.containsKey(user.getName())){
            System.out.println("This username is already taken try another one");
            return ;
        }
        if(!checkNameAndPasswordValidation(user.getName(),user.getPassword())){
            return ;
        }

        users.put(user.getName() , user.getPassword()) ;
        allUsers.put(user.getName(),user) ;
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("IDandPass.txt")) ;
        oos.writeObject(users);
        oos.flush();
        oos.close();
        File newUser = new File("Users",user.getName()) ;
        newUser.mkdirs() ;
    }
    public static User getUserByName(String name){
        return allUsers.get(name) ;
    }
    public static boolean checkNameAndPasswordValidation(String username , String password){
        // First checking if the username is valid or not
        if (username == null || username.trim().isEmpty()) {
            System.out.println("Username cannot be empty");
            return false;
        }
        if (username.length() < 3 || username.length() > 20) {
            System.out.println("Username must be between 3 and 20 characters");
            return false;
        }
        if (!username.matches("^[a-zA-Z0-9_]+$")) {
            System.out.println("Username can only contain letters, numbers and underscores");
            return false;
        }
        // Now checking if the password is valid or not
        if (password == null || password.trim().isEmpty()) {
            System.out.println("Password cannot be empty");
            return false;
        }
        return true;
    }

    public static HashMap<String, User> getAllUsers(){
        return allUsers ;
    }
    public static boolean checkUserExistance(String name , String password){
        if(users.containsKey(name)) {
            if(users.get(name).equals(password)) {
                System.out.println("Welcome " + name);
                return true ;
            }
            else {
                System.out.println("Wrong password");
                return false ;
            }
        }
        else {
            System.out.println("Wrong username");
            return false ;
        }
    }


    public static void createNewSecuredNote(User user , Note note,String password){
        String path = "Users" + '\\' + user.getName() + "\\" + note.getNoteName() ;
        securedNotes.put(note,password) ;
        File newNote = new File(path) ;
        newNote.mkdirs() ;
    }

    public static void createTextualNote(User user , Note note , String textFileName){
        String textPath ;
        textPath = "Users\\" + user.getName() + "\\" + note.getNoteName() + "\\" + textFileName + ".txt" ;
        File file = new File(textPath) ;
        try{
            file.createNewFile();
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    public static String readFromFile(String filePath) throws IOException {
        byte[] fileBytes = Files.readAllBytes(Path.of(filePath));
        return new String(fileBytes);
    }


    public static void writeToFile(String filePath, String content) throws IOException {
        Files.write(Path.of(filePath), content.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.WRITE);
    }

}
