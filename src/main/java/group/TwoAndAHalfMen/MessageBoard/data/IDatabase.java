package group.TwoAndAHalfMen.MessageBoard.data;

import group.TwoAndAHalfMen.MessageBoard.model.Message;

import java.util.List;

public interface IDatabase {
    void addMessage(Message message);
    List<Message> readMessage();
    void  closeConnection();
}
