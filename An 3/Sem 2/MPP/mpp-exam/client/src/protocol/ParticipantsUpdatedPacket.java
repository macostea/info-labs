package protocol;

import model.Participant;

import java.util.ArrayList;

/**
 * Created by mihaicostea on 03/06/15.
 */
public class ParticipantsUpdatedPacket extends Packet {
    public ArrayList<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(ArrayList<Participant> participants) {
        this.participants = participants;
    }

    private ArrayList<Participant> participants;
}
