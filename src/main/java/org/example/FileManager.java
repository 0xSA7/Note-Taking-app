package org.example;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;

public class FileManager {
    private final File UsersDir = new File("Users") ;
    private HashMap<String, String> users ;
    public FileManager(){
        Users() ;
    }
    private  void Users(){
        File file  = new File("IDandPass.txt") ;
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
    private HashMap<String , String> getUsers(){
        return this.users ;
    }
    public void createNewUser(String name , String password) throws IOException {
        if(users.containsKey(name)){
            System.out.println("This username is already taken try another one");
            return ;
        }
        if(!checkNameAndPasswordValidation(name,password)){
            return ;
        }

        users.put(name , password) ;

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("IDandPass.txt")) ;
        oos.writeObject(users);
        oos.flush();
        oos.close();
        File newUser = new File("Users",name) ;
        newUser.mkdirs() ;
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

    public boolean checkUserExistance(String name , String password){
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

    public void createNewUnSecuredNote(User user , Note note){
        String path = "Users" + '\\' + user.getName() + "\\" + "UnSecured_Notes\\" + note.getNoteName() ;
        File newNote = new File(path) ;
        newNote.mkdirs() ;
    }

    public void createNewSecuredNote(User user , Note note,String password){
        String path = "Users" + '\\' + user.getName() + "\\" + "Secured_Notes\\" + note.getNoteName() ;
        File newNote = new File(path) ;
        newNote.mkdirs() ;
    }

    public void createTextualNote(User user , Note note , String textFileName){
        String textPath ;
        if(!note.isSecured()){
            textPath = "Users\\" + user.getName() + "\\" +"UnSecured_Notes\\"+ note.getNoteName() + "\\" + textFileName + ".txt" ;
        }else{
            textPath = "Users\\" + user.getName() + "\\" +"Secured_Notes\\"+ note.getNoteName() + "\\" + textFileName + ".txt" ;
        }
        File file = new File(textPath) ;
        try{
            file.createNewFile();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public String readFromFile(String filePath) throws IOException {
        byte[] fileBytes = Files.readAllBytes(Path.of(filePath));
        return new String(fileBytes);
    }

    public void writeToFile(String filePath, String content) throws IOException {
        Files.write(Path.of(filePath), content.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.WRITE);
    }

}
