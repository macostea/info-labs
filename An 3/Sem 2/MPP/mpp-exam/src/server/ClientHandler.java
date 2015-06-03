package server;

import model.Participant;
import model.User;
import protocol.*;

import java.io.*;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;

public class ClientHandler extends Thread {

    Socket socket;
    boolean running;
    InputStream inputStream;
    OutputStream outputStream;
    ObjectInputStream objectInput;
    ObjectOutputStream objectOutput;
    Server server;

    public ClientHandler(Socket socket, Server server) {
        this.server = server;
        this.socket = socket;
        running = true;

        initStreams();
    }

    public void initStreams() {
        try {
            outputStream = socket.getOutputStream();
            objectOutput = new ObjectOutputStream(outputStream);
            objectOutput.flush();

            inputStream = socket.getInputStream();
            objectInput = new ObjectInputStream(inputStream);

            this.server.connections.add(objectOutput);
        } catch (IOException ex) {
            System.out.println("Error on socket streams!");
        }
    }

    private void sendAllParticipants() {
        try {
            Method method = this.server.participantsDAO.getClass().getMethod("getAllParticipants", null);
            ArrayList<Participant> participants = (ArrayList<Participant>)method.invoke(this.server.participantsDAO, null);
            GetAllParticipantsPacket packet = new GetAllParticipantsPacket();
            packet.setParticipants(participants);

            System.out.println(participants);

            try {
                objectOutput.writeObject(packet);
            } catch (IOException ex) {
                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception ex) {
            Logger.getLogger((ClientHandler.class.getName())).log(Level.SEVERE, null, ex);
        }
    }

    private void updateParticipant(Participant p) {
        if (this.server.participantsDAO.updateParticipant(p)) {
            this.server.sendUpdateNotification(this.server.participantsDAO.getAllParticipants());
        } else {
            ErrorPacket packet = new ErrorPacket();
            packet.setMessage("Error processing request");
            try {
                objectOutput.writeObject(packet);
            } catch (IOException ex) {
                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void loginUser(User u) {
        User loggedUser = this.server.usersDAO.loginUser(u);
        LoginPacket loginPacket = new LoginPacket();
        loginPacket.setUser(loggedUser);
        loginPacket.setLoggedIn(loggedUser != null);
        try {
            objectOutput.writeObject(loginPacket);
        } catch (IOException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        while (running) {
            Packet packet = null;

            try {
                packet = (Packet) objectInput.readObject();
            } catch (IOException ex) {
                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(ex.getLocalizedMessage());

                this.server.connections.remove(objectOutput);

                running = false;
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (packet == null) {
                continue;
            }

            if (packet.getClass().equals(GetAllParticipantsPacket.class)) {
                System.out.println("Client requested all participants");
                this.sendAllParticipants();
            }

            if (packet.getClass().equals(UpdateParticipantsPacket.class)) {
                System.out.println("Client updated a participant");
                this.updateParticipant(((UpdateParticipantsPacket) packet).getParticipant());
            }

            if (packet.getClass().equals(LoginPacket.class)) {
                System.out.println("Client is trying to log in");
                this.loginUser(((LoginPacket) packet).getUser());
            }

        }
    }
}
