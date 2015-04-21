package com.mcostea.SalesAgency.protocol;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataRequestor {

    public static ObjectOutputStream outputStream;

    public static void getAllBooks(Socket sock) {
        try {
            Packet packet = new Packet();
            packet.setRequestType(RequestType.GET_ALL_ORDERS);
            outputStream.writeObject(packet);

            System.out.println("REQUESTED ALL BOOKS");
        } catch (IOException ex) {
            Logger.getLogger(DataRequestor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    public static void getUserBooks(Socket sock, String userId) {
//        try {
//            Packet packet = new Packet(userId, 1, null, null, null);
//            outputStream.writeObject((Object) packet);
//
//            System.out.println("REQUESTED BOOKS FOR USER: " + userId);
//        } catch (IOException ex) {
//            Logger.getLogger(DataRequestor.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    public static void borrowBookForUser(Socket sock, Book book, String userId) {
//        try {
//            Packet packet = new Packet(userId, 2, null, null, book);
//            outputStream.writeObject((Object) packet);
//
//            System.out.println("REQUESTED BORROW " + book + " FOR USER: " + userId);
//        } catch (IOException ex) {
//            Logger.getLogger(DataRequestor.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    public static void returnBookForUser(Socket sock, Book book, String userId) {
//        try {
//            Packet packet = new Packet(userId, 3, null, null, book);
//            outputStream.writeObject((Object) packet);
//
//            System.out.println("REQUESTED RETURN " + book + " FOR USER: " + userId);
//        } catch (IOException ex) {
//            Logger.getLogger(DataRequestor.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    public static void searchBookForString(Socket sock, String searchString) {
//        try {
//            Packet packet = new Packet(null, 4, null, searchString, null);
//            outputStream.writeObject((Object) packet);
//            System.out.println("REQUESTED SERCH WITH SUBSTRING: " + searchString);
//        } catch (IOException ex) {
//            Logger.getLogger(DataRequestor.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}
