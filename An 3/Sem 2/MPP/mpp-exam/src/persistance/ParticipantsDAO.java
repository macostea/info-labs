package persistance;

import model.Participant;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 * Created by mihaicostea on 03/06/15.
 */
public class ParticipantsDAO extends DAO {
    public ArrayList<Participant> getAllParticipants() {
        synchronized (this) {
            try {
                this.connect();
                String query = "SELECT * FROM Participants";

                Statement stmt = this.conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                ArrayList<Participant> result = new ArrayList<Participant>();

                while (rs.next()) {
                    Participant participant = new Participant();
                    participant.setId(rs.getInt("idParticipant"));
                    participant.setName(rs.getString("name"));
                    participant.setLastCheckpoint(rs.getInt("lastCheckpoint"));
                    participant.setStartingTime(rs.getTime("startingTime"));
                    participant.setTimeReached(rs.getTime("timeReached"));

                    result.add(participant);
                }

                rs.close();
                this.disconnect();
                return result;

            } catch (SQLException ex) {
                Logger.getLogger(ParticipantsDAO.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }
    }


    public boolean updateParticipant(Participant participant) {
        synchronized (this) {
            try {
                this.connect();
                String query = "UPDATE Participants SET lastCheckpoint=" + participant.getLastCheckpoint() + "," +
                        " timeReached='" + participant.getTimeReached() + "'" +
                        " WHERE idParticipant=" + participant.getId();
                Statement statement = this.conn.createStatement();

                int res = statement.executeUpdate(query);
                this.disconnect();
                return true;

            } catch (SQLException ex) {
                Logger.getLogger(ParticipantsDAO.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
    }
}
