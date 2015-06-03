package persistance;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by mihaicostea on 16/04/15.
 */
public class DAO {
    protected String dbConnString = "";
    protected String dbDriverName = "com.mysql.jdbc.Connection";

    protected volatile Connection conn;

    public DAO() {
        this.dbConnString = this.getDBConnString();
        if (this.dbConnString.equals("")) {
            this.dbConnString = "jdbc:mysql://localhost/mpp_exam?user=root";
        }
    }

    public void connect() {
        synchronized (this) {
            try {
                Class.forName(this.dbDriverName);
                this.conn = DriverManager.getConnection(this.dbConnString);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void disconnect() {
        synchronized (this) {
            try {
                this.conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public String getDBConnString() {
        final String[] connString = {""};

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            DefaultHandler handler = new DefaultHandler() {
                boolean connStringExists = false;

                @Override
                public void startElement(String uri, String localName, String qName, org.xml.sax.Attributes attributes) throws SAXException {
                    if (qName.equalsIgnoreCase("dbAddr")) {
                        connStringExists = true;
                    }
                }

                public void endElement(String uri, String localName, String qName) throws SAXException {
                    if (qName.equalsIgnoreCase("dbAddr")) {
                        connStringExists = false;
                    }
                }

                public void characters(char ch[], int start, int length) throws SAXException {
                    if (connStringExists) {
                        connString[0] = new String(ch, start, length);
                    }
                }
            };

            saxParser.parse("config.xml", handler);

        } catch (Exception ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.INFO, null, ex);
        }

        return connString[0];
    }
}
