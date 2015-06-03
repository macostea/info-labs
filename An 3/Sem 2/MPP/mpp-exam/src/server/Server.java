package server;

import model.*;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import persistance.*;
import protocol.ParticipantsUpdatedPacket;

import javax.xml.XMLConstants;
import javax.xml.bind.Validator;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

    ServerSocket serverSocket;
    public volatile ArrayList<ObjectOutputStream> connections = new ArrayList<ObjectOutputStream>();
    private volatile ArrayList<ClientHandler> workers = new ArrayList<ClientHandler>();

    public ParticipantsDAO participantsDAO = new ParticipantsDAO();
    public UsersDAO usersDAO = new UsersDAO();

    public void run() throws IOException {

        int port = this.getPortNumber();

        if (port == -1) {
            port = 4322;
        }

        serverSocket = new ServerSocket(port);

        while (true) {
            Socket newConnection = serverSocket.accept();

            System.out.println("New client CONNECTED!");

            if (newConnection != null) {
                ClientHandler handler = new ClientHandler(newConnection, this);
                workers.add(handler);
                handler.start();
            }
        }
    }

    public void sendUpdateNotification(ArrayList<Participant> participants) {
        synchronized (this) {
            for (ObjectOutputStream ou : this.connections) {
                ParticipantsUpdatedPacket packet = new ParticipantsUpdatedPacket();
                packet.setParticipants(participants);
                try {
                    ou.writeObject(packet);
                } catch (IOException ex) {
                    Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public int getPortNumber() {
        final int[] portNumber = {-1};

        try {
            Source xmlFile = new StreamSource(new File("config.xml"));
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(new File("configSchema.xsd"));
            javax.xml.validation.Validator validator = schema.newValidator();
            validator.validate(xmlFile);

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            DefaultHandler handler = new DefaultHandler() {
                boolean portNumberExists = false;

                @Override
                public void startElement(String uri, String localName, String qName, org.xml.sax.Attributes attributes) throws SAXException {
                    if (qName.equalsIgnoreCase("port")) {
                        portNumberExists = true;
                    }
                }

                public void endElement(String uri, String localName, String qName) throws SAXException {
                    if (qName.equalsIgnoreCase("port")) {
                        portNumberExists = false;
                    }
                }

                public void characters(char ch[], int start, int length) throws SAXException {
                    if (portNumberExists) {
                        String portNumberString = new String(ch, start, length);
                        portNumber[0] = Integer.parseInt(portNumberString);
                    }
                }
            };

            saxParser.parse("config.xml", handler);

        } catch (Exception ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.INFO, null, ex);
        }

        return portNumber[0];
    }
}
