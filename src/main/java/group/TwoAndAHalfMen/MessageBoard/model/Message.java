package group.TwoAndAHalfMen.MessageBoard.model;

public class Message {
    private String title;
    private String content;
    private String sender;
    private String urlAddress;
    public Message(String title,String content,String sender,String urlAddress){
        this.title=title;
        this.content=content;
        this.sender=sender;
        this.urlAddress=urlAddress;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getUrlAddress() {
        return urlAddress;
    }

    public void setUrlAddress(String urlAddress) {
        this.urlAddress = urlAddress;
    }
}
