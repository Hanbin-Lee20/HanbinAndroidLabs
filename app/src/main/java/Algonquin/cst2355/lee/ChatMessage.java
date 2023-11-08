package Algonquin.cst2355.lee;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ChatMessage {

    @PrimaryKey(autoGenerate=true) //the DB manages IDs
    @ColumnInfo(name = "ID")
    public long id;

    @ColumnInfo(name="Message")
    public String message;

    @ColumnInfo(name = "TimeSent")
    public String timeSent;

    @ColumnInfo(name = "SendOrReceive")
    boolean isSentButton;

    public  ChatMessage() {}

    public ChatMessage(String m, String t, boolean isS) {

        message = m;
        timeSent = t;
        isSentButton = isS;
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
