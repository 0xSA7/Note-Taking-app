package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class User {
    private  final String name ;
    private final String password ;
    private FileManager fileManager ;

    private List<Note> notes ;
    public User(String name , String password)throws IOException{
        this.fileManager = new FileManager();
        this.name = name ;
        this.password = password ;
        this.notes = new ArrayList<>() ;
        fileManager.createNewUser(this);
    }

    public String getName(){
        return this.name ;
    }
    public String getPassword(){
        return this.password ;
    }
    public void createNewSecuredNote(SecureNote note){
        notes.add(note) ;
        fileManager.createNewSecuredNote(this,note , note.getNotePassword());

    }

    public List<Note> getNotes(){
        return this.notes ;
    }
}
