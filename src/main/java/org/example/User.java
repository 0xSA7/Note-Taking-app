package org.example;

import java.io.*;
import java.util.HashMap;

public class User {
    private HashMap<String , String> users ;
    private FileManager fileManager ;
    private final File file ;
    public User() throws IOException{
        fileManager = new FileManager();
        file  = new File("IDandPass.txt") ;
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
        }
    }

    public void addNewUser(String name , String password) throws IOException {
        if(users.containsKey(name)){
            System.out.println("This username is already taken try another one");
            return ;
        }
        if(!checkValidation(name,password)){
            return ;
        }

        users.put(name , password) ;
        fileManager.createNewUser(name);
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("IDandPass.txt")) ;
        oos.writeObject(users);
        oos.flush();
        oos.close();
    }
    public boolean checkValidation(String username , String password){
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

    public boolean isOldUser(String name , String password) throws IOException {
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
}
