package group.TwoAndAHalfMen.MessageBoard.data;

import group.TwoAndAHalfMen.MessageBoard.model.Message;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SQLiteDatabase implements IDatabase {
    protected Connection connection;

    public SQLiteDatabase(String dbFileName){
        try {
            this.connection = DriverManager.getConnection("jdbc:sqlite:" + dbFileName);
            createMessageTable();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void  closeConnection(){
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    //String title,String content,String sender,String urlAddress
    private void createMessageTable(){
        String query="CREATE TABLE IF NOT EXISTS messages (id integer PRIMARY KEY,title text NOT NULL,\n" +
                "content text NOT NULL,sender text NOT NULL,urlAddress text NOT NULL);";
        try {
            PreparedStatement statement=connection.prepareStatement(query);
            statement.execute();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addMessage(Message message) {
        String query="INSERT INTO messages(title, content, sender, urlAddress) VALUES(?,?,?,?)";
        try {
            PreparedStatement statement=connection.prepareStatement(query);
            statement.setString(1, message.getTitle());
            statement.setString(2,message.getContent());
            statement.setString(3,message.getSender());
            statement.setString(4,message.getUrlAddress());
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


    public List<Message> readMessage() {
        String query="SELECT * FROM messages";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            List<Message> messages = new ArrayList<>();
            while (resultSet.next()) {
                Message message = new Message(resultSet.getString("title"),
                        resultSet.getString("content"),
                        resultSet.getString("sender"),
                        resultSet.getString("urlAddress"));
                messages.add(message);
            }
            return messages;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }
}
