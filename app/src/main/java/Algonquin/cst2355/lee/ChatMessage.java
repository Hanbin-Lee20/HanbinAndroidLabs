package Algonquin.cst2355.lee;

public class ChatMessage {

    String message;
    String timeSent;
    boolean isSentButton;

    public ChatMessage(String m, String t, boolean sent) {
        message = m;
        timeSent = t;
        isSentButton = sent;
    }

    public String getMessage() {
        return message;
    }

    public String getTimeSent() {
        return timeSent;
    }

    public boolean isSent() {
        return isSentButton;
    }


    @Override
    public String toString() {
        return message;
    }


}
