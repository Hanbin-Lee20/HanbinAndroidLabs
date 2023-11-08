package Algonquin.cst2355.lee;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ChatMessageDAO {

    @Insert //Compiler knows this is for inserting into database
    public long insertMessage(ChatMessage cm);

    @Delete //Compiler will generate "Delete from Table Where id = ?"
    public int deleteMessage(ChatMessage cm);

    @Query("Select * from ChatMessage")//search statement
    public List<ChatMessage> getAllMessages();
}
