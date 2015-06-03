package protocol;

import model.Participant;

/**
 * Created by mihaicostea on 03/06/15.
 */
public class UpdateParticipantsPacket extends Packet {
    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    private Participant participant;
}
