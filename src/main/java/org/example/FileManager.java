package org.example;

import java.io.*;
import java.util.HashMap;

public class FileManager {
    private final File file = new File("Users") ;
    private HashMap<String, String>
    public FileManager(){

    }
    public void createNewUser(String name){
        File newUser = new File("Users",name) ;
        newUser.mkdirs() ;
    }

    public static HashMap<String , String> getUsers(){
        File file  = new File("IDandPass.txt") ;
        try{
            if(file.length() == 0){
                return null ;
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
}
