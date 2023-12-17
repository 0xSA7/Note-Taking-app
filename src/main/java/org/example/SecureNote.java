package org.example;


public class SecureNote extends Note{
    private final String notePassword ;
    public SecureNote(String name,String password,User user){
        super(name,user) ;
        this.secured = true;
        this.notePassword = password ;
    }

    public String getNotePassword(){
        return this.notePassword ;
    }
}
