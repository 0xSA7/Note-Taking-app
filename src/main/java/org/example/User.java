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
    private ArrayList<String> securedNotes ;
    private ArrayList<String> unSecuredNotes ;
//    private List<Note> Notes ;
    public User(String name , String password)throws IOException{
        this.fileManager = new FileManager();
        this.name = name ;
        this.password = password ;
        this.securedNotes = new ArrayList<>();
        this.unSecuredNotes = new ArrayList<>() ;
        fileManager.createNewUser(name, password);
    }

    public String getName(){
        return this.name ;
    }

    public void createNewUnSecuredNote(Note note){
        this.unSecuredNotes.add(note.getNoteName()) ;
        fileManager.createNewUnSecuredNote(this , note);
    }
    public void createNewSecuredNote(Note note , String password){
        this.securedNotes.add(note.getNoteName()) ;
        fileManager.createNewSecuredNote(this,note , password);
    }
    public List<String> getUserSecuredNotes(){
        return securedNotes ;
    }
    public List<String> getUserUnSecuredNotes(){
        return unSecuredNotes ;
    }
}
