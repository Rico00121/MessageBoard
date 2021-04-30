package group.TwoAndAHalfMen.MessageBoard.data;

import group.TwoAndAHalfMen.MessageBoard.model.Message;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SQLiteDatabaseTest {

    private static SQLiteDatabase database;
    @BeforeAll
    public static void init(){
        System.out.println("初始化数据");
        database=new SQLiteDatabase("messageBoard.sqlite");
    }
    @AfterAll
    public static void finish(){
        database.closeConnection();
    }

    @Test
    void addMessage() {
        Message message=new Message("hello","hello world!","rico","rico.com");
        database.addMessage(message);
    }

    @Test
    void readMessage() {
        List<Message> messagesList=database.readMessage();
        System.out.println(messagesList.toString());
    }
}