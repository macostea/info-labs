package controller;

import model.Participant;
import model.User;
import protocol.*;

import java.io.*;
import java.net.Socket;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by mihaicostea on 21/04/15.
 */
public class Controller extends Observable {
    private Socket socket;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    public Controller() {
        try {
            this.socket = new Socket("localhost", 4322);
            this.outputStream = new ObjectOutputStream(this.socket.getOutputStream());
            this.inputStream = new ObjectInputStream(this.socket.getInputStream());

            new Thread(() -> {
                boolean running = true;
                while (running) {
                    Packet packet = null;

                    try {
                        packet = (Packet) this.inputStream.readObject();
                    } catch (IOException ex) {
                        Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                        System.out.println(ex.getLocalizedMessage());
                        running = false;
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if (packet == null) {
                        continue;
                    }

                    if (packet.getClass().equals(ParticipantsUpdatedPacket.class)) {
                        System.out.println("Update notification received");
                        if (((ParticipantsUpdatedPacket)packet).getParticipants() != null) {
                            this.setChanged();
                        }
                        this.notifyObservers(((ParticipantsUpdatedPacket)packet).getParticipants());
                        this.clearChanged();
                    }

                    if (packet.getClass().equals(ErrorPacket.class)) {
                        System.out.println(((ErrorPacket) packet).getMessage());
                    }

                    if (packet.getClass().equals(GetAllParticipantsPacket.class)) {
                        System.out.println("Got a server response back");
                        if (((GetAllParticipantsPacket)packet).getParticipants() != null) {
                            this.setChanged();
                        }
                        this.notifyObservers(((GetAllParticipantsPacket)packet).getParticipants());
                        this.clearChanged();
                    }

                    if (packet.getClass().equals(LoginPacket.class)) {
                        System.out.println("Login response received");
                        this.setChanged();
                        this.notifyObservers(packet);
                        this.clearChanged();
                    }
                }
            }).start();

        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getParticipants() {
        GetAllParticipantsPacket packet = new GetAllParticipantsPacket();

        try {
            this.outputStream.writeObject(packet);
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loginUser(User u) {
        LoginPacket packet = new LoginPacket();
        packet.setUser(u);
        packet.setLoggedIn(false);

        try {
            this.outputStream.writeObject(packet);
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateParticipant(Participant p) {
        UpdateParticipantsPacket packet = new UpdateParticipantsPacket();
        packet.setParticipant(p);
        try {
            this.outputStream.writeObject(packet);
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//
//    public void sendOrder(Order o) {
//        AddOrderPacket packet = new AddOrderPacket();
//        packet.setOrder(o);
//        try {
//            this.outputStream.writeObject(packet);
//        } catch (IOException ex) {
//            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}
