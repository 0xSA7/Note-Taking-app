package org.example;

import java.io.*;
import java.util.HashMap;

public class User {
    private FileManager fileManager ;
    public User() throws IOException{
        this.fileManager = new FileManager();
    }

    public void addNewUser(String name , String password) throws IOException {
        fileManager.createNewUser(name , password);
    }

    public boolean isOldUser(String name , String password) throws IOException {
        return fileManager.checkUserExistance(name , password) ;
    }
}
