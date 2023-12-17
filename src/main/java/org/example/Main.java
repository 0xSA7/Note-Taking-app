package org.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        User obj = new User();
        obj.addNewUser("Khaled","23324dsfj");
        obj.addNewUser("Ahmed","93842834");
        obj.addNewUser("Mohamed" , "2304fdhf");
        System.out.println(obj.isOldUser("Khaled","23324dsfj"));
        System.out.println(obj.isOldUser("Ahmed","93842834"));
        System.out.println(obj.isOldUser("Ahmed","9384283"));
        System.out.println(obj.isOldUser("Mohamed","2304fdhf"));
        System.out.println(obj.isOldUser("Mohame","223434"));
    }
}
