package protocol;

/**
 * Created by mihaicostea on 05/05/15.
 */
public class ErrorPacket extends Packet {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
