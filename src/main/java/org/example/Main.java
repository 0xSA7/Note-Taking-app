package org.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        User user1 = new User("Khaled","23324dsfj");
        User user2 = new User("Ahmed","93842834") ;
        User user3 = new User("Mohamed" , "2304fdhf") ;
        FileManager obj = new FileManager();
        System.out.println(obj.checkUserExistance("Khaled","23324dsfj"));
        Note note1 = new Note("Note1" , user2) ;
        user2.createNewUnSecuredNote(note1);
        note1.createTextualNote("note1");
        System.out.println(obj.checkUserExistance("Ahmed","93842834"));
        System.out.println(obj.checkUserExistance("Ahmed","9384283"));
        System.out.println(obj.checkUserExistance("Mohamed","2304fdhf"));
        System.out.println(obj.checkUserExistance("Mohame","223434"));
        SecureNote secureNote = new SecureNote("Khaled's Note" , "345" , user1) ;
        user3.createNewSecuredNote(secureNote , secureNote.getNotePassword() );
        Note note2 = new Note("Note2" , user1 ) ;
        user1.createNewSecuredNote(note1 , "1234");
        user1.createNewSecuredNote(note2 , "23423");
        List<String> list = new ArrayList<>() ;
        list = user1.getUserSecuredNotes() ;
        for(var l : list){
            System.out.println(l.toString());
        }
    }
}
