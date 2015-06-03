package protocol;

import model.Participant;

import java.util.ArrayList;

/**
 * Created by mihaicostea on 05/05/15.
 */
public class GetAllParticipantsPacket extends Packet {
    private ArrayList<Participant> participants;

    public ArrayList<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(ArrayList<Participant> participants) {
        this.participants = participants;
    }
}
