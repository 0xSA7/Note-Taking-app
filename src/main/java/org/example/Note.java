package org.example ;

import java.util.ArrayList;

public class Note {
    protected final String noteName ;
    protected FileManager fileManager ;
    protected boolean secured ;
    protected User user ;
    private ArrayList<String> textualNotes ;
    private ArrayList<String> imageNotes ;
    public Note(String noteName , User user){
        this.noteName = noteName ;
        this.user = user ;
        this.fileManager = new FileManager();
        this.textualNotes = new ArrayList<String>() ;
        this.imageNotes = new ArrayList<>() ;
    }

    public String getNoteName(){
        return this.noteName;
    }

    public boolean isSecured(){
        return secured ;
    }
    public void createTextualNote(  String textFileName){
        this.textualNotes.add(textFileName) ;
        fileManager.createTextualNote(this.user,this,textFileName) ;
    }
}
