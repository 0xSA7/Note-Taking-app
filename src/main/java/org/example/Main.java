package org.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        User user1 = new User("Khaled","23324dsfj");
        User user2 = new User("Ahmed","93842834") ;
        User user3 = new User("Mohamed" , "2304fdhf") ;
        FileManager obj = new FileManager();
        System.out.println(obj.checkUserExistance("Khaled","23324dsfj"));
        System.out.println(obj.checkUserExistance("Ahmed","93842834"));
        System.out.println(obj.checkUserExistance("Ahmed","9384283"));
        System.out.println(obj.checkUserExistance("Mohamed","2304fdhf"));
        System.out.println(obj.checkUserExistance("Mohame","223434"));
    }
}
