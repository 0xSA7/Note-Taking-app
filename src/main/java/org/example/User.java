package org.example;

import java.io.*;
import java.util.HashMap;

public class User {
    private  final String name ;
    private final String password ;
    private FileManager fileManager ;
//    private List<Note> Notes ;
    public User(String name , String password)throws IOException{
        this.fileManager = new FileManager();
        this.name = name ;
        this.password = password ;
        fileManager.createNewUser(name, password);
    }

    public void createNewNote(String noteName){

    }
}
