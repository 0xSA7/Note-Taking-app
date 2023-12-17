package org.example;


import java.util.ArrayList;

public class SecureNote implements Note {
    protected final String noteName ;
    protected FileManager fileManager ;
    protected boolean secured ;
    protected User user ;
    private ArrayList<String> textualNotes ;
    private ArrayList<String> imageNotes ;
    private final String notePassword ;
    public SecureNote(String name,String password,User user){

        this.noteName = name ;
        this.user = user ;
        this.fileManager = new FileManager();
        this.textualNotes = new ArrayList<String>() ;
        this.imageNotes = new ArrayList<>() ;
        this.secured = true;
        this.notePassword = password ;
    }

    public String getNotePassword(){
        return this.notePassword ;
    }

    @Override
    public String getNoteName() {
        return this.noteName ;
    }

    @Override
    public void createTextualNote(String textFileName) {
        this.textualNotes.add(textFileName) ;
        fileManager.createTextualNote(this.user,this,textFileName) ;
    }
}
