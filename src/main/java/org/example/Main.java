package org.example;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner ;
public class Main {
//    private FileManager fm = new FileManager() ;
    private static Scanner in = new Scanner(System.in) ;
    public static void LoginUser(){
        User user ;
        System.out.println("Enter you Name and Password");
        String name = in.nextLine() ;
        String password = in.nextLine();
        FileManager fileManager = new FileManager() ;
        if(!fileManager.checkUserExistance(name,password)){
            System.out.println("User-name or Password Wrong, try again");
            return ;
        }else{
            System.out.println("Welcome "+name);
            user = fileManager.getUserByName(name) ;
        }
        while(true){
            System.out.println("Enter you choice number");
            System.out.println("1- Create New Note");
            System.out.println("2- Edit Old Note");
            System.out.println("3- Exit");
            int choice = in.nextInt();
            in.nextLine() ;
            if (choice == 1) {
                System.out.println("Enter your new note name: ");
                String newNoteName = in.nextLine();
                System.out.println("Enter your note password: ");
                String newNotePassword = in.nextLine();
                SecureNote newNote = new SecureNote(name, password, user);
                user.createNewSecuredNote(newNote);
            }else if(choice == 3){
                System.exit(0);
            }
        }
    }
    public static void SignupUser()throws IOException{
        System.out.print("Enter your name: ") ;
        String name = in.nextLine() ;
        System.out.print("\nEnter new password: ");
        String password = in.nextLine() ;
        User newUser = new User(name, password) ;
        System.out.println();
    }
    public static void run()throws IOException{
        System.out.println("Welcome to your note");
        System.out.println("press 1 for Login and 2 for Signup");
        String response = in.next() ;

        if(response == "1"){
            LoginUser() ;
        }else{
            SignupUser() ;
        }
    }


    public static void main(String[] args) throws IOException, ClassNotFoundException {
        run() ;
//        User user1 = new User("Khaled","23324dsfj");
//        User user2 = new User("Ahmed","93842834") ;
//        User user3 = new User("Mohamed" , "2304fdhf") ;
//        FileManager obj = new FileManager();
//        System.out.println(obj.checkUserExistance("Khaled","23324dsfj"));
//        Note note1 = new Note("Note1" , user2) ;
//        user2.createNewUnSecuredNote(note1);
//        note1.createTextualNote("note1");
//        System.out.println(obj.checkUserExistance("Ahmed","93842834"));
//        System.out.println(obj.checkUserExistance("Ahmed","9384283"));
//        System.out.println(obj.checkUserExistance("Mohamed","2304fdhf"));
//        System.out.println(obj.checkUserExistance("Mohame","223434"));
//        SecureNote secureNote = new SecureNote("Khaled's Note" , "345" , user1) ;
//        user3.createNewSecuredNote(secureNote , secureNote.getNotePassword() );
//        Note note2 = new Note("Note2" , user1 ) ;
//        user1.createNewSecuredNote(note1 , "1234");
//        user1.createNewSecuredNote(note2 , "23423");
//        HashMap<Note , String> securedNotes = new HashMap<>();
//        for(var l : securedNotes.entrySet()){
//            System.out.println(l.toString());
//        }
    }
}
